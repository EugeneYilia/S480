import org.jetbrains.kotlin.gradle.dsl.JvmTarget

allprojects {
    repositories.all {
        (this as? MavenArtifactRepository)?.let { it.isAllowInsecureProtocol = true }
    }
}

plugins {
    kotlin("jvm") version "2.1.10"
    application
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("-Dfile.encoding=UTF-8")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    sourceCompatibility = "23"
    targetCompatibility = "23"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_23)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.java.dev.jna:jna:5.13.0")
}

application {
    mainClass.set("yichen.MaxSubSequence") // ← 改成你的主类
}
