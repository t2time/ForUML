/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ua.front;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author t2time
 */
public class DragListener implements DropTargetListener {

    JTextArea ta = new JTextArea();
    JList fl = new JList();

    public DragListener(JTextArea f, JList fileList) {
        ta = f;
        fl = fileList;

    }

    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.println("Drag Enter");
    }

    public void dragExit(DropTargetEvent dte) {
        System.out.println("Drag Exit");
    }

    public void dragOver(DropTargetDragEvent dtde) {
        System.out.println("Drag Over");
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
        System.out.println("Drop Action Changed");
    }

    public void drop(DropTargetDropEvent dtde) {
        try {
            // Ok, get the dropped object and try to figure out what it is
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (int i = 0; i < flavors.length; i++) {
                System.out.println("Possible flavor: " + flavors[i].getMimeType());
                // Check for file lists specifically
                if (flavors[i].isFlavorJavaFileListType()) {
                    // Great!  Accept copy drops...
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    ta.setText("Successful file list drop.\n\n");
                    
                    DefaultListModel listModel = new DefaultListModel();
                    
                    
                    // And add the list of file names to our text area
                    java.util.List list = (java.util.List) tr.getTransferData(flavors[i]);
                    System.out.println("Total Files::" + list.size());
                    for (int j = 0; j < list.size(); j++) {
                        ta.append(list.get(j) + "\n");
                        
                        //check file or folder 
                        if(list.get(j).toString().endsWith(".f90")){
                          listModel.addElement(list.get(j));
                          
                        }else{
                            
                          Filewalker fw = new Filewalker();
                      
                          ArrayList<String> listPathfile = fw.walk(list.get(j).toString());
                          
                          for (int k = 0; k < listPathfile.size(); k++) {
                              listModel.addElement(listPathfile.get(k)+ "\n"); 
                          }
                           
                        }
                        
                    }
                    
                    this.fl.setModel(listModel);
                    

                    // If we made it this far, everything worked.
                    dtde.dropComplete(true);
                    return;
                } // Ok, is it another Java object?
                else if (flavors[i].isFlavorSerializedObjectType()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    ta.setText("Successful text drop.\n\n");
                    Object o = tr.getTransferData(flavors[i]);
                    ta.append("Object: " + o);
                    dtde.dropComplete(true);
                    return;
                } // How about an input stream?
                else if (flavors[i].isRepresentationClassInputStream()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    ta.setText("Successful text drop.\n\n");
                    ta.read(new InputStreamReader(
                            (InputStream) tr.getTransferData(flavors[i])),
                            "from system clipboard");
                    dtde.dropComplete(true);
                    return;
                }
            }
            // Hmm, the user must not have dropped a file list
            System.out.println("Drop failed: " + dtde);
            dtde.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }
}
