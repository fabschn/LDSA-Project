#!/usr/bin/env python3

import sys

for line in sys.stdin:
	line = line.strip()

	columns = line.split(',')

	print('%s\t%s' % (columns[5], 1))
