enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}