buildscript {
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("org.jetbrains.compose").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
