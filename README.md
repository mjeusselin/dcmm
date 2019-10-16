# DICOM Manager

This is a Java application using DICOM data.

## Projet branches

The master branch represents the application latest version.

Other integration branches served to build intermediary versions, each one with a specified purpose :
* int/v0.1 : opens a file, changes the content of DICOM data, and saves the file.
* int/v0.2 : v0.1 + includes a small PNG image vertically centered on the right border of an image and stores it into a new file.
* int/v0.3 : v0.2 + monitor a directory and track modifications in database.
