plugins {
    id("dev.viniciusarnhold.experiments.native.java-application-conventions")
    id("dev.viniciusarnhold.experiments.native.kotlin-application-conventions")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.aot)
}

dependencies {
    libs.bundles.boms.spring.forUseAtConfigurationTime().get().forEach {
        implementation(enforcedPlatform(it))
    }

    implementation(libs.spring.cloud.starter.function.webflux)
    implementation(libs.spring.cloud.function.adapter.aws)

    testImplementation(libs.spring.boot.starter.test)
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