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
package edu.ua.meta;

public class VariableMeta {

  private String type = "";
  private String name = "";
  private String stereotype = null;

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getStereoType()
  {
      return stereotype;
  }

  public void setName(String name) {
    this.name = name;

  }

  public void setType(String type) {
    this.type = type;
  }
  
  public void setStereoType(String stereo){
      this.stereotype = stereo;
}
}
