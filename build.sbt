

// Homepage: https://github.com/zhenglaizhang/spark-scala-template
name := "Spark Scala App project template"

version := "0.1"

// replace this with your
organization := "neg.zhenglai"

scalaVersion := "2.11.8"

// | @see https://github.com/jrudolph/sbt-dependency-graph
// net.virtualvoid.sbt.graph.Plugin.graphSettings

// use "provided" to prevent sbt-assembly from bundling the JARs already provided
// | by the Spark cluster.
libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.0.0-preview" % "provided"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.0.0-preview" % "provided"
libraryDependencies += "org.apache.spark" % "spark-hive_2.11" % "2.0.0-preview" % "provided"

assemblyJarName in assembly := "spark-scala-app.jar"
// Since spark already has Scala, we skip to assembly scala related lib
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false, includeDependency = false)


// | Extra libraries

// resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"

// libraryDependencies += "ua_parser" % "ua-parser" % "1.3.0" withSources()

// libraryDependencies += "com.maxmind.geoip" % "geoip-api" % "1.2.14" withSources() withJavadoc()


// libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.3.11" withSources() withJavadoc()