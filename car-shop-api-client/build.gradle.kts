plugins {
	`java-library`
	id("io.spring.dependency-management")
	id("org.openapi.generator") version "6.2.1"
}

val openApi by configurations.creating // special dependency used only for source generation
dependencies {
	api("org.springframework.boot:spring-boot-starter-webflux")
	api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("io.swagger:swagger-annotations:1.6.6")
	implementation("com.google.code.findbugs:jsr305:3.0.2")
	implementation("org.openapitools:jackson-databind-nullable:0.2.3")
	implementation("javax.annotation:javax.annotation-api:1.3.2")
	implementation("javax.validation:validation-api:2.0.1.Final")
}

sourceSets.main {
	java.srcDir(buildDir.resolve("generate-resources/main/src/main/java")) // add the generated sources to main sources
}

openApiGenerate {
	inputSpec(file("openapi/openapi.yaml").path)
	ignoreFileOverride(file(".openapi-generator-ignore").path)
	generatorName("java")
	library("webclient")
	packageName("com.eleveo.carshop.client")
	apiPackage(packageName)
	invokerPackage(packageName)
	modelPackage(packageName.map { "$it.model" })
	generateModelTests(false)
	generateModelDocumentation(false)
	generateApiTests(false)
	generateApiDocumentation(false)
}

tasks {
	compileJava {
		dependsOn(openApiGenerate)
	}
}

// workaround for missing syntax sugar in gradle.kts - see https://github.com/gradle/gradle/issues/9268
operator fun <T> Property<T>.invoke(value: T?) = value(value)
operator fun <T> Property<T>.invoke(from: Provider<T>) = value(from)
