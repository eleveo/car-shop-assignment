plugins {
	id("org.springframework.boot") version "3.0.2" apply false
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.eleveo"
version = "0.0.1-SNAPSHOT"

allprojects {
	repositories {
		mavenCentral()
	}

	tasks.withType<JavaCompile> {
		options {
			release(17)
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// workaround for missing syntax sugar in gradle.kts - see https://github.com/gradle/gradle/issues/9268
operator fun <T : AbstractOptions> T.invoke(config: T.() -> Unit) = apply(config)
operator fun <T> Property<T>.invoke(value: T?) = value(value)
operator fun <T> Property<T>.invoke(from: Provider<T>) = value(from)
