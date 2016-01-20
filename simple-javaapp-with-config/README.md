# simple-javaapp-with-config
The Java Application Packaging or `JavaAppPackaging` archetype provides a default application structure and executable 
scripts to launch your application. To package the `JavaAppPackaging` structure as a `zip` or `tar` we use the 
[Universal Plugin](http://www.scala-sbt.org/sbt-native-packager/formats/universal.html), that is automatically enabled
when you enable the `JavaAppPackaging` archetype. 

The `Universal Plugin` creates a distribution format that is not tied to any particular platform. This means that it does 
not create a `package`, that must be managed by a platform specific package manager like eg. `rpm`, `apt` or `brew`. It creates
a universal package like a `zip`, that needs some manual labor to setup the application on a specific platform, but if 
an unzipper is available on your platform (any platform) that the `zip` can be extracted and the content launched.
                    
The [Universal Plugin](http://www.scala-sbt.org/sbt-native-packager/formats/universal.html) uses a set of conventions 
how the default directory structure looks like:
 
```bash
bin/
   <scripts and things you want on the path>
lib/
   <shared libraries>
conf/
   <configuration files that should be accessible using platform standard config locations.>
doc/
   <Documentation files that should be easily accessible. (index.html treated specially)>
```

By default, all files found in the `src/universal` directory of the project are included/packaged in the distribution.
So, the first step in creating a a distribution is to place files in this directory in the layout you would like 
in the distributed zip file. For example, placing configuration files in `src/universal/conf/application.conf` will
package the file `application.conf` in the `conf` directory of the zip.
 
This build will package a simple `application.conf` and will use the [Typesafe Configuration](https://github.com/typesafehub/config)
libary that provides a way to separate application configuration from the source code in a human readable format.   

In `build.sbt` we will add the following line:

```scala
bashScriptExtraDefines += """addJava "-Dconfig.file=${app_home}/../conf/application.conf""""
```

The `bashScriptExtraDefines` alteres the bash/bat script that gets generated. The configuration is literally woven into it. 
The line modifies the generated `bash` script to add the JVM option the Typesafe config library uses to read the location 
of the application configuration on disk. The Typesafe config reads the `config.file` environment variable.

Now, let’s try it out on the command line:

```bash
> simpleJavaAppWithConfig/stage
[info] Wrote /Users/dennis/projects/sbt-native-packager-demo/simple-javaapp-with-config/target/scala-2.11/simple-javaapp-with-config_2.11-1.0.0.pom
[success] Total time: 0 s, completed 20-jan-2016 21:19:52
```

It creates the following directory structure:

```bash
target/universal/
├── stage
│   ├── bin
│   │   ├── simple-javaapp-with-config
│   │   └── simple-javaapp-with-config.bat
│   ├── conf
│   │   └── application.conf
│   └── lib
│       ├── com.github.dnvriend.simple-javaapp-with-config-1.0.0.jar
│       ├── com.typesafe.config-1.2.0.jar
│       └── org.scala-lang.scala-library-2.11.7.jar
```
                     
# Changing the executable script name
Say, we want to change the executable script name in the `bin` directory. We can configure it
by looking at the [cheatsheet](http://www.scala-sbt.org/sbt-native-packager/archetypes/cheatsheet.html). 
There we read that the variable `executableScriptName` must be used in Global scope, this means that we can add 
the following to the `build.sbt` file to change the executable script name from `simple-javaapp-with-config` to `helloworld`:

```bash
executableScriptName := "helloworld
```

Creating a new packaging with the command creates the following directory structure:

```bash
> simpleJavaAppWithConfig/universal:packageBin
[info] Wrote /Users/dennis/projects/sbt-native-packager-demo/simple-javaapp-with-config/target/scala-2.11/simple-javaapp-with-config_2.11-1.0.0.pom
[success] Total time: 1 s, completed 20-jan-2016 21:27:38
```

It creates the following directory structure:

```bash
target/universal/
├── stage
│   ├── bin
│   │   ├── helloworld
│   │   └── helloworld.bat
│   ├── conf
│   │   └── application.conf
│   └── lib
│       ├── com.github.dnvriend.simple-javaapp-with-config-1.0.0.jar
│       ├── com.typesafe.config-1.2.0.jar
│       └── org.scala-lang.scala-library-2.11.7.jar

```

This concludes this short introduction to add configuration files to our packaging.