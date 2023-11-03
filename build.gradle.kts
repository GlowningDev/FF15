plugins {
    kotlin("jvm") version "1.9.0"
}

group = "dev.glowning"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}


val jdaVersion = "5.0.0-beta.17"
val r4jVersion = "2.3.0"

dependencies {
    implementation("net.dv8tion:JDA:${jdaVersion}")
    implementation("com.github.stelar7:R4J:${r4jVersion}")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}