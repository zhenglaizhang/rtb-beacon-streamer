package net.zhenglai.sparkdemo

import org.apache.spark._
import org.apache.spark.rdd.RDD

object BasicAvgApp {

  def main(args: Array[String]) = {
    val master = args.length match {
      case x: Int if x > 0 ⇒ args(0)
      case _ ⇒ "local"
    }

    val sc = new SparkContext(master, "BasicAvg.App", System.getenv("SPARK_HOME"))
    val input = sc.parallelize(1 to 100)
    val result = computeAvg(input)
    val avg = result._1 / result._2.toFloat
    println(s"BasicAvgApp output => $avg")
  }

  def computeAvg(input: RDD[Int]) = {
    input.aggregate((0, 0))(
      (x, y) ⇒ (x._1 + y, x._2 + 1),
      (x, y) ⇒ (x._1 + y._1, x._2 + y._2)
    )
  }
}

