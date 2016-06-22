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
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public class StereotypeNode extends UMLNode {

  private List node = new ArrayList();
  private String name = "";
  private String id = "";

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void addNodes(UMLNode n) {
    node.add(n);
  }

  @Override
  public String getName() {
    return "UML:Stereotype";
  }

  public void setName(String name){
      this.name = name;
  }
  
  @Override
  public List getChildren() {
    return node;
  }

  @Override
  public int getChildCount() {
    return node.size();
  }

  @Override
  public Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "xmi.id", "", "", this.id);
    attributes.addAttribute("", "name", "", "", this.name);
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "isRoot", "", "", "false");
    attributes.addAttribute("", "isLeaf", "", "", "false");
    attributes.addAttribute("", "isAbstract", "", "", "false");
    return attributes;
  }
}
