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

import java.util.ArrayList;
import java.util.List;
import edu.ua.node.*;
import edu.ua.util.UIDGenerator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ViewMeta {

    private List view = new ArrayList();
    private Hashtable clstbl = null;
    private Hashtable relation = null;
    ArrayList pairedSuperClasses = null;

    public void addClasses(ClassMeta cls) {
        view.add(cls);

    }

    public ViewMeta() {
        clstbl = new Hashtable();
        relation = new Hashtable();
        pairedSuperClasses = new ArrayList();
    }

    public UMLNode getAllItems() {
        ContentNode content = new ContentNode();
        ModelNode modelNode = new ModelNode();
        NamespaceNode namespaceNode = new NamespaceNode();
        modelNode.addNode(namespaceNode);
       

        for (int i = 0; i < view.size(); i++) {
            ClassNode classNode = new ClassNode();
            ClassMeta clsMeta = (ClassMeta) view.get(i);
            if (!clstbl.containsKey(clsMeta.getClassname())) {
                clstbl.put(clsMeta.getClassname(), clsMeta);
            }
            classNode.setId(clsMeta.getId());
            classNode.setName(clsMeta.getClassname());
            List usedClasses = clsMeta.getUses();
            if (usedClasses != null) {
                for (int p = 0; p < usedClasses.size(); p++) {
                    GeneralizableElementNode genelizeNodeElement = new GeneralizableElementNode();
                    GeneralizationNode generalizeNode = new GeneralizationNode();
                    generalizeNode.setChildClass(clsMeta.getClassname());
                   
                    ClassMeta parent = (ClassMeta) usedClasses.get(p);
                    generalizeNode.setParentClass(parent.getClassname());
                    
                    genelizeNodeElement.addNode(generalizeNode);
                    classNode.addUses(genelizeNodeElement);
                    String paired = clsMeta.getClassname()+"::"+parent.getClassname();
                    
                    if(!pairedSuperClasses.contains(paired)){
                         pairedSuperClasses.add(paired);
                         relation.put(generalizeNode.getId(), generalizeNode);
                    }
                }
            }

            List compositeClasses = clsMeta.getComposites();
            if (compositeClasses != null) {
                for (int p = 0; p < compositeClasses.size(); p++) {
                    AssociationEndNodeComposite src = new AssociationEndNodeComposite();
                    src.setAgrregation("composite");
                    AssociationEndParticipant participant = new AssociationEndParticipant();
                    ClassRefNode ref = new ClassRefNode();
                    ref.setId(UIDGenerator.hashCode(clsMeta.getClassname()));
                    participant.addChild(ref);
                    src.addChild(participant);
                    AssociationEndNodeComposite dest = new AssociationEndNodeComposite();
                    dest.setAgrregation("none");
                    ClassMeta dstClass = (ClassMeta) compositeClasses.get(p);
                    AssociationEndParticipant participant2 = new AssociationEndParticipant();
                    ClassRefNode ref2 = new ClassRefNode();
                    ref2.setId(UIDGenerator.hashCode(dstClass.getClassname()));
                    participant2.addChild(ref2);
                    dest.addChild(participant2);
                    AssociationConnection connection = new AssociationConnection();
                    connection.addConenection(src, dest);
                    if(!dstClass.getClassname().equals("logical") && !dstClass.getClassname().equals("character") && !dstClass.getClassname().equals("integer") && !dstClass.getClassname().equals("real") && !dstClass.getClassname().equals("complex") && !dstClass.getClassname().equals("type")){
                   //  System.out.println(clsMeta.getClassname()+"--"+dstClass.getClassname());
                    }
                    AssociationConnectionNode connectionNode = new AssociationConnectionNode();
                    connectionNode.addChildren(connection);         
                    namespaceNode.addNode(connectionNode);
                }
            }
            List variables = clsMeta.getVariables();
            if (variables != null) {
                for (int x = 0; x < variables.size(); x++) {
                    VariableMeta var = (VariableMeta) variables.get(x);
                    AttributeNode attr = new AttributeNode();
                    attr.setName(var.getName());
                    StructuralFeatureMultiNode multiply = new StructuralFeatureMultiNode();
                    attr.addNode(multiply);
                    MultiplicityNode multinode = new MultiplicityNode();
                    multiply.addNode(multinode);
                    MultiplicityRangeNode rangeNode = new MultiplicityRangeNode();
                    multinode.addNode(rangeNode);
                    MultiplicityRange range = new MultiplicityRange();
                    rangeNode.addNode(range);
                    StructuralFeatureTypeNode structural = new StructuralFeatureTypeNode();
                    attr.addNode(structural);
                    if (var.getType().equals("integer")) {
                        DataTypeNode typeNode = new DataTypeNode();
                        typeNode.setType("integer");
                        typeNode.setId(UIDGenerator.getLongTime());
                        structural.addNode(typeNode);
                       // typeNode.setType(var.getType());
                     }else if(var.getType().equals("integer[]")){
                        DataTypeNode typeNode = new DataTypeNode();
                        typeNode.setType("integer[]");
                        typeNode.setId(UIDGenerator.getLongTime());
                        structural.addNode(typeNode);    
                    }else if(var.getType().equals("real")){
                        DataTypeNode typeNode = new DataTypeNode();
                        typeNode.setType("real");
                        typeNode.setId(UIDGenerator.getLongTime());
                        structural.addNode(typeNode);
                    }else if(var.getType().equals("real[]")){
                        DataTypeNode typeNode = new DataTypeNode();
                        typeNode.setType("real[]");
                        typeNode.setId(UIDGenerator.getLongTime());
                        structural.addNode(typeNode);
                    }else if(var.getType().equals("character")){
                        DataTypeNode typeNode = new DataTypeNode();
                        typeNode.setType("character");
                        typeNode.setId(UIDGenerator.getLongTime());
                        structural.addNode(typeNode);
                    }else if(var.getType().equals("character[]")){
                        DataTypeNode typeNode = new DataTypeNode();
                        typeNode.setType("character[]");
                        typeNode.setId(UIDGenerator.getLongTime());
                        structural.addNode(typeNode);
                    }else if(var.getType().equals("logical")){
                        DataTypeNode typeNode = new DataTypeNode();
                        typeNode.setType("logical");
                        typeNode.setId(UIDGenerator.getLongTime());
                        structural.addNode(typeNode);
                    }else if(var.getType().equals("complex")){
                        DataTypeNode typeNode = new DataTypeNode();
                        typeNode.setType("complex");
                        typeNode.setId(UIDGenerator.getLongTime());
                        structural.addNode(typeNode);
                    }else {
                   
                        ClassRefNode refNode = new ClassRefNode();
                        refNode.setId(UIDGenerator.hashCode(var.getType()));
                        structural.addNode(refNode);
                    }
                    
                    if(var.getStereoType() != null){
                        ModelStereotype modelStereo = new ModelStereotype();
                        StereotypeRefNode stereoRefNode = new StereotypeRefNode();
                        String stereoId = UIDGenerator.getLongTime();
                        stereoRefNode.setId(stereoId);
                        modelStereo.addNodes(stereoRefNode);
                        attr.addNode(modelStereo);
                        
                        StereotypeNode stereoTypeNode = new StereotypeNode();
                        stereoTypeNode.setId(stereoId);
                        stereoTypeNode.setName(var.getStereoType());
                        StereoBaseClassNode stereoBase = new StereoBaseClassNode();
                        stereoTypeNode.addNodes(stereoBase);
                        namespaceNode.addNode(stereoTypeNode);
        
                    }
                    
                    classNode.addVariable(attr);
                }
            }
            //Functions
            List functions = clsMeta.getFunctions();
            if (functions != null) {

                for (int k = 0; k < functions.size(); k++) {
                    FunctionMeta funcMeta = (FunctionMeta) functions.get(k);
                    FunctionNode funcNode = new FunctionNode();
                    funcNode.setName(funcMeta.getName());
                    ArrayList params = funcMeta.getParameters();
                    BehavioralFeatureNode beh = new BehavioralFeatureNode();
                    //Return type
                    ParameterNode paramNode = new ParameterNode();
                    paramNode.setKind("return");
                    beh.addNode(paramNode);
                    //Arguments
                    for (int xx = 0; xx < params.size(); xx++) {
                        
                        String paramName = ((ParameterMeta) params.get(xx)).getName();
                        String parmType = ((ParameterMeta) params.get(xx)).getType();
                        ParameterNode paramNode2 = new ParameterNode();
                        paramNode2.setName(paramName);
                        paramNode2.setKind("in");
                        ParameterTypeNode paramType2 = new ParameterTypeNode();
                        DataTypeNode dataTypeNode2 = new DataTypeNode();
                        dataTypeNode2.setType(parmType);
                        paramType2.addNode(dataTypeNode2);
                        paramNode2.addNode(paramType2);
                        beh.addNode(paramNode2);
                    }
                    funcNode.addParameter(beh);
                    classNode.addFunction(funcNode);
                }
            }
            //Subroutine2
            List subroutines2 = clsMeta.getSubroutine2();
            if (subroutines2 != null) {
                for (int n = 0; n < subroutines2.size(); n++) {
                    SubroutineMeta subMeta = (SubroutineMeta) subroutines2.get(n);
                    FunctionNode subNode = new FunctionNode();
                    subNode.setName(subMeta.getName());
                    
                    SubroutineMeta subroutineTemp = clsMeta.getSubroutineTempMeta(subMeta.getName());
                    if(subroutineTemp != null){
                    
                      BehavioralFeatureNode beh = new BehavioralFeatureNode();
                      subNode.addParameter(beh);
                      ArrayList parameterList = subroutineTemp.getParameters();
                      if(parameterList != null){
                        for(int k = 0; k < parameterList.size(); k++){
                       
                          ParameterMeta param = (ParameterMeta) parameterList.get(k);
                          String paramName = param.getName(); 
                          String paramType = param.getType();
                          ParameterNode paramNode = new ParameterNode();
                          paramNode.setName(paramName);
                          paramNode.setKind(param.getDirection());
                          ParameterTypeNode paramTypeNode = new ParameterTypeNode();
                          DataTypeNode typeNode = new DataTypeNode();
                          typeNode.setType(paramType);
                           typeNode.setId(UIDGenerator.getLongTime());
                          paramTypeNode.addNode(typeNode);
                          paramNode.addNode(paramTypeNode);
                          beh.addNode(paramNode);
                       }
                      }
                    }           
                    if(subMeta.getStereoType() != null){
                        
                        ModelStereotype modelStereo = new ModelStereotype();
                        StereotypeRefNode stereoRefNode = new StereotypeRefNode();
                        String stereoId = UIDGenerator.getLongTime();
                        stereoRefNode.setId(stereoId);
                        modelStereo.addNodes(stereoRefNode);
                        subNode.addParameter(modelStereo);
                        
                        StereotypeNode stereoTypeNode = new StereotypeNode();
                        stereoTypeNode.setId(stereoId);
                        stereoTypeNode.setName(subMeta.getStereoType());
                        StereoBaseClassNode stereoBase = new StereoBaseClassNode();
                        stereoTypeNode.addNodes(stereoBase);
                        namespaceNode.addNode(stereoTypeNode);
                      }
                    classNode.addSubroutine(subNode);
                }
            }
            //Subroutine
            List subroutines = clsMeta.getSubroutine();
            if (subroutines != null) {
                for (int n = 0; n < subroutines.size(); n++) {
                    SubroutineMeta subMeta = (SubroutineMeta) subroutines.get(n);
                    FunctionNode subNode = new FunctionNode();
                    subNode.setName(subMeta.getName());
                    BehavioralFeatureNode beh = new BehavioralFeatureNode();
                    ParameterNode paramNode = new ParameterNode();
                    paramNode.setKind("return");
                    beh.addNode(paramNode);
                    ArrayList params2 = subMeta.getParameters();
                    for (int yy = 0; yy < params2.size(); yy++) {

                        String paramName = ((ParameterMeta) params2.get(yy)).getName();
                        String parmType = ((ParameterMeta) params2.get(yy)).getType();
                        
                        if (!parmType.equals("")) {

                            ParameterNode paramNode3 = new ParameterNode();
                            paramNode3.setName(paramName);
                            paramNode3.setKind("in");
                            ParameterTypeNode paramType3 = new ParameterTypeNode();
                            DataTypeNode dataTypeNode3 = new DataTypeNode();
                            dataTypeNode3.setType(parmType);
                            paramType3.addNode(dataTypeNode3);
                            paramNode3.addNode(paramType3);
                            beh.addNode(paramNode3);
                        }
                    }
                    subNode.addParameter(beh);
                    classNode.addSubroutine(subNode);
                }
            }
            namespaceNode.addNode(classNode);
        }

        Set set = relation.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            GeneralizationNode rel = (GeneralizationNode) entry.getValue();
            GeneralizationEntryNode generalizeentry = new GeneralizationEntryNode();
            generalizeentry.setId(rel.getId());
            GeneralizationChildNode childNode = new GeneralizationChildNode();
            ClassRefNode childRefNode = new ClassRefNode();
            ClassMeta tmp = (ClassMeta) clstbl.get(rel.getChildClass());
            childRefNode.setId(tmp.getId());
            childNode.addNode(childRefNode);
            generalizeentry.addNode(childNode);
            GeneralizationParentNode parentNode = new GeneralizationParentNode();
            ClassRefNode parentRefNode = new ClassRefNode();
            tmp = (ClassMeta) clstbl.get(rel.getParentClass());
            if (tmp != null) {
                parentRefNode.setId(tmp.getId());
                parentNode.addNode(parentRefNode);
                generalizeentry.addNode(parentNode);
                namespaceNode.addNode(generalizeentry);
            }
        }
        content.addNode(modelNode);
        return content;
    }
}
