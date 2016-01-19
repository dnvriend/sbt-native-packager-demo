# sbt-native-packager-test
Study project on the [sbt-native-packager](http://www.scala-sbt.org/sbt-native-packager/).

# Examples
https://github.com/muuki88/sbt-native-packager-examples

# TL;DR
1. In `project/plugins.sbt` add the plugin,
2. In `build.sbt`, enable one of the available `packaging format archetype plugin`, and set the `configuration`,  
3. Execute the appropriate `packaging task` for the packaging archetype of your choice,
4. Read the [getting started guide](http://www.scala-sbt.org/sbt-native-packager/gettingstarted.html).

# Packaging Archetypes
Packaging (project) archetypes are default deployment scripts that try to “do the right thing” for a given type of 
project. Because not all projects are created equal, there is no one single archetype for all native packages, 
but a set of them for usage.

The architecture of the plugin is set up so that you can customize your packages at any level of complexity.

The following archetypes are available:

Archetype        | Plugin           | Customize
---------------- | ---------------- | -----------
[Java CLI Application](http://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/index.html) | JavaAppPackaging | [Customize](http://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/customize.html) 
  
# Plugin Architecture
[Architecture](http://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/gettingstarted.html)