# sbt-native-packager-test
A small study project on the [sbt-native-packager](http://www.scala-sbt.org/sbt-native-packager/).

# Configuration
1. In `project/build.properties` add:

```bash
sbt.version=0.13.9
```

2. In `project/plugins.sbt` add:

```scala
resolvers ++= Seq(
  "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "bintray-sbt-plugin-releases" at "http://dl.bintray.com/content/sbt/sbt-plugin-releases",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

// to package applications
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.1.0-M3")
```

3. Configure the project with minimum settings:

```scala
name := "simple-java-packaging"

version := "1.0.0"

scalaVersion := "2.11.7"

enablePlugins(JavaAppPackaging)
```

4. Package your project eg. `universal:packageBin` to create a `zip`, `universal:packageZipTarball` to create a `tgz` or
`docker:publishLocal` to create a docker image in your local repository.
