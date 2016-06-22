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

public class ParameterNode extends UMLNode {

  public String name = "";
  public String type = "";
  public String visibility = "public";
  public String kind = "";
  private List nodes = new ArrayList();

  public void addNode(UMLNode node) {
    nodes.add(node);
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  @Override
  public String getName() {
    return "UML:Parameter";
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
  public Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "xmi.id", "", "", Long.toString(this.hashCode()));
    attributes.addAttribute("", "name", "", "", this.name);
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "kind", "", "", this.kind);
    return attributes;
  }
}
