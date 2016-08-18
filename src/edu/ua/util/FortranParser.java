/*
Copyright (c) 2016, Aziz Nanthaamornphong and Anawat Leathongkum

Redistribution and use in source and binary forms, with or without modification, are 
permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of 
conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of 
conditions and the following disclaimer in the documentation and/or other materials 
provided with the distribution.

3. Neither the names of the copyright holders nor the names of its contributors may be 
used to endorse or promote products derived from this software without specific prior 
written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package edu.ua.util;

import edu.ua.fortran.FortranLexer;
import org.antlr.runtime.*;

import java.util.HashMap;

public abstract class FortranParser extends Parser implements IFortranParser {

  /* Provide action objects to implement the AST.  These are singleton objects. */
  public static IFortranParserAction action = null;
  protected static IFortranParserAction nullAction = new FortranParserActionNull(null, null, null);
  FortranParser delegate = null;
  public String filename;
  public String pathname;
 
  boolean has_error_occurred = false;

  public FortranParser(TokenStream input, RecognizerSharedState state) {
    super(input, state);

    // TODO - see if the size has to increase with new F2008 rules
    state.ruleMemo = new HashMap[489 + 1];
  }

  public void initialize(FortranParser delegate, IFortranParserAction action,
          String filename, String path) {
    this.delegate = delegate;
    this.filename = filename;
    this.pathname = path;

//      if (FortranParser.action != null) FortranParser.action = action;
    FortranParser.action = action;
  }

  public boolean hasErrorOccurred() {
    return delegate.has_error_occurred;
  }

  public void reportError(RecognitionException re) {
    super.reportError(re);

    // Change action class to FortranParserActionNull so that actions are no
    // longer called.  This will allow error handling to be done by ANTLR
    // only.
    //
    this.action = nullAction;

    delegate.has_error_occurred = true;
    
    String header = getErrorHeader(re);
    String msg = super.getErrorMessage(re, this.getTokenNames());
    GlobalErrorException.setError(header+" "+msg);
    GlobalErrorException.setHasError(true);
    //System.out.println("REPORT ERROR :"+header+"-- "+msg);
  }

  public IFortranParserAction getAction() {
    return action;
  }

  public void main_program() throws RecognitionException {
    delegate.main_program();
  }

  /* TODO - implement, needed by FortranParserAction */
  public Token getRightIToken() {
    return null;
  }

  /* TODO - implement, may be needed by FortranParserAction */
  public Token getRhsIToken(int i) {
    return null;
  }

  /**
   * Check for include and end of file. T_INCLUDE is not in the grammar so this
   * method must be called after every statement (and initially at the beginning
   * of program unit file).
   */
  public void checkForInclude() {

    // consume bare T_EOS
    while (input.LA(1) == FortranLexer.T_EOS) {
      input.consume();
    }

    if (input.LA(1) == FortranLexer.T_INCLUDE) {
      String files[];
      input.consume();  // consume T_INCLUDE

      // get include filename from token stream
      files = input.LT(1).getText().split(":");
      action.start_of_file(files[0], files[1]);
      input.consume();  // consume T_INCLUDE_NAME

      // check for empty include file (no statements)
      if (input.LA(1) == FortranLexer.T_EOF) {
        Token tk = input.LT(1);
        input.consume();

        files = tk.getText().split(":");
        action.end_of_file(files[0], files[1]);
      }

      // include acts like a statement so need to see if another include follows
      checkForInclude();
    } else if (input.LA(1) == FortranLexer.T_EOF) {
      Token tk = input.LT(1);
      String[] files = tk.getText().split(":");
      input.consume();
      action.end_of_file(files[0], files[1]);
      // unwind T_EOFs for include files containing includes
      checkForInclude();
    }
  }
} // end FortranParser
