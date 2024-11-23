plugins {
    `java-library`
    id("org.openrewrite.rewrite") version "6.27.2"
    id("com.github.ben-manes.versions") version ("0.48.0")
    id("se.patrikerdes.use-latest-versions") version ("0.2.18")
}

repositories {
    mavenCentral()
}

group = "com.teja.rac.or"
version = "10.2.1"
description = "OpenRewrite based Refactoring as CODE"
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    rewrite(platform("org.openrewrite.recipe:rewrite-recipe-bom:2.22.0"))
    rewrite("org.openrewrite.recipe:rewrite-testing-frameworks")
    rewrite("org.openrewrite.recipe:rewrite-spring")
    rewrite("org.openrewrite.recipe:rewrite-ai-search:0.19.1")
    rewrite("org.openrewrite.recipe:rewrite-migrate-java")
    rewrite("org.openrewrite.recipe:rewrite-static-analysis")
    rewrite("org.openrewrite.recipe:rewrite-java-dependencies")
    rewrite("org.openrewrite.recipe:rewrite-logging-frameworks")
    rewrite("org.openrewrite.recipe:rewrite-java-security")
    rewrite("org.openrewrite.recipe:rewrite-cloud-suitability-analyzer:2.3.0")
    rewrite("org.openrewrite.recipe:rewrite-spring")

    api("org.springframework.boot:spring-boot-starter:2.7.13")
    api("org.springframework.boot:spring-boot-starter-web:2.7.13")
    api("log4j:log4j:1.2.17")
    api("com.google.guava:guava:20.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("junit:junit:4.13.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testImplementation("org.assertj:assertj-core:3.26.3")
}

rewrite {
    //https://docs.openrewrite.org/recipes
    //https://docs.openrewrite.org/recipes/java/spring
    //https://github.com/openrewrite/rewrite
    activeRecipe(
	//refactoring
	//"com.teja.rac.AutoChangePackage",
	//"org.openrewrite.java.AddApache2LicenseHeader",
	//"org.openrewrite.java.logging.log4j.Log4j1ToLog4j2",
	//"org.openrewrite.staticanalysis.RemoveSystemOutPrintln",
	//"org.openrewrite.java.logging.SystemOutToLogging",

	//upgrade
	//"org.openrewrite.java.migrate.UpgradeToJava17",
	//"org.openrewrite.java.testing.junit5.JUnit4to5Migration",

	//"org.openrewrite.gradle.DependencyUseStringNotation",
	//"org.openrewrite.java.RemoveUnusedImports",
	//"org.openrewrite.java.migrate.guava.NoGuava",
	//"org.openrewrite.java.testing.hamcrest.MigrateHamcrestToAssertJ",
	//"org.openrewrite.staticanalysis.CommonStaticAnalysis",
	//"org.openrewrite.java.logging.slf4j.ParameterizedLogging",

	//security
	//"org.openrewrite.java.security.JavaSecurityBestPractices",
	//"org.openrewrite.java.security.OwaspTopTen",

	//cloud
	//"org.openrewrite.cloudsuitability.FindFileIo",
	//"org.openrewrite.cloudsuitability.FindCacheUses",
	//"org.openrewrite.cloudsuitability.FindUnsuitableCode",

	//spring
	//"org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_2",

	//ai
	//"io.moderne.ai.FindCommentsLanguage",
	//"io.moderne.ai.SpellCheckCommentsInFrench",
		)
}