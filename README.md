# LDSA-Project

## Example: Counting the Crime Type

To run locally, issue this command in the home directory of this repository:

	head -10 crime-examples.csv| ./mapper.py | sort -k1,1 | ./reducer.py

It should produce an output like this:

	BATTERY	2
	MOTOR VEHICLE THEFT	2
	Primary Type	1
	THEFT	5