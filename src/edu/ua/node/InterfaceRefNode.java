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

public class InterfaceRefNode extends UMLNode {

  private String id = "";
  private List node = new ArrayList();

  public InterfaceRefNode() {
    id = Long.toString(this.hashCode());
  }

  public void addNodes(UMLNode n) {
    node.add(n);
  }

  @Override
  public String getName() {
    return "UML:Interface";
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
    attributes.addAttribute("", "xmi.idref", "", "", id);
    return attributes;
  }
}
