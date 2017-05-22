# LDSA-Project

## Example: Counting the Crime Type

To run locally, issue this command in the home directory of this repository:

	head -10 crime-examples.csv| ./mapper.py | sort -k1,1 | ./reducer.py

It should produce an output like this:

	BATTERY	2
	MOTOR VEHICLE THEFT	2
	Primary Type	1
	THEFT	5

## Run it on the cloud instance

Make sure the output directory does not exist:

	hdfs dfs -rm -r /user/hduser/crime-types

Run the Map Reduce job:

	hadoop jar /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-2.7.3.jar \
	-file /home/hduser/LDSA-Project/mapper.py    -mapper /home/hduser/LDSA-Project/mapper.py \
	-file /home/hduser/LDSA-Project/reducer.py   -reducer /home/hduser/LDSA-Project/reducer.py \
	-input /user/hduser/crimes/* -output /user/hduser/crime-types

Display the results:

	hdfs dfs -cat /user/hduser/crime-types/part-00000

