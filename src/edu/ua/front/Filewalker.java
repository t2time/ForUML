/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ua.front;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author t2time
 */
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
