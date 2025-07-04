// ✅ 프로젝트 루트 이름 설정
rootProject.name = "jibangyoung-backend"

// ✅ 하위 모듈 include (순서 중요하지 않음)
include(
    "app", // ✅ Spring Boot 실행 진입점 모듈
    "modules:auth",
    "modules:user",
    "modules:policy",
    "core:common",
    "core:security",
    "core:exception",
    "core:support",
    "core:annotation",
    "config",
    "JobScheduler" // 또는 "job-scheduler"로 실제 디렉토리명에 맞춰 조정
)

// ✅ Gradle Plugin 해석 위치 설정
pluginManagement {
    repositories {
        gradlePluginPortal() // Gradle 공식 플러그인 저장소
        mavenCentral()       // Maven 저장소도 함께 등록
    }
}

// ✅ 공통 의존성 및 버전 관리 설정 (버전 카탈로그 사용 시)
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("myLibs") {
            from(files("gradle/libs.versions.toml")) // ✅ 버전 정의 파일 경로
        }
    }
}

// ✅ JDK 자동 설치/관리용 Foojay Resolver 설정 (Gradle 7.6 이상 권장)
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
