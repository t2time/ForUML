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
