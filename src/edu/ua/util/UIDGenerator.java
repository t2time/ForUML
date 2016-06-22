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

import java.util.Date;
import java.util.Random;

public class UIDGenerator {

  public static String hashCode(String a) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < a.length(); i++) {
      char c = a.charAt(i);
      int n = (int) c;
      builder.append(n);
    }
    return builder.toString();
  }

  public static int getRandom() {
    Random rand = new Random();
    return rand.nextInt();
  }
  
  public static String getLongTime(){
      Date d = new Date();
      return d.getTime()+"-"+ getRandom();
  }
}
