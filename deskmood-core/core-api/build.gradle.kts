plugins {
    id("com.epages.restdocs-api-spec") version "0.16.0"
}

dependencies {
    // module dependencies
    implementation(project(":deskmood-core:core-domain"))
    implementation(project(":deskmood-storage:core-db"))
    implementation(project(":deskmood-support:logging"))
    implementation(project(":deskmood-support:monitoring"))

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // kotlin object mapper
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // logging-request
    implementation("com.github.wwan13:spring-request-logger:0.0.7")

    // api docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("com.github.wwan13.kotlin-dsl-rest-docs:impl-mockmvc:1.2.9")
}

tasks {
    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}
