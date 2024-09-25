plugins {
    id("com.epages.restdocs-api-spec") version "0.16.0"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    // web
    compileOnly("org.springframework.boot:spring-boot-starter-web")

    // kotlin object mapper
    compileOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    // security
    compileOnly("org.springframework.security:spring-security-core")
    compileOnly("com.github.wwan13:winter-security:0.0.10")

    // jpa
    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")

    // kotlin jdsl
    compileOnly("com.linecorp.kotlin-jdsl:jpql-dsl:3.4.2")
    compileOnly("com.linecorp.kotlin-jdsl:jpql-render:3.4.2")
    compileOnly("com.linecorp.kotlin-jdsl:hibernate-javax-support:3.4.2")

    // api docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("com.github.wwan13.kotlin-dsl-rest-docs:impl-mockmvc:1.2.11")
}
