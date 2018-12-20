name := "SpringInsanity"

organization := "net.woggioni"

version := "1.0"
resolvers += Resolver.mavenLocal

logLevel := Level.Info
git.useGitDescribe := true
fork := true

val springVersion = settingKey[String]("The version of Scala used for building.")

springVersion := "5.1.3.RELEASE"

libraryDependencies += "org.projectlombok" % "lombok" % "1.18.4"
libraryDependencies += "javax" % "javaee-api" % "7.0"
libraryDependencies += "org.springframework" % "spring-context" % springVersion.value
libraryDependencies += "org.springframework" % "spring-test" % springVersion.value

libraryDependencies += "junit" % "junit" % "4.12" % "test" % Test
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test


