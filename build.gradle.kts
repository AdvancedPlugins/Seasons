plugins {
    id("java")
    id("maven-publish")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17);
    }
}

group = "net.advancedplugins.seasons"
version = "0.1.0"

repositories {
    mavenCentral()

    maven { url = uri("https://jitpack.io") }

    maven {
        name = "OSS Sonatype"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }

    maven { url = uri("https://oss.sonatype.org/content/repositories/central") }

    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        content {
            includeGroup("org.bukkit")
            includeGroup("org.spigotmc")
        }
    }
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.0")
    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}


tasks.named<Test>("test") {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        events("passed")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            version = project.version.toString()
            artifactId = project.name
        }
    }
}