package net.zhenglai.spark

import com.holdenkarau.spark.testing.SharedSparkContext
import org.scalatest.{FunSuite, Matchers}

class WordCountSpec extends FunSuite with Matchers with SharedSparkContext {
  test("sc initialization") {
    val rdd = sc.parallelize(List(1, 2, 3, 4))
    rdd.sum should be(10)
  }
}
