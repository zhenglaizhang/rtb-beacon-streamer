package net.zhenglai.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Zhenglai on 7/20/16.
  */

case class Line(line: Int, content: String)

case class Text(lines: List[Line])

object BasicAvgWithKryoApp {

  def main(args: Array[String]) = {
    val master = args.length match {
      case x: Int if x > 0 ⇒ args(0)
      case _ ⇒ "local"
    }

    val conf = new SparkConf()
      .setMaster(master)
      .setAppName("BasicAvgWithKryoApp")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    conf.registerKryoClasses(Array(classOf[Line], classOf[Text]))
    val input = sc.parallelize(generate.lines)
    val result = input.map(
      _.line
    ).aggregate((0, 0))(
      (x: (Int, Int), y: Int) ⇒ (x._1 + y, x._2 + 1),
      (x: (Int, Int), y: (Int, Int)) ⇒ (x._1 + y._1, x._2 + y._2)
    )
    println(s"Spark computing on ${java.net.InetAddress.getLocalHost.getHostName} result => ${
      result._1 / result._2
        .toFloat
    }")
  }

  def generate = {
    var lines = List[Line]()
    for (i ← 1 to 1000) {
      lines = Line(i, s"hello ${i}") +: lines
    }
    Text(lines)
  }

}
