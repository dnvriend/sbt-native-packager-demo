resolvers ++= Seq(
  "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "bintray-sbt-plugin-releases" at "http://dl.bintray.com/content/sbt/sbt-plugin-releases",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

// to package applications
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.1.0-M3")
