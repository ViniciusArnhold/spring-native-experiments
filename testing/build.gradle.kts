plugins {
    id("dev.viniciusarnhold.experiments.native.java-application-conventions")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.aot)
}

dependencies {
    libs.bundles.boms.spring.forUseAtConfigurationTime().get().forEach {
        implementation(enforcedPlatform(it))
    }

    implementation(libs.spring.cloud.starter.function.webflux)
    implementation(libs.spring.cloud.function.adapter.aws)

    testImplementation(libs.bundles.testing.spring)
}

application {
    mainClass.set("dev.viniciusarnhold.experiments.aot.app.CloudFunctionApplication")
}

springAot {
    removeSpelSupport.set(true)
}

springBoot {
    mainClass.set(application.mainClass)
}