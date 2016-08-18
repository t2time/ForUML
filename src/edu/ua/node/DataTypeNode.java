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
package edu.ua.node;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.helpers.AttributesImpl;

public class DataTypeNode extends UMLNode {

  private List nodes = new ArrayList();
  private String type = "";
  private String id = "";
  //private String hrefex = "";

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
    //hrefex = TypeConverter.getHref(type);

  }
  
  public void setId(String id)
  {
      this.id = id;
  }
  
  public String getId(){
      return this.id;
  }
  
  public void addNode(UMLNode node) {
    nodes.add(node);
  }

  @Override
  public String getName() {
    return "UML:DataType";
  }

  @Override
  public List getChildren() {
    return nodes;
  }

  @Override
  public int getChildCount() {
    return nodes.size();
  }

  @Override
  public org.xml.sax.Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    //attributes.addAttribute("", "href", "", "", "http://argouml.org/profiles/uml14/default-uml14.xmi#-84-17--56-5-43645a83:11466542d86:-8000:000000000000087C");
    attributes.addAttribute("", "xmi.id", "", "", id);
    attributes.addAttribute("", "name", "", "", this.type);
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "isRoot", "", "", "false");
    attributes.addAttribute("", "isLeaf", "", "", "false");
    attributes.addAttribute("", "isAbstract", "", "", "false");
    return attributes;
  }
}
