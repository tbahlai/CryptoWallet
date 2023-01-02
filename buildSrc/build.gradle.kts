plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies{
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation(kotlin("gradle-plugin","1.7.20"))
    implementation("com.squareup:javapoet:1.13.0")
}