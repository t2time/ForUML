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

import edu.ua.util.UIDGenerator;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public class AssociationConnectionNode extends UMLNode {

  String name = "";
  List child = new ArrayList();

  @Override
  public String getName() {
    return "UML:Association";
  }

  public void addChildren(UMLNode node) {
    child.add(node);
  }

  @Override
  public List getChildren() {
    return child;
  }

  @Override
  public int getChildCount() {
    return child.size();
  }

  @Override
  public Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "xmi.id", "", "", "" + UIDGenerator.getRandom());
    attributes.addAttribute("", "name", "", "", "");
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "isRoot", "", "", "false");
    attributes.addAttribute("", "isLeaf", "", "", "false");
    attributes.addAttribute("", "isAbstract", "", "", "false");
    attributes.addAttribute("", "isNavigable", "", "", "true");
    return attributes;
  }
}
