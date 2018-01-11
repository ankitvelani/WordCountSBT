import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]) {
    
    val sc = new SparkContext(new SparkConf().setAppName("WordCount").setMaster("local[2]").set("spark.executor.memory","1g"))
   

    val file= sc.textFile(args(0))
    val counts = file.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    
    counts.saveAsTextFile(args(1))
    sc.stop()
  }
}
