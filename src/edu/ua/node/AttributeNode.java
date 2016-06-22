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

public class AttributeNode extends UMLNode {

  private String name = "";
  private String visibility = "public";
  private List nodes = new ArrayList();

  public void addNode(UMLNode node) {
    nodes.add(node);
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return "UML:Attribute";
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
    attributes.addAttribute("", "xmi.id", "", "", Long.toString(this.hashCode()));
    attributes.addAttribute("", "name", "", "", name);
    attributes.addAttribute("", "visibility", "", "", "public");
    attributes.addAttribute("", "ownerScope", "", "", "instance");
    attributes.addAttribute("", "changeability", "", "", "changeable");
    attributes.addAttribute("", "targetScope", "", "", "instance");
    attributes.addAttribute("", "isSpecification", "", "", "false");
    return attributes;
  }
}
