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
    implementation(libs.spring.cloud.starter.loadbalancer)

    testImplementation(libs.spring.boot.starter.test)
}

application {
    mainClass.set("dev.viniciusarnhold.experiments.aot.app.CloudLoadbalancerApplication")
}

springAot {
    removeSpelSupport.set(true)
}

springBoot {
    mainClass.set(application.mainClass)
}