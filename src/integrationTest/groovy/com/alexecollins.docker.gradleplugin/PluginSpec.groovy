package com.alexecollins.docker.gradleplugin

import org.junit.Before

class PluginSpec extends IntegrationSpec {

    @Before
    def setup() {
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

        dir.newFolder("src", "main", "docker")
    }
}
