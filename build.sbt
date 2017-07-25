name := "rtb-beacon-streamer"
version := "0.1"
organization := "neg.zhenglai"
scalaVersion := "2.11.8"

lazy val versions = new {
  val scalaTest = "3.0.1"
  val scalaCheck = "1.13.5"
  val akka = "2.5.3"
  val akkaHttp = "10.0.9"
  val scalaAsync = "0.9.6"
  val cats = "0.9.0"
}

// | @see https://github.com/jrudolph/sbt-dependency-graph
// net.virtualvoid.sbt.graph.Plugin.graphSettings

// use "provided" to prevent sbt-assembly from bundling the JARs already provided
// | by the Spark cluster.
libraryDependencies ++= Seq(
 "org.apache.spark" %% "spark-core" % "2.2.0" % "provided",
 "org.apache.spark" %% "spark-sql" % "2.2.0" % "provided",
 "org.apache.spark" %% "spark-hive" % "2.2.0" % "provided",
 "com.holdenkarau"  %% "spark-testing-base" % "2.2.0_0.7.2" % "test",
 "org.scalatest" %% "scalatest" % versions.scalaTest % Test,
 "org.scalacheck" %% "scalacheck" % versions.scalaCheck % Test
)

// avoid OOM when many tests are executed in spark local mode
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

assemblyJarName in assembly := "rtb-beacon-streamer.jar"
// Since spark already has Scala, we skip to assembly scala related lib
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false, includeDependency = false)


// | Extra libraries
// resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases"
// libraryDependencies += "ua_parser" % "ua-parser" % "1.3.0" withSources()
// libraryDependencies += "com.maxmind.geoip" % "geoip-api" % "1.2.14" withSources() withJavadoc()
// libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.3.11" withSources() withJavadoc()
