plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    java
}

dependencies {
    // ✅ Spring Boot 실행 관련 의존성 추가
    implementation("org.springframework.boot:spring-boot-starter")

    // ✅ 필요시 테스트도 같이 추가 가능
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
