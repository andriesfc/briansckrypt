plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    id("org.jetbrains.kotlin.kapt") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.0.4"
    id("io.micronaut.aot") version "4.0.4"
}

group = "com.andriesfc.briansckrypt"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.micrometer:micronaut-micrometer-annotation")
    kapt("io.micronaut.openapi:micronaut-openapi")
    kapt("io.micronaut.serde:micronaut-serde-processor")
    kapt("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
    implementation("io.micronaut.problem:micronaut-problem-json")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("io.swagger.core.v3:swagger-annotations")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.yaml:snakeyaml")
    testImplementation("io.micronaut:micronaut-http-client")
    developmentOnly("io.micronaut.controlpanel:micronaut-control-panel-management")
    developmentOnly("io.micronaut.controlpanel:micronaut-control-panel-ui")
}


application {
    mainClass.set("com.andriesfc.briansckrypt.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    compileTestKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("com.andriesfc.briansckrypt.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}



