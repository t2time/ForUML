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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TypeConverter {

  public static final String TYPE_PROP_FILE = "typeconfig.properties";
  private static Properties prop = null;
  
  public static String getHref(String t) {
    InputStream is = null;
    String ret = "";
    try {
      is = TypeConverter.class.getResourceAsStream(TYPE_PROP_FILE);
      prop = new Properties();
      prop.load(is);
      ret = prop.getProperty(t.toUpperCase());
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException ex) {
      }
    }
    return ret;
  }
}