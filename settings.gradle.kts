pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.spring.io/milestone")
        maven("https://repo.spring.io/snapshot")
    }
}

enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "spring-native-experiments"
include("reactive-feign-app")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://repo.spring.io/milestone")
        maven("https://repo.spring.io/snapshot")
    }
}