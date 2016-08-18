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
package edu.ua.front;

import java.io.File;
import java.util.ArrayList;

class Filewalker {
//     public void walk( String path ) {
//
//        File root = new File( path );
//        File[] list = root.listFiles();
//
//        if (list == null) return;
//
//        for ( File f : list ) {
//            if ( f.isDirectory() ) {
//                walk( f.getAbsolutePath() );
//                System.out.println( "Dir:" + f.getAbsoluteFile() );
//            }
//            else {
//                System.out.println( "File:" + f.getAbsoluteFile() );
//            }
//        }
//    }
    
    ArrayList<String> listPathfile = new ArrayList<String>();

    public ArrayList<String> walk(String path) {

        File dir = new File(path);
        File[] list = dir.listFiles();

        if (list == null) {
            return walk(path);
        }
        if (dir.exists()) {

            for (File f : list) {
                if (f.isDirectory()) {
                    walk(f.getAbsolutePath());
                    System.out.println( "Dir:" + f.getAbsoluteFile() );
                } else if (!f.isDirectory() && f.getName().endsWith(".f90")) {
                    listPathfile.add(f.getAbsoluteFile().toString());
                    System.out.println( "File:" + f.getAbsoluteFile() );
                }
            }
        }
        return listPathfile;
    }

}
