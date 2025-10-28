plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.cloudsimplus:cloudsimplus:8.5.7")
}

tasks.test {
    useJUnitPlatform()
}