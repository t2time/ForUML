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

import org.antlr.runtime.RecognitionException;

public abstract interface IFortranParser {

  public void initialize(String[] args, String kind, String filename, String path);

  public void main_program() throws RecognitionException;

  public void module() throws RecognitionException;

  public void submodule() throws RecognitionException;

  public void block_data() throws RecognitionException;

  public void subroutine_subprogram() throws RecognitionException;

  public void ext_function_subprogram() throws RecognitionException;

  public IFortranParserAction getAction();

  public boolean hasErrorOccurred();

  public void reportError(RecognitionException re);

  public void checkForInclude();

  public void eofAction();
}
