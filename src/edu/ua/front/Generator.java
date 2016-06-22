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
package edu.ua.front;

import edu.ua.node.UMLNode;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.xml.sax.helpers.AttributesImpl;
import edu.ua.util.DataWriter;
import edu.ua.util.Fortran2XMI;

public class Generator {

  private String outputPath = "";

  public void setOutput(String path) {
    outputPath = path;
  }

  public String getOutput() {
    return outputPath;
  }

  public void generate(UMLNode contentNode) {
    try {
      String fileName = outputPath;
      AttributesImpl attributes = new AttributesImpl();
      String documentName = "";
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
      DataWriter xmlWriter = new DataWriter(writer);
      xmlWriter.setIndentStep(2);
      xmlWriter.startDocument();
      Fortran2XMI fortran2xmi = new Fortran2XMI(xmlWriter);
      // create an XMI header
      attributes.clear();
      attributes.addAttribute("", "xmi.version", "", "", "1.1");
      attributes.addAttribute("", "xmlns:UML", "", "", "org.omg/standards/UML");
      xmlWriter.startElement("", "XMI", "", attributes);
      // create the XMI header in the XML file
      fortran2xmi.serializeHeader(documentName);
      //ContentNode contentNode = new ContentNode();
      // serialize the UML model into the XML file
      fortran2xmi.serialzeNode(contentNode);
      xmlWriter.endElement("XMI");
      xmlWriter.endDocument();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}