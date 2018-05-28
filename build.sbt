name := """Scala-WebApp-AdminLTE"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

// Resolvers
//resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Atlassian Releases" at "https://maven.atlassian.com/public/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Ejisan Github" at "https://ejisan.github.io/repo/",
  "lightshed-maven" at "http://dl.bintray.com/content/lightshed/maven"
)
// Dependencies
libraryDependencies ++= Seq(
  "com.ejisan" %% "play-pagemeta" % "2.0.1",
  "com.ejisan" %% "play-form" % "3.0.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)
// Dependencies mysql
libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.11",
  jdbc,
  cache,
  ws,
  "org.scalikejdbc" %% "scalikejdbc"          % "3.1.0",
  "org.scalikejdbc" %% "scalikejdbc-config"   % "3.1.0",
  "com.h2database"  %  "h2"                        % "1.4.197",
  "ch.qos.logback"  %  "logback-classic"           % "1.2.3"

)
// Web Jars Dependencies
libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars.bower" % "compass-mixins" % "1.0.2",
  //"org.webjars" % "foundation" % "6.2.3",
  "org.webjars" % "jquery" % "2.2.4"

)

// Email Dependencies
libraryDependencies ++= Seq(
  "ch.lightshed" %% "courier" % "0.1.4"
)

dependencyOverrides += "org.webjars" % "jquery" % "2.2.4"

TwirlKeys.templateImports += "ejisan.play.libs.PageMeta"
