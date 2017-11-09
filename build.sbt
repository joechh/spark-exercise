import sbtassembly.AssemblyPlugin.autoImport._

name := "sparkClass"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.0"
libraryDependencies += "log4j" % "log4j" % "1.2.16"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.0.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.0.0"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.0.0"
libraryDependencies += "org.apache.hive" % "hive-jdbc" % "1.2.2"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-8-assembly_2.11" % "2.0.0"
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.0-M3"
libraryDependencies += "com.typesafe" % "config" % "1.3.1"
libraryDependencies += "com.typesafe.akka" %% "akka-camel" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster-metrics" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster-sharding" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster-tools" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-distributed-data" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-multi-node-testkit" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-osgi" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-persistence" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-persistence-query" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-persistence-tck" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-typed" % "2.5.1"
libraryDependencies += "com.typesafe.akka" %% "akka-contrib" % "2.5.1"


resolvers += "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"

assemblyMergeStrategy in assembly := {
  case PathList("org", "aopalliance", xs@_*) => MergeStrategy.last
  case PathList("org", "jsontype", xs@_*) => MergeStrategy.first
  case PathList("org", "apache", xs@_*) => MergeStrategy.last

  case PathList("javax", "inject", xs@_*) => MergeStrategy.last
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.last
  case PathList("javax", "activation", xs@_*) => MergeStrategy.last
  case PathList("javax", "xml", xs@_*) => MergeStrategy.last


  case PathList("com", "google", xs@_*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs@_*) => MergeStrategy.last
  case PathList("com", "codahale", xs@_*) => MergeStrategy.last
  case PathList("com", "yammer", xs@_*) => MergeStrategy.last
  case PathList("com", "thoughtworks", xs@_*) => MergeStrategy.last
  case PathList("org", "slf4j", xs@_*) => MergeStrategy.last
  case PathList("play", "core", "server", xs@_*) => MergeStrategy.last

  case PathList("META-INF", "LICENSE.txt") => MergeStrategy.concat
  case PathList("META-INF", "NOTICE.txt") => MergeStrategy.concat
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}


assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("org.codehaus.jackson.**" -> "shadejackson.@1").inAll
)
