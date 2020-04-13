# Tafelhilfe Connector
With the Tafelhilfe Connector it is possible to post requests for help or support via the website tafelhilfe.de to known platforms, so that non-profit organisations in need of help can find support quickly and without the hassle of registering to numerable platforms.

## Contributions
This project belongs to the non-profit project tafelhilfe.de that was founded during the German hackathon #wirvsvirus tackeling the challenges caused by Corona-crisis.


## Getting Started

This repository contains the source code of a maven web application. To run the application, you need a webserver like tomcat and need to deloy the war-file in the target-directory to the server. If you want to build the application, you need to have maven installed.

To build the application run 
###`mvn clean install` 
on the project root. You will find a war-file afterwards in the target-folder, that you can deploy to a webserver.

## Local development

If you would like to develop the connector locally, you need an IDE like eclipse EE or IntelliJ and a local webserver like tomcat. 
Import the repository as a maven web application project and add the tomcat to the run configurations of the project. 

You can now run the tomcat and access a testpage via http://localhost:8080/{name of the war}/abfrage.html. 
On there you find a button, which when clicked will send a JSON to the Connector-Servlet.

The Connector-Servlet will create a Postobject with all the JSON-data, will insert it into a database and post the contents to defined webpages. 