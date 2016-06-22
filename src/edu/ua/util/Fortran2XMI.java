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
package edu.ua.util;

import edu.ua.node.UMLNode;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class Fortran2XMI {

  private XMLWriter xmlWriter;
  private ResourceBundle resource;
  private ResourceBundle model2UMLNodeReserouce;

  public Fortran2XMI(XMLWriter xmlWriter) {
    this.xmlWriter = xmlWriter;
  }

  public void serializeHeader(String documentName) throws SAXException {
    AttributesImpl attributes = new AttributesImpl();
    xmlWriter.startElement("", "XMI.header", "", attributes);

    attributes.addAttribute("", "name", "", "", "UML");
    attributes.addAttribute("", "version", "", "", "1.3");
    attributes.addAttribute("", "href", "", "", "UML.xml");

    xmlWriter.startElement("", "XMI.metaModel", "", attributes);

    xmlWriter.endElement("XMI.metaModel");

    attributes.clear();
    attributes.addAttribute("", "name", "", "", documentName);
    attributes.addAttribute("", "version", "", "", "1");
    attributes.addAttribute("", "href", "", "", documentName);

    xmlWriter.startElement("", "XMI.model", "", attributes);
    xmlWriter.endElement("XMI.model");
    xmlWriter.endElement("XMI.header");
  }

  public void serialzeNode(UMLNode node) throws SAXException {
    String elementName;
    List children;
    ListIterator iterator;
    String childElementName = null;
    elementName = node.getName();
    Attributes attributes = node.getAttributes();
    xmlWriter.startElement("", elementName, "", attributes);
    children = node.getChildren();
    iterator = children.listIterator();
    while (iterator.hasNext()) {

      Object object = iterator.next();
      UMLNode childNode = (UMLNode) object;

      if (childNode.getChildCount() != 0) {
        serialzeNode(childNode);
      } else {
        childElementName = childNode.getName();
        attributes = childNode.getAttributes();
        xmlWriter.startElement("", childElementName, "", attributes);
        xmlWriter.endElement(childElementName);
      }
    }
    xmlWriter.endElement(elementName);
  }
}
