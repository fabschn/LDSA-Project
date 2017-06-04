import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext._

object SparkCrime {

	def main(args: Array[String]) {
		
		val conf = new SparkConf().setAppName("SparkCrime").setMaster("spark://192.168.1.90:7077")
		val sc = new SparkContext(conf)
		
		try{
		val relevantLines = sc.textFile("/mnt/volume/crimes.csv")
				.map(_.split(","))
				.filter{ line =>
					(line(17) == "2011")
				}

		val crimeMonth = relevantLines.map { line =>
						val month = line(2).split(" ")(0).split("/")(0)
						val crimeType = line(5)
						(crimeType, month)
					}
					.map { crimeMonth =>
						(crimeMonth, 1)
					}
					.reduceByKey(_+_)
					.saveAsTextFile("/mnt/volume/results")

		}	
		finally {
			sc.stop()
		}
		
	}
}
