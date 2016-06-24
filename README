==========================
DEVELOPMENT INSTRUCTIONS
==========================
Prior to work on the project, you need to download & install these followings:

1. Java Platform, Standard Edition JDK version 1.7.0 or higher - http://www.oracle.com/technetwork/java/javase/downloads/index.html
2. NetBeans IDE v.8.0.0 or higher - https://netbeans.org/
3. Additional libraries - http://research.te.psu.ac.th/aziz/foruml/lib.zip

Note that you have to extract the file lib.zip on your machine. 

Once you installed the software, you can open the project in NetBean by followings:

1. Launch NetBean

2. Go the the menu File > Open Project, then select the project that you cloned from the repository.

3. On the project, right click on the project and select Properties. In the popup window, select Libraries and select buttons 'Add Library' and 'Create' respectively. You must provide the library name as "LibForUML". 
You have to select the button "Add Jar/Folder..." that will ask you to add Jar files. You should select all Jar files contained in the extracted lib folder. 

4. Now you can work on the project!!

Note that you only need to do steps #2 and #3 in the first time.


=======================
BUILD INSTRUCTIONS
=======================

To build the project from the command line, you need to use 'Ant', which is an automation tool to build the Java application. 
You can download it from http://ant.apache.org/bindownload.cgi. Once, you have Ant on your machine, following these steps:

1. Go to the project directory and type the following:
	
	ant -f build.xml
	
2. If you built successfully, you will see the 2 new folders, including "build" and "dist". The build folder contains all compiled classes and the dist  folder contains the ForUML application and its required libraries. 

Notes: 

* DO not push those 2 directories (build and dist) into the remote repository. We intend to keep only source code and configuration files in the repository.

========================
EXECUTION INSTRUCTIONS
=======================+

To run the application from the command line, go to the dist folder and type the following:

	java -jar "ForUML.jar"
	
To distribute this project, zip up the dist folder (including the lib folder) and distribute the ZIP file.


