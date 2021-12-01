plugins {
    java
    id("com.diffplug.spotless")
    id("net.ltgt.errorprone")
}

dependencies {
    errorprone("com.google.errorprone:error_prone_core:2.10.0")
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
        googleJavaFormat("1.13.0")
    }
    kotlin {
        ktlint()
    }
    kotlinGradle {
        ktlint()
    }
}