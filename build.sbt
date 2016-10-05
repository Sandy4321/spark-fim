name := "spark-fim"

version := "1.0"

scalaVersion := "2.10.4"

spName := "chen-lin/spark-fim"

sparkVersion := "1.6.2"

sparkComponents += "mllib"

resolvers += Resolver.sonatypeRepo("public")

spShortDescription := "spark-fim"

spDescription := """A library of scalable frequent itemset mining algorithms based on spark""".stripMargin

credentials += Credentials(Path.userHome / ".ivy2" / ".sbtcredentials")

licenses += "Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0")
    