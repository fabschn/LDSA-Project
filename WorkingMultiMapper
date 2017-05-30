import sys
from mrjob.job import MRJob
import logging
logging.basicConfig(filename='example.log',filemode='w')
class MRCrimePerYear(MRJob):
        def filter_data_by_year(self, key, record):
                record = record.split(',')
                if record[17] =='2011':
                        crimeType = record[5]
                        date = record[2]
                        month = date.split(' ')[0].split('/')[0]
                        yield crimeType, month
        def count_event_mapper(self, crimeType, month):
                yield'%s %s' % (month, crimeType), 1

        def sumCrime(self, month, crimeType):
                yield month, sum(crimeType)

        def steps(self):
                return [self.mr(mapper=self.filter_data_by_year),
                self.mr(mapper=self.count_event_mapper),
                self.mr(reducer=self.sumCrime)]
if __name__=='__main__':
        MRCrimePerYear.run()

