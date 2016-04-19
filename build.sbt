name := "plant-uml-server"

version := "1.0"

scalaVersion := "2.11.8"

unmanagedJars in Compile <++= baseDirectory map { base =>
  val customJars = base ** "/lib/*.jar"
  customJars.classpath
}

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.10.0"
)
