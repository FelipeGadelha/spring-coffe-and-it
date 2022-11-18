import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	id("org.flywaydb.flyway") version "9.1.2"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "br.com.felipe.gadelha.coffeandit"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
	implementation("org.flywaydb:flyway-core")
	implementation("org.springframework.kafka:spring-kafka")
}

//var dsUrl: String = System.getenv("SPRING_DATASOURCE_URL")
//var dsUsername: String = System.getenv("SPRING_DATASOURCE_USERNAME")
//var dsPassword: String = System.getenv("SPRING_DATASOURCE_PASSWORD")
//systemProperty "spring.profiles.active", System.getProperty("DEV")
//SPRING_DATASOURCE_URL
//SPRING_DATASOURCE_USERNAME
//SPRING_DATASOURCE_PASSWORD

//flyway {
//	url = "jdbc:postgresql://localhost:5432/limit-db"
//	user = "postgres"
//	password = "password"
//	cleanDisabled = true
//	locations = arrayOf("classpath:db/migration, classpath:db/data")
//	locations = arrayOf("classpath:db/migration", "classpath:db/data")
//}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
