# spark-scala-template
Spark Scala App sbt project template

## Run

```bash
shell> sbt assembly

# Start docker spark cluster here

shell> ~/git/spark/bin/spark-submit --master spark://192.168.1.100:7077 --class net.zhenglai.spark.BasicAvgApp target/scala-2.11/spark-scala-app.jar

shell> bin/spark-submit --master spark://192.168.1.100:7077 --deploy-mode cluster --class net.zhenglai.spark.BasicAvgWithKryoApp target/scala-2.11/spark-scala-app.jar

```

## Usage

master web: `http://192.168.1.100:8080/`
work web:   `http://192.168.1.100:8082/` and `http://192.168.1.100:8081`

## Troubleshooting

1. **Version issue**

```bash
Exception in thread "main" java.lang.NoSuchMethodError: org.apache.spark.SparkContext.<init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lscala/collection/Seq;Lscala/collection/Map;)V
        at net.zhenglai.sparkdemo.BasicAvgApp$.main(BasicAvgApp.scala:14)
```

**Solution**
Make sure local spark-submit related lib version is compatible with the spark jar version defined in `build.sbt`
