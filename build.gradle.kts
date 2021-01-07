buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.Gradle.plugin)
        classpath(Libs.Gradle.build)
        classpath("com.android.tools.build:gradle:7.0.0-alpha03")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
