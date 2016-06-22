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

import java.util.List;
import org.xml.sax.Attributes;


public abstract class UMLNode {

  public abstract String getName();

  public abstract List getChildren();

  public abstract int getChildCount();

  public String getText() {
    return null;
  }

  public abstract Attributes getAttributes();
}
