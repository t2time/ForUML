/**
 * Copyright (c) 2013 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Aziz Nanthaamornphong, Craig E Rasmussen, Christopher D. Rickett, Jeffrey Overbey
 * 
 * Some portions of this file was previously produced by Los Alamos National Security, LLC see below:
 * 
 * Copyright (c) 2005, 2006 Los Alamos National Security, LLC. This material was
 * produced under U.S. Government contract DE- AC52-06NA25396 for Los Alamos
 * National Laboratory (LANL), which is operated by the Los Alamos National
 * Security, LLC (LANS) for the U.S. Department of Energy. The U.S. Government
 * has rights to use, reproduce, and distribute this software. NEITHER THE
 * GOVERNMENT NOR LANS MAKES ANY WARRANTY, EXPRESS OR IMPLIED, OR ASSUMES ANY
 * LIABILITY FOR THE USE OF THIS SOFTWARE. If software is modified to produce
 * derivative works, such modified software should be clearly marked, so as not
 * to confuse it with the version available from LANL.
 * 
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
