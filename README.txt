REQUIREMENTS:

scala 2.11.11
spark 2.3.3
sbt 1.0
java 8
Python 3.7


SUMMARY
This project is a demonstration for structured project using spark scala sbt and python for data visualisation.

	For the spark scala implementation : 
This project contains levels from data import, data clustering using kmeans algorithm, data visualisation for clustered data
the steps are described below:
Initializing spark Session
Importing data ( in the import we configure Properties.scala under src/resources/application.properties for paths)
Constructing a pipeline which do the following steps:
   Step 1 : assembling features
   Step 2 : Running the clustering on data
Applying the model to input data
Finally Closing spark Session

	For python Implementation
The python part will take the output dataset,  a CSV clustered data and visualise clusters in a map using
locations inputs and predicted labes from kmeans.
For code execution read the python requirements.txt file under Scripts/DataVis


CONFIGURATION
Before running the project locally , you have to complete the configuration file available in /src/resources/application.properties
input.data= json file used as input uu.json under dataInput/data folder.
output.dir= The directory that will be used to store clustered data.
model.dir=  Directory that will be used to store the model generated in case you will need to reuse it again.
a folder  DataOutput is created in order to store both of output and model directories
numberCluster= the number of cluster for kmeans clustering


RUNNING
for running this project
for project build : use  this comamnd
sbt clean assembly
this command will creat a fat JAR file which contains the compiled project.
for job submission : use this command
spark-submit
a folder under scripts named spark  contains the code of the spark-submit,
just copy/paste every time you want to change properties  or add --files, --class

OUTPUT/RESULT
Using python, we will use the clusters label for each address (address name),
specified with longitude and latitude data columns as input.
5 is the number of clusters, it is fixed as a parameter in application.properties;
Then could it could be modified if the analysis is not relevant.
In the case of 5 clusters, From the html output testmap.html under Scripts\DataVis,
we can interpret the clusters as north, south, east, west and center.

The python code also provides a way to capture an image from the html using the selenium package,
opening a browser window that displays the map, generate a screen grab of it and save the image map.png










