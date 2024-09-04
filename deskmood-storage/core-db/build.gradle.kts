allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    // core-module
    compileOnly(project(":deskmood-core:core-domain"))

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // kotlin jdsl
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:3.5.2")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:3.5.2")
    implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support:3.5.2")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    // h2 : test
    testImplementation("com.h2database:h2")
}
