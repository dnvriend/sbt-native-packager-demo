// to resolve jars //
resolvers +=  "bintray-sbt-plugin-releases" at "http://dl.bintray.com/content/sbt/sbt-plugin-releases"

// to package applications //
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.1.0-M3")
