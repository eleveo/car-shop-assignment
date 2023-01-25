plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

dependencies {
	implementation(project(":car-shop-api-client"))
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("com.github.tomakehurst:wiremock:3.0.0-beta-2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
