/*
Copyright (c) 2016, Aziz Nanthaamornphong and Anawat Leathongkum

Redistribution and use in source and binary forms, with or without modification, are 
permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of 
conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of 
conditions and the following disclaimer in the documentation and/or other materials 
provided with the distribution.

3. Neither the names of the copyright holders nor the names of its contributors may be 
used to endorse or promote products derived from this software without specific prior 
written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
