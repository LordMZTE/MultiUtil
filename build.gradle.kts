import groovy.lang.GroovyObject
import org.gradle.api.JavaVersion.VERSION_1_8
import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinTest

//settings for manifest and stuff
val specTitle = "MultiUtil"
val jarName = specTitle
val implTitle = "de.mzte.multiutil"
group = "de.mzte"
application.mainClassName = "de.mzte.multiutil.Main"
version = "1.2-SNAPSHOT"

plugins {
    id("java")
    id("application")
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("org.jetbrains.dokka") version "1.4.0-rc"
}

configure<JavaPluginConvention> {
    sourceCompatibility = VERSION_1_8
    targetCompatibility = VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    arrayOf(
        //Kotlin
        "org.jetbrains.kotlin:kotlin-reflect:1.3.72",
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8",
        "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.8",

        //GUI
        "no.tornado:tornadofx:1.7.20",

        //Other
        "org.reflections:reflections:0.9.12"
    ).forEach {implementation(it)}
}

//This exists so options can be set for test compile and compile at once
val kOptions = {obj: GroovyObject ->
//This is sorta garbage, but if i would do this the proper way Intelli'ntJ would think its an error even tho it is not
    obj.withGroovyBuilder {
        "kotlinOptions" {
            setProperty("jvmTarget", "1.8")
            //without this option, kotlin interfaces dont support default methods in java (will be changed in kt 1.4)
            setProperty("freeCompilerArgs", arrayOf("-Xjvm-default=compatibility"))
        }
    }
}

tasks.withType<KotlinCompile> {kOptions(this as GroovyObject)}
tasks.withType<KotlinTest> {kOptions(this as GroovyObject)}

@Suppress("DEPRECATION") //"version" is deprecated and archiveVersion cannot be set outside task scope. Too Bad!
task("fatJar", Jar::class) {
    group = "build" //sets group in intelliJ's side bar
    manifest.attributes.apply {
        set("Main-Class", application.mainClassName)
        set("Implementation-Version", version)
        set("Specification-Title", specTitle)
        set("Implementation-Title", implTitle)
    }
    archiveBaseName.set(jarName)
    from(configurations.runtimeClasspath.get()
        .map {if(it.isDirectory) it else zipTree(it)})
    with(tasks.jar.get())
}