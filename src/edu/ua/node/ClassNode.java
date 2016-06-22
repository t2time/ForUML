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

public class ClassNode extends UMLNode {

  private String name = "";
  private String visibility = "Public";
  private List child = new ArrayList();
  ClassifierFeatureNode featureNode = new ClassifierFeatureNode();
  private String id = "";

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ClassNode() {
    child.add(featureNode);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addVariable(UMLNode node) {
    featureNode.addNode(node);
  }

  public void addFunction(UMLNode node) {
    featureNode.addNode(node);
  }

  public void addSubroutine(UMLNode node) {
    featureNode.addNode(node);
  }

  public void addUses(UMLNode node) {
    child.add(node);
  }

  public void addComposites(UMLNode node) {
    child.add(node);
  }

  @Override
  public String getName() {
    // return this.name;
    return "UML:Class";
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
    attributes.addAttribute("", "xmi.id", "", "", id);
    attributes.addAttribute("", "name", "", "", this.name);
    attributes.addAttribute("", "visibility", "", "", this.visibility);
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "isRoot", "", "", "false");
    attributes.addAttribute("", "isLeaf", "", "", "false");
    attributes.addAttribute("", "isAbstract", "", "", "false");
    attributes.addAttribute("", "isActive", "", "", "false");
    return attributes;
  }
}
