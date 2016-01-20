# sbt-native-packager-test
A small study project on the [sbt-native-packager](http://www.scala-sbt.org/sbt-native-packager/).

# Documentation
The following documentation is available:

- [SBT Native Packager Universal Plugin](http://www.scala-sbt.org/sbt-native-packager/formats/universal.html)
- [SBT Native Packager Archetype Cheatsheet](http://www.scala-sbt.org/sbt-native-packager/archetypes/cheatsheet.html)
- [SBT Native Packager Packaging Formats](http://www.scala-sbt.org/sbt-native-packager/formats/index.html)
- [Nepomuk Seiler - SBT Native Packager Examples](https://github.com/muuki88/sbt-native-packager-examples)


## Examples
The following examples are sorted from very simple to complex. Simple projects only use defaults that come out
of the box and the more complex override these default settings because we don't like the 'acceptable default' and 
override it with our own setting.

- [single-module-build](https://github.com/dnvriend/sbt-native-packager-demo/tree/master/single-module-build)

## Basic Configuration
- In `project/build.properties` add:

```bash
sbt.version=0.13.9
```

- In `project/plugins.sbt` add:

```scala
// to resolve jars //
resolvers +=  "bintray-sbt-plugin-releases" at "http://dl.bintray.com/content/sbt/sbt-plugin-releases"

// to package applications //
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.1.0-M3")

```

- Configure the project with minimum settings:

```scala
name := "simple-java-packaging"

version := "1.0.0"

scalaVersion := "2.11.7"

enablePlugins(JavaAppPackaging)
```

- Package your project eg. `universal:packageBin` to create a `zip`, `universal:packageZipTarball` to create a `tgz` or
`docker:publishLocal` to create a docker image in your local repository.

# Available Archetypes
[Archetypes](http://www.scala-sbt.org/sbt-native-packager/gettingstarted.html#archetypes) __packaging defaults__ that make assumptions how to package our application and make it easy for us to 
package our application quickly. Of course, when we don't like (some of) the default behaviors we can override 
those by overriding the appropriate key using the [archetype cheatsheet](http://www.scala-sbt.org/sbt-native-packager/archetypes/cheatsheet.html). 

Archetypes are enabled in the `build.sbt` file. For example, to enable the packaging defaults for an application
that will be a standalone application, we can use the `JavaAppPackaging` archtype. Just add the line `enablePlugins(JavaAppPackaging)`
to `build.sbt` and you are set.
  
The following [archetypes](http://www.scala-sbt.org/sbt-native-packager/gettingstarted.html#archetypes) are available:

- [Java Application](http://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/): `enablePlugins(JavaAppPackaging)` - Creates a standalone package, with a `bin/lib` directory structure and an executable bash/bat script.
- [Java Server](http://www.scala-sbt.org/sbt-native-packager/archetypes/java_server/): `enablePlugins(JavaServerAppPackaging)` - Creates a standalone package with an executable bash/bat script and additional configuration and autostart.

# Available Packaging Formats
Believe it or not, the `zip`, `tgz` and `docker` are not the only packaging formats available. The `sbt-native-packager`
also supports the following packaging formats:
 
__Note:__  Some packaging formats may only be created when the environment SBT runs in supports it, eg. when running on
Ubuntu, the plugin can create the `deb` (Debian) package and packaging `docker` is only supported when `docker` is available
and so on.

- [deb](http://www.scala-sbt.org/sbt-native-packager/formats/debian.html): `debian:packageBin` - Packaging format for Debian based systems like Ubuntu using the `Debian plugin`.
- [rpm](http://www.scala-sbt.org/sbt-native-packager/formats/rpm.html): `rpm:packageBin` - Packaging format for Redhat based systems like RHEL or CentOS using the `Rpm plugin`.
- [msi](http://www.scala-sbt.org/sbt-native-packager/formats/windows.html): `windows:packageBin` - Packaging format for windows systems using the `Windows plugin`.
- [dmg](http://www.scala-sbt.org/sbt-native-packager/formats/universal.html): `universal:packageOsxDmg` - Packaging format for osx based systems using the `Universal plugin`.
- [docker](http://www.scala-sbt.org/sbt-native-packager/formats/docker.html): `docker:publishLocal` - Package your application in a docker container using the `Docker plugin`.
- [zip](http://www.scala-sbt.org/sbt-native-packager/formats/universal.html): - `universal:packageBin` - Packaging format for all systems supporting zip using the `Universal plugin`.
- [tar](http://www.scala-sbt.org/sbt-native-packager/formats/universal.html): `universal:packageZipTarball` - Packaging format for all systems supporting tar using the `Universal plugin`.
- [xz](http://www.scala-sbt.org/sbt-native-packager/formats/universal.html): `universal:packageXzTarball` - Packaging format for all systems supporting xz using the `Universal plugin`.
- [jdkpackager](http://www.scala-sbt.org/sbt-native-packager/formats/jdkpackager.html): `jdkPackager:packageBinl` Oracle javapackager create packages for your running platform using the `JDK Packager plugin`.

