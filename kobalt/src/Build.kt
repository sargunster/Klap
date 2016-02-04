import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.publish.bintray
import com.beust.kobalt.plugin.publish.github

val repos = repos()

val kotlinVersion = "1.0.0-beta-4584"
val cucumberVersion = "1.2.5-SNAPSHOT"

val p = project {

    name = "Klap"
    group = "me.sargunvohra.lib"
    artifactId = name
    version = "1.0.1"

    sourceDirectories {
        path("src/main/kotlin")
        path("src/main/resources")
    }

    sourceDirectoriesTest {
        path("src/test/kotlin")
        path("src/test/resources")
    }

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
        compile("org.jetbrains.kotlin:kotlin-runtime:$kotlinVersion")
        compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        compile("me.sargunvohra.lib:CakeParse:1.0.1")
    }

    dependenciesTest {
        compile("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
        compile("info.cukes:cucumber-junit:$cucumberVersion")
        compile("info.cukes:cucumber-java8:$cucumberVersion")
        compile("info.cukes:cucumber-picocontainer:$cucumberVersion")
    }

    assemble {
        jar {
        }
        mavenJars {
        }
    }

    bintray {
        publish = true
        sign = true
    }

    github {
        file("$buildDirectory/libs/$name-$version.jar", "$name/$version/$name-$version.jar")
    }

    test {
        includes("**/*Tests.class", "**/*Test.class")
    }
}
