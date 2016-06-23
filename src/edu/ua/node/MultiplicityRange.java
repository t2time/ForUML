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

public class MultiplicityRange extends UMLNode {

  private List nodes = new ArrayList();

  public void addNodes(UMLNode node) {
    nodes.add(node);
  }

  @Override
  public String getName() {
    return "UML:MultiplicityRange";
  }

  @Override
  public List getChildren() {
    List list = new ArrayList();
    return list;
  }

  @Override
  public int getChildCount() {
    return nodes.size();
  }

  @Override
  public Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "xmi.id", "", "", Long.toString(this.hashCode()));
    attributes.addAttribute("", "lower", "", "", "1");
    attributes.addAttribute("", "upper", "", "", "1");
    return attributes;
  }
}