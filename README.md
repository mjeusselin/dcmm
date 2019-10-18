# DICOM Manager

This is a Java application which manipulates DICOM data.

## Projet branches

The master branch represents the application latest jar version v0.3. 

Other integration branches served to build intermediary versions, each one with a specified purpose :
* int/v0.1 : opens a file, changes the content of DICOM data, and saves the file.
* int/v0.2 : v0.1 + includes a small PNG image vertically centered on the right border of an image and stores it into a new file.
* int/v0.3 : v0.2 + monitor a directory and track modifications in database.
* int/v0.4 : v0.3 + distribute the application in a docker container (This branch was not merged into master because not fully operationnal and because its pom is configured to build a war, not a jar as other branches do)

## How to build the application

WARNING : This branch (int/v0.4) contains a proof of concept of distributing the application in a docker container. As is, we can build from that branch a war, then deploy it in a docker container running Tomcat. The limit of this work resides in the fact that, in the docker container, the application has not the ability to configure any directory. The next step would be to study differents ways to share files between container and host. This might be a track among many others : https://docs.oracle.com/cd/E37670_01/E75728/html/section_x54_32w_gp.html.

However, we can follow these steps to modify a DICOM with the application within a docker container :

1. Import maven project dcmm-parent in Eclipse.
2. Right click > Maven > Update project...
3. Adapt paths in src/main/resources/application.properties in order to make them coherent with the absolutes paths such as seen by os running docker.
5. Run this maven configuration on the base directory of the project : install -DskipTests
5. The build must be in SUCCESS.

## How to use the application

Once the war is generated, those commands enable us to launch the app in a docker container, from the folder which contains Dockerfile :

docker build -t dcmm1 .

docker run -p 8888:8080 -v /vagrant/dcmm/git/dcmm/dcmm-resources:/vagrant/dcmm/git/dcmm/dcmm-resources:rw dcmm1:latest

Note : The application is launched, the input DICOM is processed with patient id tag change and PNG inclusion and modifications are saved into an output file. Tracking modifications in database is disabled because no mongoDB has been configured to work with this docker container.
