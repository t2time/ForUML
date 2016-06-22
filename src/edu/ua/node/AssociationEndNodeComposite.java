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

public class AssociationEndNodeComposite extends UMLNode {

  String name = "";
  String aggregation = "composite";
  List child = new ArrayList();
  String id = "";

  @Override
  public String getName() {
    return "UML:AssociationEnd";
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public List getChildren() {

    return child;
  }

  public void addChild(UMLNode node) {
    child.add(node);
  }

  @Override
  public int getChildCount() {
    return child.size();
  }

  public void setAgrregation(String agrregation) {
    aggregation = agrregation;
  }

  @Override
  public Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "xmi.id", "", "", "" + UIDGenerator.getRandom());
    attributes.addAttribute("", "visibility", "", "", "public");
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "isNavigable", "", "", "true");
    attributes.addAttribute("", "ordering", "", "", "unordered");
    attributes.addAttribute("", "aggregation", "", "", aggregation);
    attributes.addAttribute("", "targetScope", "", "", "instance");
    attributes.addAttribute("", "changeability", "", "", "changeable");
    return attributes;

  }
}
