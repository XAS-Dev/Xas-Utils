plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val name = rootProject.name
val group = "top.xiyang6666"
val version = "0.1.0-SNAPSHOT"
val targetJavaVersion = 17

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    if (JavaVersion.current() < javaVersion) {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.jar {
    archiveVersion.set(version)
    archiveClassifier.set("bare")
}

tasks.shadowJar {
    archiveVersion.set(version)
    archiveClassifier.set("")
    relocate("org.jetbrains", "xyz.xasmc.xasutils.jetbrains")
    relocate("org.intellij", "xyz.xasmc.xasutils.intellij")
    relocate("kotlin", "xyz.xasmc.xasutils.kotlin")
    mergeServiceFiles()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}