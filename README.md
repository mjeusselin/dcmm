# DICOM Manager

This is a Java application which manipulates DICOM data.

## Projet branches

The master branch represents the application latest jar version v0.3. 

Other integration branches served to build intermediary versions, each one with a specified purpose :
* int/v0.1 : opens a file, changes the content of DICOM data, and saves the file.
* int/v0.2 : v0.1 + includes a small PNG image vertically centered on the right border of an image and stores it into a new file.
* int/v0.3 : v0.2 + monitor a directory and track modifications in database.
* int/v0.4 : v0.3 + distribute the application in a docker container (This branch was not merged into master because not fully operationnal as described in its own README.md, and because its pom is configured to build a war, not a jar as other branches do)

## How to build the application

The application can be built following these steps :
1. Import maven project dcmm-parent in Eclipse.
2. Right click on the projet > Maven > Update project...
3. Run this maven configuration on the base directory of the project : install -DskipTests
4. The build must be in SUCCESS.

## How to use the application

### From Eclipse

1. Adapt properties located in src/main/resources/application.properties (especially because of the choosen use absolute paths).
2. Run the main method src/main/java/fr/mjeu/dcmm/DcmApplication.java.
3. The application should run with success.

### Standalone

Prerequisites : The application has been built, a jar target/dcmm-parent-version.jar is available.
We note [DCMM_HOME] the absolute path to the folder we chose to contain our externalized configuration.
Near this readme.md, config-samples are provided, where DCMM_HOME was /Users/Maxime/Desktop/dcmm

1. Adapt [DCMM_HOME] in both application.properties and logback.xml
2. Launch following command from command line, in the target folder :
java -jar -Dspring.config.location="/Users/Maxime/Desktop/dcmm/application.properties" dcmm-parent-0.0.3-SNAPSHOT.jar
Example :
java -jar -Dspring.config.location="[DCMM_HOME]/application.properties" dcmm-parent-version.jar
