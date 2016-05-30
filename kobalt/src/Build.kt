import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.publish.bintray

object Versions {
    val kotlin = "1.0.2"
    val cakeparse = "1.0.8"
    val testng = "6.9.9"
}

@Suppress("unused")
val p = project {

    name = "klap"
    group = "me.sargunvohra.lib"
    artifactId = name
    version = "1.0.4"

    dependencies {
    	compile("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
        compile("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
        compile("me.sargunvohra.lib:cakeparse:${Versions.cakeparse}")
    }

    dependenciesTest {
    	compile("org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}")
        compile("org.testng:testng:${Versions.testng}")
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
