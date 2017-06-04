# LDSA-Project

## Example: Counting the Crime Type

This is not the final analysis, merely a first step which was used during the project to get started with writing Map Reduce tasks in python.

To run locally, issue this command in the home directory of this repository:

	head -10 crime-examples.csv| ./mapper.py | sort -k1,1 | ./reducer.py

It should produce an output like this:

	BATTERY	2
	MOTOR VEHICLE THEFT	2
	Primary Type	1
	THEFT	5

## Run the example with Hadoop

Make sure the output directory does not exist:

	hdfs dfs -rm -r /user/hduser/crime-types

Run the Map Reduce job:

	hadoop jar /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-2.7.3.jar \
	-file /home/hduser/LDSA-Project/mapper.py    -mapper /home/hduser/LDSA-Project/mapper.py \
	-file /home/hduser/LDSA-Project/reducer.py   -reducer /home/hduser/LDSA-Project/reducer.py \
	-input /user/hduser/crimes/* -output /user/hduser/crime-types

Display the results:

	hdfs dfs -cat /user/hduser/crime-types/part-00000

## Analysis: Hadoop 

The data to be analyzed is not included in this repository and can be downloaded as a csv file on the following web page:

https://data.cityofchicago.org/Public-Safety/Crimes-2001-to-present/ijzp-q8t2

In the following, it is assumed that the file is saved as 'crimes.csv'.

Since the analysis consists of multiple map and reduce steps, mrjob (https://pythonhosted.org/mrjob/) was used to be able to easily write a python script.

Make sure that mrjob is installed:

	pip install mrjob

### Run the analysis

To run the task, issue

	python multimapper.py -r hadoop < crimes.csv > crime-analysis.txt

## Analysis: Spark

In the 'SparkCrime.scala' file the following things have to be adjusted:

* the URL to be used as Spark master in line 9

	val conf = new SparkConf().setAppName("SparkCrime").setMaster(master_url)

* the location of the data file in line 13

	val relevantLines = sc.textFile(/path/to/file)

* the location of the resulting files in line 28

	.saveAsTextFile(/path/to/results)

Before running the analysis, make sure the results folder does not exist. To run the analysis, you can then call 

	spark-submit path/to/compiled/scala/jar




