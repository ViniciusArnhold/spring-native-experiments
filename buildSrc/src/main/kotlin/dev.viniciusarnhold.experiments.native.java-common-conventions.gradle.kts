plugins {
    java
    id("com.diffplug.spotless")
}

dependencies {
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.GRAAL_VM)
    }

    consistentResolution {
        useCompileClasspathVersions()
    }
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.8.1")
        }
    }
}

spotless {
    java {
        importOrder()
        googleJavaFormat()
    }
    kotlin {
        ktlint()
    }
    kotlinGradle {
        ktlint()
    }
}