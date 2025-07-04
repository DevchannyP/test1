plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
    id("org.jetbrains.kotlin.plugin.spring")
    id("org.jetbrains.kotlin.plugin.jpa")
}

dependencies {
    // ✅ 필수 Spring 모듈
    implementation(myLibs.spring.boot.starter.web)
    implementation(myLibs.spring.boot.starter.security)
    implementation(myLibs.spring.boot.starter.data.jpa)
    implementation(myLibs.spring.boot.starter.oauth2.client)
    implementation(myLibs.spring.boot.starter.data.redis)

    // ✅ JWT
    implementation(myLibs.jjwt.api)
    runtimeOnly(myLibs.jjwt.impl)
    runtimeOnly(myLibs.jjwt.jackson)

    // ✅ Swagger
    implementation(myLibs.springdoc.openapi)

    // ✅ AWS, Elastic
    implementation(myLibs.aws.s3)
    implementation(myLibs.elasticsearch.java)

    // ✅ 기타
    implementation(myLibs.jackson.module.kotlin)
    implementation(myLibs.guava)
    implementation(myLibs.commons.codec)
    implementation(myLibs.bouncycastle)
    implementation(myLibs.jsoup)

    // ✅ ShedLock
    implementation(myLibs.shedlock.spring)
    implementation(myLibs.shedlock.jdbc)

    // ✅ QueryDSL
    implementation(myLibs.querydsl.jpa)
    kapt(myLibs.querydsl.apt)

    // ✅ 테스트
    testImplementation(myLibs.spring.boot.starter.test)
    testImplementation(myLibs.mockito.core)
    testImplementation(myLibs.kotlin.test.junit5)
}

kapt {
    correctErrorTypes = true
}

sourceSets["main"].java.srcDir("build/generated/source/kapt/main")
