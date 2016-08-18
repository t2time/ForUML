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
