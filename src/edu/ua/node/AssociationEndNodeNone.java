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

public class AssociationEndNodeNone extends UMLNode {

  String name = "";

  @Override
  public String getName() {
    return "UML:AssociationEnd";
  }

  @Override
  public List getChildren() {
    List child = new ArrayList();
    return child;
  }

  @Override
  public int getChildCount() {
    return 0;
  }

  @Override
  public Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "xmi.id", "", "", "" + UIDGenerator.getRandom());
    attributes.addAttribute("", "visibility", "", "", "public");
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "isNavigable", "", "", "true");
    attributes.addAttribute("", "ordering", "", "", "unordered");
    attributes.addAttribute("", "aggregation", "", "", "none");
    attributes.addAttribute("", "targetScope", "", "", "instance");
    attributes.addAttribute("", "changeability", "", "", "changeable");
    return attributes;

  }
}
