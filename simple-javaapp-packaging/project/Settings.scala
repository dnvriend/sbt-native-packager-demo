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

package com.github.dnvriend

import com.typesafe.sbt.SbtScalariform._
import de.heikoseeberger.sbtheader.HeaderKey._
import de.heikoseeberger.sbtheader.license.Apache2_0
import sbt._

import scalariform.formatter.preferences._

object Settings {

  lazy val settings: Seq[Setting[_]] =
    headerSettings ++ formatterSettings

  // enable setting headers on files //
  lazy val headerSettings: Seq[Setting[_]] = Seq(
    headers := Map(
      "scala" -> Apache2_0("2015", "Dennis Vriend"),
      "conf" -> Apache2_0("2015", "Dennis Vriend", "#")
    )
  )

  // enable scala code formatting //
  lazy val formatterSettings: Seq[Setting[_]] =
    scalariformSettings ++ Seq(
      ScalariformKeys.preferences := ScalariformKeys.preferences.value
        .setPreference(AlignSingleLineCaseStatements, true)
        .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
        .setPreference(DoubleIndentClassDeclaration, true)
        .setPreference(RewriteArrowSymbols, true)
    )
}