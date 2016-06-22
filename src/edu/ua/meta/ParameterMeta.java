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

public class ParameterMeta {

  String name = "";
  String type = "";
  String direction = "";

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }
  
  public void setDirection(int di){
   
    if(di == 602){
      this.direction = "inout";
    }else if(di == 600){
      this.direction = "in";
    }else{
      this.direction = "out";
    }
  }
  
  public String getDirection(){
   return this.direction;
  }
  
}
