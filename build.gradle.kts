plugins {
    kotlin("jvm") version "2.1.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.java.dev.jna:jna:5.13.0")
}

application {
    mainClass.set("yichen.ScheduleStartTeamsKt") // ← 改成你的主类
}
