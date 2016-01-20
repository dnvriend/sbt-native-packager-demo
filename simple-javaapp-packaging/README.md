# simple-javaapp-packaging
In this build, we will look at [the most simple style of packaging](http://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/my-first-project.html) you can think of, the 
`JavaAppPackaging` packaging archetype that comes out of the box with the `sbt-native-packager` plugin.

In this example, we have a minimum of configuration, so its all the defaults.
 
# Configuring the project
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
│   │   ├── simple-javaapp-packaging
│   │   └── simple-javaapp-packaging.bat
│   └── lib
│       ├── com.github.dnvriend.simple-javaapp-packaging-1.0.0.jar
│       └── org.scala-lang.scala-library-2.11.7.jar
```

The `stage` task creates a local directory with all the files laid out as they would be in the final distribution.
The plugin created a `bin` directory, containing the launch scripts and a `lib` directory containing all the 
dependent jars. You can test the local distribution by launching the script in the `bin` directory.

# Creating a zip and tgz distribution
The steps above did __not__ package our application, it just added the `sbt-native-packager` plugin to your project and created
a local `staging` directory containing all the files our distribution will be based on, just for us to verify that everything
works as expected.

We have a lot of ways to distribute our application. For example, our application could be distributed as a `zip` or `tgz`.
Let's do that. Change to the directory `target/universal` and use the `zip` or `tgz` command in the `bash` console. 
No I am just kidding :P, let's use the `sbt-native-packager`! 

The `sbt-native-packager` can package our application a lot of ways. It can create for example a `zip` and `tgz` file 
for us. Let's try it (the output below has been edited):
 
```bash
> ;clean;universal:packageBin;universal:packageZipTarball
...
[success] Total time: 0 s, completed 20-jan-2016 15:41:13
```

It created the following with a single line!

```bash
target/universal
├── simple-javaapp-packaging-1.0.0.tgz
├── simple-javaapp-packaging-1.0.0.zip
```

The zip and tgz files contains exact the same content (and directory structure) we found in the `staged` directory.
The `zip` and `tgz` files can be distributed and the whole world can experience our Hello World! 

Of course, the behavior of the `sbt-native-packager` can be configured, and there is a [cheatsheet](http://www.scala-sbt.org/sbt-native-packager/archetypes/cheatsheet.html)
available with configuration items, scopes and variables that are available for us to tweak the behavior. 

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
[info] Wrote /Users/dennis/projects/sbt-native-packager-demo/simple-javapp-packaging/target/scala-2.11/simple-javaapp-packaging_2.11-1.0.0.pom
[success] Total time: 0 s, completed 20-jan-2016 15:42:06
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
            │   ├── simple-javaapp-packaging
            │   └── simple-javaapp-packaging.bat
            └── lib
                ├── com.github.dnvriend.simple-javaapp-packaging-1.0.0.jar
                └── org.scala-lang.scala-library-2.11.7.jar
```

And it creates the following `Dockerfile`:

```bash
FROM java:latest
WORKDIR /opt/docker
ADD opt /opt
RUN ["chown", "-R", "daemon:daemon", "."]
USER daemon
ENTRYPOINT ["bin/simple-javaapp-packaging"]
CMD []
```

Lets actually build and package our project using docker using the `docker:publishLocal` task:

```bash
> docker:publishLocal
...
[info] Successfully built 5eba301f438b
[info] Built image simple-javaapp-packaging:1.0.0
[success] Total time: 3 s, completed 20-jan-2016 15:43:08
```

We can now run the docker image:

```bash
$ docker run simple-javaapp-packaging:1.0.0
Hello World!
```

# Other packaging formats
The `sbt-native-packager` supports [other packaging formats](http://www.scala-sbt.org/sbt-native-packager/formats/index.html), 
but for now, I think I have covered the most useful ones, the `zip`, `tgz` and `docker` and we have seen that the plugin
can be used with a minimum of configuration.