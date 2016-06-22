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

public class GlobalErrorException {
    private static String error_message = null;
    private static boolean error = false;
    
    public static void setError(String msg){
        error_message = msg;
    }
    
    public static String getError()
    {
        return error_message;
    }
    
    public static void setHasError(boolean err){
        error = true;
    }
    
    public static boolean hasError(){
       return error;
    }
}
