plugins {
    id("dev.viniciusarnhold.experiments.native.java-application-conventions")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.aot)
}

dependencies {
    libs.bundles.boms.spring.forUseAtConfigurationTime().get().forEach {
        implementation(enforcedPlatform(it))
    }

    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.reactive.feign.cloud.starter)
}

application {
    mainClass.set("dev.viniciusarnhold.experiments.aot.app.ReactiveFeignApplication")
}

springAot {
    removeSpelSupport.set(true)
}

springBoot {
    mainClass.set(application.mainClass)
}

tasks.bootBuildImage {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf(
        "BP_NATIVE_IMAGE" to "true"
    )
}