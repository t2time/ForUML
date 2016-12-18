[This document is formatted with GitHub-Flavored Markdown.   ]:#
[For better viewing, including hyperlinks, read it online at ]:#
[https://github.com/time2time/ForUML/blob/master/README.md   ]:#

<a name="top"> </a>

ForUML
======

* [Overview](#overview)
* [Getting Started](#getting-started)
* [Developer Instructions](#developer-instructions)
 - [Build Instructions](#build-instructions)
 - [Execution Instructions](#execution-instructions)
* [Publications](#publications)

Overview
========
ForUML is a reverse engineering tool that generates Unified Modeling Language (UML) class
diagrams from object-oriented Fortran programs.  ForUML understands and diagrams Fortran
class relationships, including inheritance and aggregation. ForUML also detects and 
depicts operator overloading, constructors, and parallel data structures (coarrays).

ForUML generates a formal description of each diagram in the XML Metadata Interchange (XMI) 
format and launches [ArgoUML] to display the resulting class diagrams.  Launching
ArgoUML gives users the option to edit ForUML diagrams and to export the diagrams
to a graphics file format for inclusion in other documents.

Getting Started
===============
Users not wishing to contribute code to the ForUML project may download our
end-user distribution from the [ForUML project page] and view an 
[online video demonstration] of using ForUML.

Developer Instructions
======================
Prior to work on the project, please download and install the following:

1. [Java SE Development Kit] version 1.7.0 or higher - 
2. [NetBeans IDE] v.8.0.0 or higher
3. [Additional libraries] \(lib.zip) available on the project web site.

Extract the lib.zip file on your machine. 

After installing the above packages, open the ForUML project in NetBeans as follows:

1. Launch NetBean
2. Go the the menu File > Open Project, then select the project that you cloned from the repository.
3. On the project, right click on the project and select Properties. In the popup window, select Libraries and select buttons 'Add Library' and 'Create' respectively. You must provide the library name as "LibForUML". 
You have to select the button "Add Jar/Folder..." that will ask you to add Jar files. You should select all Jar files contained in the extracted lib folder. 
4. Now you can work on the project!

You only need to do steps #2 and #3 above once.

Build Instructions
------------------
To build the project from the command line, use 'Ant', an automation tool, to build the Java application. 
You can download Ant [here](http://ant.apache.org/bindownload.cgi). Once you have Ant, following these steps:

1. Go to the project directory and type the following:
```
   ant -f build.xml
```
2. If you built successfully, you will see the 2 new folders, including `build` and `dist`. The build folder contains all compiled classes and the `dist` folder contains the ForUML application and its required libraries. 

Notes: 

* DO not push those 2 directories (`build` and `dist`) into the remote repository. We intend to keep only source code and configuration files in the repository.

Execution Instructions
------------------

To run the application from the command line, go to the dist folder and type the following:
```
   java -jar "ForUML.jar"
```
To distribute this project, zip up the `dist` folder (including the `lib` folder) and distribute the ZIP file.

Publications
============
If you find ForUML useful, please cite the following paper in publications that employ ForUML-generated diagrams: 

Nanthaamornphong, Aziz, Jeffrey Carver, Karla Morris, and Salvatore Filippone. 
"Extracting uml class diagrams from object-oriented fortran: Foruml." 
[Scientific Programming 2015 (2015): 1.](http://dl.acm.org/citation.cfm?id=2814697)

For other releated publications, please visit the [publications page] of Prof. Aziz Nanthaamornphong.

[Java SE Development Kit]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[publications page]: http://research.te.psu.ac.th/aziz/pub.htm
[online video demonstration]: http://research.te.psu.ac.th/aziz/VDO.htm 
[ForUML project page]: http://research.te.psu.ac.th/aziz/foruml.htm
[ArgoUML]: http://argouml.tigris.org/
[NetBeans IDE]: https://netbeans.org/
[Additional libraries]: http://research.te.psu.ac.th/aziz/foruml/lib.zip
