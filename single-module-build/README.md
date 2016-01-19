# simple-java-packaging
In this build, we will look at [the most simple style of packaging](http://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/my-first-project.html) you can think of, the 
`JavaAppPackaging` packaging archetype that comes out of the box with the `sbt-native-packager` plugin.

In this example, we have a minimum of configuration, so its all the defaults.
 
# Configuring the project
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

# Adding the plugin to an existing build
To add the `sbt-native-packager` to an existing project, just add the plugin to the `project/plugins.sbt` file and
just enable the `JavaAppPackaging` archetype in `build.sbt`.
 
# Creating a local distribution
The `JavaAppPackaging` creates the following directory structure in the `target` directory
after running the `stage` task in the sbt console:

```bash
target/universal
├── stage
│   ├── bin
│   │   ├── simple-java-packaging
│   │   └── simple-java-packaging.bat
│   └── lib
│       ├── org.scala-lang.scala-library-2.11.7.jar
│       └── simple-java-packaging.simple-java-packaging-1.0.0.jar
        └── simple-java-packaging.bat
```

The `stage` task creates a local directory with all the files laid out as they would be in the final distribution.
The plugin created a `bin` directory, containing the launch scripts and a `lib` directory containing all the 
dependent jars. You can test the local distribution by launching the script in the `bin` directory.

# Creating a zip and tgz distribution
The steps above did package our application, it just added the `sbt-native-packager` plugin to your project and created
a local `staging` directory containing all the files our distribution will be based on, just for us to verify that everything
works as expected.

We have a lot of ways to distribute our application. For example, our application could be distributed as a `zip` or `tgz`.
Let's do that. Change to the directory `target/universal` and use the `zip` or `tgz` command in the `bash` console. 
No I am just kidding :P, let's use the `sbt-native-packager`! 

The `sbt-native-packager` can package our application a lot of ways. It can create for example a `zip` and `tgz` file 
for us. Let's try it (the output below has been edited):
 
```bash
> ;clean;universal:packageBin;universal:packageZipTarball
[success] Total time: 0 s, completed 19-jan-2016 19:42:56
[info] Packaging /Users/dennis/projects/sbt-native-packager-test/single-module-build/target/scala-2.11/simple-java-packaging_2.11-1.0.0-sources.jar ...
[info] Done packaging.
[info] Packaging /Users/dennis/projects/sbt-native-packager-test/single-module-build/target/scala-2.11/simple-java-packaging_2.11-1.0.0.jar ...
[info] Done packaging.
[success] Total time: 1 s, completed 19-jan-2016 19:42:57
[info] Wrote /Users/dennis/projects/sbt-native-packager-test/single-module-build/target/scala-2.11/simple-java-packaging_2.11-1.0.0.pom
a simple-java-packaging-1.0.0
a simple-java-packaging-1.0.0/bin
a simple-java-packaging-1.0.0/lib
a simple-java-packaging-1.0.0/lib/org.scala-lang.scala-library-2.11.7.jar
a simple-java-packaging-1.0.0/lib/simple-java-packaging.simple-java-packaging-1.0.0.jar
a simple-java-packaging-1.0.0/bin/simple-java-packaging
a simple-java-packaging-1.0.0/bin/simple-java-packaging.bat
[success] Total time: 0 s, completed 19-jan-2016 19:42:57
```

It created the following with a single line!

```bash
└── universal
    ├── simple-java-packaging-1.0.0.tgz
    ├── simple-java-packaging-1.0.0.zip
```

The zip and tgz files contains exact the same content (and directory structure) we found in the `staged` directory.
The `zip` and `tgz` files can be distributed and the whole world can experience our Hello World!

# Creating a docker distribution
[Docker](https://www.docker.com/) is a very popular distribution format, because you can build, ship and run our 
Hello World application. But before we can ship and run it, we must first build and package it, and guess what, `sbt`
can do both. The `sbt-native-packager` [supports docker](http://www.scala-sbt.org/sbt-native-packager/formats/docker.html).
The plugin has [some tasks and configuration](http://www.scala-sbt.org/sbt-native-packager/formats/docker.html) available
for you to tweak the settings, but before you run off and look at the settings, lets see what comes out of the box.

Before we actually build and package our application as a docker image, let's first take a look at what docker will do
for us. We can use the command `docker:stage` for this:

```bash
> docker:stage
[info] Wrote /Users/dennis/projects/sbt-native-packager-test/single-module-build/target/scala-2.11/simple-java-packaging_2.11-1.0.0.pom
[success] Total time: 0 s, completed 19-jan-2016 20:20:54
```

It created the following directory structure:

```bash
target/docker
├── Dockerfile
└── stage
    ├── Dockerfile
    └── opt
        └── docker
            ├── bin
            │   ├── simple-java-packaging
            │   └── simple-java-packaging.bat
            └── lib
                ├── org.scala-lang.scala-library-2.11.7.jar
                └── simple-java-packaging.simple-java-packaging-1.0.0.jar
```

And it creates the following `Dockerfile`:

```bash
FROM java:latest
WORKDIR /opt/docker
ADD opt /opt
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
ENTRYPOINT ["bin/simple-java-packaging"]
CMD []
```

Lets actually build and package our project using docker using the `docker:publishLocal` task:

```bash
> docker:publishLocal
[info] Wrote /Users/dennis/projects/sbt-native-packager-test/single-module-build/target/scala-2.11/simple-java-packaging_2.11-1.0.0.pom
[info] Sending build context to Docker daemon 5.769 MB
[info] Step 1 : FROM java:latest
...
[info] Built image simple-java-packaging:1.0.0
[success] Total time: 1 s, completed 19-jan-2016 20:25:13
```

We can now run the docker image:

```bash
$ docker run simple-java-packaging:1.0.0
Hello World!
```

# Other packaging formats
The `sbt-native-packager` supports [other packaging formats](http://www.scala-sbt.org/sbt-native-packager/formats/index.html), 
but for now, I think I have covered the most useful ones, the `zip`, `tgz` and `docker` and we have seen that the plugin
can be used with a minimum of configuration.