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

    implementation(libs.spring.boot.starter.webflux)

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.reactor.test)
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