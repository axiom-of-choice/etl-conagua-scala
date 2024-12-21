scalaVersion := "2.12.18"

name := "etl-conagua"
// organization := "com.example"
version := "1.0"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % "3.5.0",
	"org.apache.spark" %% "spark-sql" % "3.5.0",
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test"
)

fork := true

javaOptions ++= Seq(
  "-Duser.name=" + System.getProperty("user.name"),
  "--add-exports", "java.base/jdk.internal.misc=ALL-UNNAMED",
  "--add-exports", "java.base/sun.nio.ch=ALL-UNNAMED"
)

javaHome := Some(file("/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home"))