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
package edu.ua.node;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.helpers.AttributesImpl;

public class GeneralizationNode extends UMLNode{
    
     private List nodes = new ArrayList();
     private String id = "";
     
     private String child = "";
     private String parent = "";
    
     
     public void setChildClass(String cls){
         child = cls;
     }
     
     public String getChildClass(){
         return child;
     }
     
     public void setParentClass(String cls){
         parent = cls;
     }
     
     public String getParentClass(){
         return parent;
     }
     
     public GeneralizationNode(){
         id = Long.toString(this.hashCode());
     }
    
    public void addNode(UMLNode node){
        nodes.add(node);
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return  Long.toString(this.hashCode());
    }
    
    @Override
    public String getName() {
        return "UML:Generalization";
    }

    @Override
    public List getChildren() {
        return nodes;
    }

    @Override
    public int getChildCount() {
       return nodes.size();
    }

    @Override
    public org.xml.sax.Attributes getAttributes() {
        AttributesImpl attributes = new AttributesImpl();
         attributes.addAttribute("", "xmi.idref", "", "", id);
        return attributes;
    }
    
}
