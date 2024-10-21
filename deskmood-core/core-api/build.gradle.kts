plugins {
    id("com.epages.restdocs-api-spec") version "0.16.0"
}

dependencies {
    // module dependencies
    implementation(project(":deskmood-core:core-domain"))
    implementation(project(":deskmood-storage:core-db"))
    implementation(project(":deskmood-clients:google-client"))
    implementation(project(":deskmood-clients:naver-client"))
    implementation(project(":deskmood-clients:s3-client"))
    implementation(project(":deskmood-support:logging"))
    implementation(project(":deskmood-support:monitoring"))

    runtimeOnly(project(":deskmood-admin:admin-api"))

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // kotlin object mapper
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // logging-request
    implementation("com.github.wwan13:spring-request-logger:0.0.7")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.github.wwan13:winter-security:0.0.10")

    // api docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("com.github.wwan13.kotlin-dsl-rest-docs:impl-mockmvc:1.2.11")
}

tasks {
    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}
