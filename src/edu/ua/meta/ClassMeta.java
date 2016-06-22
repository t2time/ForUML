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

import edu.ua.util.UIDGenerator;
import java.util.*;

public class ClassMeta {

  private String filename = "";
  private String classname = "";
  private ArrayList compositeNames = new ArrayList();
  private ArrayList functions = new ArrayList();
  private ArrayList subroutine = new ArrayList();
  private ArrayList uselists = new ArrayList();
  private ArrayList variables = new ArrayList();
  private ArrayList compositelists = new ArrayList();
  private ArrayList subroutine2 = new ArrayList();
  private ArrayList functionNames = new ArrayList();
 // private ArrayList tmpSubroutine = new ArrayList();
  private Hashtable tmpSubroutine = new Hashtable();
  private ArrayList tmpSubroutineName = new ArrayList();
  private int currentVariable = 0;
  private ArrayList usedClassNames = new ArrayList();
  private String id = Long.toString(this.hashCode());

  public String getId() {
    
    String uid = UIDGenerator.hashCode(classname);
    return uid;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void addCompositeNames(String a) {
    this.compositeNames.add(a);
  }

  public ArrayList getCompositenames() {
    return compositeNames;
  }

  public void addVariables(VariableMeta v) {
    variables.add(v);
  }

  public ArrayList getVariables() {
    return variables;
  }

  public void addFunctions(FunctionMeta f) {
    functions.add(f);
  }

  public ArrayList getFunctions() {
    return this.functions;
  }

  public void addSubroutine2(SubroutineMeta s) {
    if (!functionNames.contains(s.getName())) {
      this.subroutine2.add(s);
      functionNames.add(s.getName());
      
    }
  }

  public void addSubroutine(SubroutineMeta s) {
    this.subroutine.add(s);
  }
  
  public void addTempSubroutine(SubroutineMeta s){
  
    
   if(!this.tmpSubroutine.containsKey(s.getName().toLowerCase())){
    
     this.tmpSubroutine.put(s.getName().toLowerCase(), s);      
   }
  }
  
  public SubroutineMeta getSubroutineTempMeta(String methodName){
    
    if(this.tmpSubroutine.containsKey(methodName.toLowerCase())){
      
      return (SubroutineMeta) this.tmpSubroutine.get(methodName.toLowerCase());
    }else{
      return null;
    }
    
  }
  
  public void changeStereoType(String methodName, String stereoType){
    for(int i=0; i< this.subroutine2.size();i++){
     if(((SubroutineMeta)this.subroutine2.get(i)).getName().equalsIgnoreCase(methodName)){
       ((SubroutineMeta)this.subroutine2.get(i)).setStereoType(stereoType);
       break;
    }
    }
  }

  public ArrayList getSubroutine2() {
    return this.subroutine2;
    
  }

  public ArrayList getSubroutine() {
    return this.subroutine;
  }

  public void addComposites(ClassMeta cl) {
    compositelists.add(cl);
  }

  public void addUses(ClassMeta cl) {
    if(!usedClassNames.contains(cl.getClassname())){
      usedClassNames.add(cl.getClassname());
      uselists.add(cl);   
    } 
  }

  public ArrayList getComposites() {
    return this.compositelists;
  }

  public ArrayList getUses() {
    return this.uselists;
  }

  public String getClassname() {
    return classname;
  }

  public String getFilename() {
    return filename;
  }

  public void setClassname(String classname) {
    this.classname = classname;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public VariableMeta getNextVariable() {
    VariableMeta var = null;
    if (currentVariable < variables.size()) {
      var = (VariableMeta) variables.get(currentVariable);
      currentVariable++;
    }
    return var;
  }
}
