# spark-fim
spark-fim is a library of scalable frequent itemset mining algorithms based on Spark. It includes:
  + PHybridFIN - A parallel frequent itemset mining algorithm based on a novel data structure named HybridNodeset to represent itemsets. It achieves a significantly better performance on different datasets when the minimum support decreases comparing to the FP-Growth algorithm which is implemented in Spark MLlib.

# Examples
## Scala API
```scala
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
```

# Requirements
spark-fim is built against Spark 1.6.2.

# Build From Source
```scala
sbt package
```

# Licenses
spark-fim is available under Apache Licenses 2.0.

# Contact & Feedback
If you encounter bugs, feel free to submit an issue or pull request. Also you can mail to:
+ Chen Lin (m2linchen@gmail.com).