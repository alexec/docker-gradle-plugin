package com.alexecollins.docker.gradleplugin

class SmokeSpec extends IntegrationSpec {

    def "smoke"() {
        setup:
        buildFile << """
			apply plugin: 'com.alexecollins.docker.gradleplugin'

            buildscript {
                repositories {
                    maven {
                        url "file://${localRepo}"
                        mavenCentral()
                    }
                }
                dependencies {
                    classpath 'com.alexecollins.docker:docker-gradle-plugin:1.0.0-SNAPSHOT'
                }
            configurations.all {
                resolutionStrategy.cacheDynamicVersionsFor 0, 'seconds'
            }
        }
		"""

        when:
        println(buildFile.text)
        run("validate")

        then:
        assert true
    }
}
