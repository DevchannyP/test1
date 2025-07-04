plugins {
    // Kotlin 관련
    kotlin("jvm")
    kotlin("kapt")
    id("org.jetbrains.kotlin.plugin.spring")
    id("org.jetbrains.kotlin.plugin.jpa")

    // Spring Boot 관련
    id("org.springframework.boot") // ❗️여기선 apply false 제거: 실행 모듈에서만 true
    id("io.spring.dependency-management")
}

dependencies {
    // ✅ 필수 Spring 모듈
    implementation(myLibs.spring.boot.starter.web)
    implementation(myLibs.spring.boot.starter.security)
    implementation(myLibs.spring.boot.starter.data.jpa)
    implementation(myLibs.spring.boot.starter.oauth2.client)
    implementation(myLibs.spring.boot.starter.data.redis)

    // ✅ 직접 선언된 Spring 모듈 (myLibs와 중복되지 않는 경우)
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

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

    // ✅ Lombok (Java 및 Kotlin 혼용 시 필수)
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    kapt("org.projectlombok:lombok:1.18.30")

    // ✅ 테스트
    testImplementation(myLibs.spring.boot.starter.test)
    testImplementation(myLibs.mockito.core)
    testImplementation(myLibs.kotlin.test.junit5)

    // 직접 선언된 테스트 (추가 보완)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

kapt {
    correctErrorTypes = true
}

// ✅ QueryDSL 및 Lombok 등 KAPT로 생성된 코드 위치 명시
sourceSets["main"].java.srcDir("build/generated/source/kapt/main")
