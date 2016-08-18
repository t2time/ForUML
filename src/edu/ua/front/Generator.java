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