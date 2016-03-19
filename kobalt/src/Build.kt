import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.publish.bintray

object Versions {
    val kotlin = "1.0.1"
    val cucumber = "1.2.4"
    val cakeparse = "1.0.7"
}

val p = project {

    name = "Klap"
    group = "me.sargunvohra.lib"
    artifactId = name
    version = "1.0.2"

    dependencies {
    	compile("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
        compile("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
        compile("me.sargunvohra.lib:CakeParse:${Versions.cakeparse}")
    }

    dependenciesTest {
    	compile("org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}")
        compile("info.cukes:cucumber-junit:${Versions.cucumber}")
        compile("info.cukes:cucumber-java8:${Versions.cucumber}")
    }

    assemble {
        jar {
        }
        mavenJars {
        }
    }

    bintray {
        publish = true
    }
}
