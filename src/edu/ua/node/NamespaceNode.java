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

public class NamespaceNode extends UMLNode {

  private List cls = new ArrayList();

  public void addNode(UMLNode node) {
    cls.add(node);
  }

  @Override
  public String getName() {
    return "UML:Namespace.ownedElement";
  }

  @Override
  public List getChildren() {
    return cls;
  }

  @Override
  public int getChildCount() {
    return cls.size();
  }

  @Override
  public Attributes getAttributes() {
    AttributesImpl atb = new AttributesImpl();
    return atb;
  }
}
