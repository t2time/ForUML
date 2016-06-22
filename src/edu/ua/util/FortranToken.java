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
package edu.ua.util;

import org.antlr.runtime.*;

public class FortranToken extends CommonToken {

  protected String whiteText = "";

  public FortranToken(CharStream input, int type, int channel,
          int start, int stop) {
    super(input, type, channel, start, stop);
  }

  public FortranToken(int type) {
    super(type);
// 		this.input = input;
  }

// 	public FortranToken(int type, String text, CharStream input) {
  public FortranToken(int type, String text) {
    super(type, text);
// 		this.input = input;
  }

// 	public FortranToken(Token token, CharStream input) {
  public FortranToken(Token token) {
    super(token);
// 		this.input = input;
  }

  public String getWhiteText() {
    return whiteText;
  }

  public void setWhiteText(String text) {
    whiteText = text == null ? "" : text;
  }

  public FortranStream getInput() {
    return (FortranStream) this.input;
  }
}
