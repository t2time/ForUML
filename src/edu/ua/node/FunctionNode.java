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

public class FunctionNode extends UMLNode {

  private String name = "";
  private String visibility = "public";
  private String returnType = "";
  private List parameters = new ArrayList();

  public void setName(String name) {
    this.name = name;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public String getReturnType() {
    return returnType;
  }

  public void addParameter(UMLNode node) {
    parameters.add(node);
  }

  @Override
  public String getName() {
    return "UML:Operation";
  }

  @Override
  public List getChildren() {
    return parameters;
  }

  @Override
  public int getChildCount() {
    return parameters.size();
  }

  @Override
  public Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "name", "", "", this.name);
    //attributes.addAttribute("", "type", "","", this.returnType);
    attributes.addAttribute("", "visibility", "", "", this.visibility);
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "ownerScoep", "", "", "instance");
    attributes.addAttribute("", "isQuery", "", "", "false");
    attributes.addAttribute("", "concurency", "", "", "sequential");
    attributes.addAttribute("", "isRoot", "", "", "false");
    attributes.addAttribute("", "isLeaf", "", "", "false");
    attributes.addAttribute("", "isAbstract", "", "", "false");



    return attributes;
  }
}
