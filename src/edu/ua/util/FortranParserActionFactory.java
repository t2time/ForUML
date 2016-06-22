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

import java.lang.reflect.Constructor;

public class FortranParserActionFactory {

  public static IFortranParserAction newAction(String[] args, IFortranParser parser, String kind, String filename) {
    IFortranParserAction action = null;
    // Look up the class name. Could be FortranParserActionPrint, or
    // FortranParserActionNull, or maybe something else.
    try {
      Constructor[] cons = Class.forName(kind).getDeclaredConstructors();
      for (int i = 0; i < cons.length; i++) {
        Class[] types = cons[i].getParameterTypes();
        if (types.length == 3
                & types[0] == String[].class
                & types[1] == IFortranParser.class
                & types[2] == java.lang.String.class) {
          Object[] actionArgs = {args, parser, filename};
          action = (IFortranParserAction) cons[i].newInstance(actionArgs);
          break;
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return action;
  }
}
