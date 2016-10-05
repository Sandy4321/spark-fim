package org.apache.spark.fim

import org.apache.spark.{SparkConf, SparkContext}

import scala.compat.Platform._

object PHybridFINSuite {
  def main(args: Array[String]): Unit = {
    val input = "/home/linchen/chess.txt"
    val minSupport = 0.85
    val numPartitions = 4
    val conf = new SparkConf()
      .setAppName("PHybridFIN")
      .set("spark.cores.max", "4")
      .set("spark.executor.memory", "4g")
      .set("spark.driver.maxResultSize", "1g")
      .setMaster("local[4]")

    val sc = new SparkContext(conf)
    val transactions = sc.textFile(input, numPartitions).map(_.split(" ")).cache()
    val numTransactions = transactions.count()
    val startTime = currentTime
    val model = new PHybridFIN()
      .setMinSupport(minSupport)
      .setNumPartitions(transactions.partitions.length)
      .run(transactions)
    val numFreqItemsets = model.freqItemsets.count()
    val endTime = currentTime
    val totalTime: Double = endTime - startTime

    println(s"====================== PHybridFIN - STATS ===========================")
    println(s" minSupport = " + minSupport + s"    numPartition = " + numPartitions)
    println(s" Number of transactions: " + numTransactions)
    println(s" Number of frequent itemsets: " + numFreqItemsets)
    println(s" Total time = " + totalTime/1000 + "s")
    println(s"=====================================================================")

    sc.stop()
  }
}
