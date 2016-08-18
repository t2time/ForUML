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
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public class FunctionNode extends UMLNode {

  private String name = "";
  private String visibility = "public";
  private String returnType = "";
  private List parameters = new ArrayList();

  public void setName(String name) {
    this.name = name;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public String getReturnType() {
    return returnType;
  }

  public void addParameter(UMLNode node) {
    parameters.add(node);
  }

  @Override
  public String getName() {
    return "UML:Operation";
  }

  @Override
  public List getChildren() {
    return parameters;
  }

  @Override
  public int getChildCount() {
    return parameters.size();
  }

  @Override
  public Attributes getAttributes() {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute("", "name", "", "", this.name);
    //attributes.addAttribute("", "type", "","", this.returnType);
    attributes.addAttribute("", "visibility", "", "", this.visibility);
    attributes.addAttribute("", "isSpecification", "", "", "false");
    attributes.addAttribute("", "ownerScoep", "", "", "instance");
    attributes.addAttribute("", "isQuery", "", "", "false");
    attributes.addAttribute("", "concurency", "", "", "sequential");
    attributes.addAttribute("", "isRoot", "", "", "false");
    attributes.addAttribute("", "isLeaf", "", "", "false");
    attributes.addAttribute("", "isAbstract", "", "", "false");



    return attributes;
  }
}
