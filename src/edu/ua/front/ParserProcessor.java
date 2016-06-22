/**
 * Copyright (c) 2013 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Aziz Nanthaamornphong
 */
package edu.ua.front;

import edu.ua.fortran.FortranParserExtras;
import edu.ua.fortran.FortranParserRiceCAF;
import edu.ua.fortran.FortranLexer;
import edu.ua.meta.ViewFactory;
import edu.ua.meta.ViewMeta;
import edu.ua.util.FortranLexicalPrepass;
import edu.ua.util.FortranParserActionPrint;
import edu.ua.util.FortranStream;
import edu.ua.util.FortranTokenStream;
import edu.ua.util.GlobalErrorException;
import edu.ua.util.IFortranParser;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.antlr.runtime.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ParserProcessor {

  private FortranStream inputStream;
  private FortranTokenStream tokens;
  private FortranLexer lexer;
  private IFortranParser parser;
  private FortranLexicalPrepass prepass;
  private String filename;
  private int sourceForm;
  private boolean verbose = false;
  private static boolean hasErrorOccurred = false;
  private static ArrayList<String> includeDirs;
  
  private static int countFile = 1;
  private static int countModule = 0;
  
  SimpleAttributeSet BOLD_BLACK = new SimpleAttributeSet();
  SimpleAttributeSet ERROR_RED = new SimpleAttributeSet();

  public ParserProcessor() {
  }

  public ParserProcessor(String[] args, String filename, String type) throws Exception {
     
    boolean riceCAF = false;

    File file = new File(filename);
    System.out.print(countFile+"::"+file.getName());
    countFile++;
    String path = file.getAbsolutePath();

    this.inputStream = new FortranStream(filename);
    try {
      this.lexer = new FortranLexer(inputStream);

      // Changes associated with antlr version 3.3 require that includeDirs
      // be set here as the tokens are loaded by the constructor.
      this.lexer.setIncludeDirs(includeDirs);
      this.tokens = new FortranTokenStream(lexer);

      // check to see if using RiceCAF parser extensions
      //
      for (int i = 0; i < args.length; i++) {
        if (args[i].startsWith("--RiceCAF")) {
          riceCAF = true;
        }
      }

      if (riceCAF == false) {
        this.parser = new FortranParserExtras(tokens);
      } else {
        // laksono 08.06.2010: only output if we have --verbose option set
        if (verbose) {
          System.out.println("FortranLexer: using Rice University's CAF extensions");
        }
        this.parser = new FortranParserRiceCAF(tokens);
      }
      this.parser.initialize(args, type, filename, path);

      this.prepass = new FortranLexicalPrepass(lexer, tokens, parser);
      this.filename = filename;
      this.sourceForm = inputStream.getSourceForm();
    } catch (Exception e) {
    }

  }

  private static boolean parseMainProgram(FortranTokenStream tokens,
          IFortranParser parser, int start) throws Exception {
    // try parsing the main program
    parser.main_program();

    return parser.hasErrorOccurred();
  } // end parseMainProgram()

  private static boolean parseModule(FortranTokenStream tokens,
          IFortranParser parser, int start) throws Exception {
    parser.module();
    return parser.hasErrorOccurred();
  } // end parseModule()

  private static boolean parseSubmodule(FortranTokenStream tokens,
          IFortranParser parser, int start) throws Exception {
    parser.submodule();
    return parser.hasErrorOccurred();
  } // end parseSubmodule()

  private static boolean parseBlockData(FortranTokenStream tokens,
          IFortranParser parser, int start) throws Exception {
    parser.block_data();

    return parser.hasErrorOccurred();
  } // end parseBlockData()

  private static boolean parseSubroutine(FortranTokenStream tokens,
          IFortranParser parser, int start) throws Exception {
    parser.subroutine_subprogram();

    return parser.hasErrorOccurred();
  } // end parserSubroutine()

  private static boolean parseFunction(FortranTokenStream tokens,
          IFortranParser parser, int start) throws Exception {
    parser.ext_function_subprogram();
    return parser.hasErrorOccurred();
  } // end parseFunction()

  private static boolean parseProgramUnit(FortranLexer lexer,
          FortranTokenStream tokens, IFortranParser parser) throws Exception {
    int firstToken;
    int lookAhead = 1;
    int start;
    boolean error = false;

    // check for opening with an include file
    parser.checkForInclude();

    // first token on the *line*. will check to see if it's
    // equal to module, block, etc. to determine what rule of
    // the grammar to start with.
    try {
      lookAhead = 1;
      do {
        firstToken = tokens.LA(lookAhead);
        lookAhead++;
      } while (firstToken == FortranLexer.LINE_COMMENT
              || firstToken == FortranLexer.T_EOS);


      // mark the location of the first token we're looking at
      start = tokens.mark();

      // attempt to match the program unit
      // each of the parse routines called will first try and match
      // the unit they represent (function, block, etc.). if that
      // fails, they may or may not try and match it as a main
      // program; it depends on how it fails.
      //
      // due to Sale's algorithm, we know that if the token matches
      // then the parser should be able to successfully match.
      if (firstToken != FortranLexer.EOF) {
        if (firstToken == FortranLexer.T_MODULE
                && tokens.LA(lookAhead) != FortranLexer.T_PROCEDURE) {
          // try matching a module
          error = parseModule(tokens, parser, start);
        } else if (firstToken == FortranLexer.T_SUBMODULE) {
          // try matching a submodule
          error = parseSubmodule(tokens, parser, start);
        } else if (firstToken == FortranLexer.T_BLOCKDATA
                || (firstToken == FortranLexer.T_BLOCK
                && tokens.LA(lookAhead) == FortranLexer.T_DATA)) {
          // try matching block data
          error = parseBlockData(tokens, parser, start);
        } else if (tokens.lookForToken(FortranLexer.T_SUBROUTINE) == true) {
          // try matching a subroutine
          error = parseSubroutine(tokens, parser, start);
        } else if (tokens.lookForToken(FortranLexer.T_FUNCTION) == true) {
          // try matching a function
          error = parseFunction(tokens, parser, start);
        } else {
          // what's left should be a main program
          error = parseMainProgram(tokens, parser, start);
        }// end else(unhandled token)
      }// end if(file had nothing but comments empty)
    } catch (RecognitionException e) {
      System.out.print(e.getMessage());
      e.printStackTrace();
      error = true;
    }// end try/catch(parsing program unit)

    return error;
  } // end parseProgramUnit()

  public void setVerbose(Boolean vFlag, Boolean sFlag) {
    this.verbose = vFlag;
  }

  public IFortranParser getParser() {
    return this.parser;
  }

  //Aziz
  private boolean validateFortranCode(File file)
    {
         String pattern = "end\\s*type";
         boolean hasModule = false;
         boolean hasType = false;
         Pattern r = Pattern.compile(pattern);
         
         if(file.exists()){
           try{
             FileInputStream fstream = new FileInputStream(file);
             DataInputStream in = new DataInputStream(fstream);
             BufferedReader br = new BufferedReader(new InputStreamReader(in));
             String strLine;
             while ((strLine = br.readLine()) != null)   {
               String line = strLine.toLowerCase().trim();
               if(hasModule == false && line.startsWith("module")){
                   hasModule = true;
                   countModule++;
               }
               Matcher m = r.matcher(line);
               
               if(hasType == false && m.find()){
                   hasType = true;
               }  
             }
             in.close();
             System.out.println("Total Modules::"+countModule);
           }catch (Exception e){//Catch exception if any
             e.printStackTrace();
           }
         }
         if(hasModule && hasType){
             return true;
         }else{
            
             return false;
         }
    }
  
  //Aziz
  public void process(String[] args, String output, JTextPane pane) throws Exception {
    Boolean error = false;
    Boolean verbose = false;
    Boolean silent = true;
    Boolean dumpTokens = false;
    ArrayList<String> newArgs = new ArrayList<String>(0);
    String type = "edu.util.FortranParserActionNull";
    int nArgs = 0;
    boolean rice_caf = false;
    StyleConstants.setBold(ERROR_RED, true);
     StyleConstants.setForeground(ERROR_RED, Color.RED);
     StyleConstants.setBold(BOLD_BLACK, true);
    //Aziz 
    // this.output = output;

    includeDirs = new ArrayList<String>();

    // Get the arguments. Use --silent --verbose, and --dump as shorthand
    // so we don't have to specify explicit class names on the command line.
    for (int i = 0; i < args.length; i++) {
      if (args[i].startsWith("--RiceCAF")) {
        newArgs.add(args[i]);
        rice_caf = true;
        nArgs += 1;
        continue;
      } else if (args[i].startsWith("--dump")) {
        type = "edu.ua.util.FortranParserActionPrint";
        silent = false;
        nArgs += 1;
        continue;
      } else if (args[i].startsWith("--verbose")) {
        type = "edu.ua.util.FortranParserActionPrint";
        verbose = true;
        silent = false;
        nArgs += 1;
        continue;
      } else if (args[i].startsWith("--silent")) {
        type = "edu.ua.util.FortranParserActionPrint";
        silent = true;
        nArgs += 1;
        continue;
      } else if (args[i].startsWith("--tokens")) {
        dumpTokens = true;
        nArgs += 1;
        continue;
      } else if (args[i].startsWith("--class")) {
        i += 1;
        type = args[i];
        nArgs += 2;
        continue;
      } else if (args[i].startsWith("-I")) {
        /* Skip the include dir stuff; it's handled by the lexer. */
        nArgs += 1;
        includeDirs.add(args[i].substring(2, args[i].length()));
      } else if (args[i].startsWith("--")) {
        newArgs.add(args[i]);
        newArgs.add(args[i + 1]);
        i += 1;
        nArgs += 2;
        continue;
      }
    }
   
    if (args.length <= nArgs) {
      System.out.println("Usage: java edu.ua.front.FrontUI "
              + "[--verbose] [--tokens] [--silent] [--class className] ");
      System.out.println("                                    "
              + "[--user_option user_arg] file1 [file2..fileN]");
    }

    for (int i = 0; i < args.length; i++) {
      if (!rice_caf && (args[i].startsWith("--dump") | args[i].startsWith("--silent")
              | args[i].startsWith("--verbose") | args[i].startsWith("--tokens"))) {
        continue;
      } else if (args[i].startsWith("-I")) {
        /* Skip the include dir stuff; it's handled by the lexer. */
        continue;
      } else if (args[i].startsWith("--")) {
        i += 1;
        continue;
      }
      
      
      
      if (verbose) {
       //Do nothing  
      }
      
      /* Make sure the file exists. */
      File srcFile = new File(args[i]);
      FortranParserActionPrint action = null;
      if (srcFile.exists() == false) {
        pane.getDocument().insertString(pane.getDocument().getLength(), "Error: " + args[i] + " does not exist!"+System.getProperty("line.separator"), ERROR_RED);
        error = new Boolean(true);
      }else if(!validateFortranCode(srcFile)){
        pane.getDocument().insertString(pane.getDocument().getLength(), "The " + args[i] + " is not parsed : "+"No Module or Type"+System.getProperty("line.separator"), ERROR_RED);
        GlobalErrorException.setHasError(true);
      }else {
        includeDirs.add(srcFile.getParent());

        ParserProcessor ofp = new ParserProcessor(newArgs.toArray(new String[newArgs.size()]), args[i], type);
        verbose = false;
        silent = true;
        ofp.setVerbose(verbose, silent);
        if (ofp.getParser().getAction().getClass().getName() == "fortran.ofp.parser.java.FortranParserActionPrint") {
          action = (FortranParserActionPrint) ofp.getParser().getAction();
          // "verbose" in Print() is either "normal" or "verbose" here..
          action.setVerbose(!silent);
        }
       
        if (dumpTokens) {
           ofp.tokens.outputTokenList(ofp.parser.getAction());     
        } else {
             error |= ofp.call(output);      
        }
        if(GlobalErrorException.getError() != null){
            pane.getDocument().insertString(pane.getDocument().getLength(), GlobalErrorException.getError()+System.getProperty("line.separator"), ERROR_RED);
            GlobalErrorException.setError(null);
        }else{
            pane.getDocument().insertString(pane.getDocument().getLength(), new File(args[i]).getName() +"... was successfully parsed!"+System.getProperty("line.separator"), BOLD_BLACK);
        }      
      }
      if (verbose) {
        //Do nothing
      }

    }
    hasErrorOccurred = error.booleanValue();

    // Reports are that ROSE sometimes doesn't get notification
    // of a failure.  Try calling exit directly with an error condition.
    if (hasErrorOccurred) {
    
      //System.exit(1);
    }
  }
  //Aziz

  public Boolean call(String output) throws Exception {
    boolean error = false;

    int sourceForm = inputStream.getSourceForm();

    if (sourceForm == FortranStream.FIXED_FORM) {
      if (verbose) {
        System.out.println(filename + " is FIXED FORM");
      } else if (verbose) {
        System.out.println(filename + " is FREE FORM");
      }
    }

    // determine whether the file is fixed or free form and
    // set the source form in the prepass so it knows how to handle lines.
    prepass.setSourceForm(sourceForm);

    // apply Sale's algorithm to the tokens to allow keywords
    // as identifiers. also, fixup labeled do's, etc.
    prepass.performPrepass();

    // overwrite the old token stream with the (possibly) modified one
    tokens.finalizeTokenStream();

    // parse each program unit in a given file
    while (tokens.LA(1) != FortranLexer.EOF) {

      //Aziz
      // attempt to parse the current program unit
      error = parseProgramUnit(lexer, tokens, parser);

      // see if we successfully parse the program unit or not
      if (verbose && error) {
        return new Boolean(error);
      }
    } // end while (not end of file)
    //Aziz
    
    ViewMeta meta = ViewFactory.getViewMeta();
    Generator generator = new Generator();
    generator.setOutput(output);
    generator.generate(meta.getAllItems());
    
    //Aziz
    // Call the end_of_file action here so that it comes after the
    // end_program_stmt occurs.
    getParser().eofAction();

    // Call the cleanUp method for the give action class. This is more
    // important in the case of a C action *class* since it could easily

    // have created memory that's outside of the jvm.
    getParser().getAction().cleanUp();
    if (verbose) {
      System.out.println("Parser exiting normally");

    }

    return new Boolean(error);
  } // end call()
}