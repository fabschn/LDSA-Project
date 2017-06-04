#!/usr/bin/env python3

import sys
from mrjob.job import MRJob

class MRCrimePerYear(MRJob):
	def filter_data_by_year(self, key, record):
		if record[18] == 2011:
			crimeType = record[6]
			month = record[3]
			yield crimeType, month
	def count_event_mapper(self, crime, month)
		yield'%s %s' % (crimeType, month), 1

	def sumCrime(self, crimeType, month):
		yield month, sum(crimeType)

	def steps(self):
		return [self.mr(mapper=self.filter_data_by_year),
		mapper=self.count_event_mapper,
		reducer=self.sumCrime)]
if __name__=='__main__':
	MRCrimePerYear.run()
		

