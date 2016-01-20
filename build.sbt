/*
 * Copyright 2015 Dennis Vriend
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

version in ThisBuild := "1.0.0"

organization in ThisBuild := "com.github.dnvriend"

scalaVersion in ThisBuild := "2.11.7"

licenses in ThisBuild +=("Apache-2.0", url("http://opensource.org/licenses/apache2.0.php"))

lazy val root = (project in file("."))
	.aggregate(singleModuleBuild) // When a task is run on this project, it will also be run on aggregated projects.
	.dependsOn(singleModuleBuild) // Adds classpath dependencies on internal or external projects.

lazy val singleModuleBuild = project in file("single-module-build")
