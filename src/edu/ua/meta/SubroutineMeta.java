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

import java.util.*;

public class SubroutineMeta {

  private String name;
  private ArrayList parameters = new ArrayList();
  private int currentParam = 0;
  private String stereoType = null;

  public void setName(String name) {
    this.name = name;

  }

  public String getName() {
    return this.name;
  }

  public void addParameters(ParameterMeta param) {
    parameters.add(param);

  }

  public ArrayList getParameters() {
    return this.parameters;
  }

  public ParameterMeta getNextParameter() {
    ParameterMeta ret = null;

    if (parameters.size() > currentParam) {
      ret = (ParameterMeta) parameters.get(currentParam);
      currentParam++;
    }
    return ret;
  }
  
    public void setStereoType(String stereo){
        this.stereoType = stereo;
}
    
    public String getStereoType(){
        return this.stereoType;
    }
}
