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

public class DataTypeNode extends UMLNode {

  private List nodes = new ArrayList();
  private String type = "";
  private String id = "";
  //private String hrefex = "";

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
    //hrefex = TypeConverter.getHref(type);

  }
  
  public void setId(String id)
  {
      this.id = id;
  }
  
  public String getId(){
      return this.id;
  }
  
  public void addNode(UMLNode node) {
    nodes.add(node);
  }

  @Override
  public String getName() {
    return "UML:DataType";
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
    //attributes.addAttribute("", "href", "", "", "http://argouml.org/profiles/uml14/default-uml14.xmi#-84-17--56-5-43645a83:11466542d86:-8000:000000000000087C");
    attributes.addAttribute("", "xmi.id", "", "", id);
    attributes.addAttribute("", "name", "", "", this.type);
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "isRoot", "", "", "false");
    attributes.addAttribute("", "isLeaf", "", "", "false");
    attributes.addAttribute("", "isAbstract", "", "", "false");
    return attributes;
  }
}
