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
