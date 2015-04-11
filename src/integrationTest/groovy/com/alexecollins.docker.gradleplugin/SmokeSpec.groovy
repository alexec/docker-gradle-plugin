package com.alexecollins.docker.gradleplugin

import org.gradle.tooling.BuildException

class SmokeSpec extends PluginSpec {

    def "garbage Dockerfile fails to validate"() {
        given:
        makeDefinition("garbage", "garbage", "")

        when:
        run("validate")

        then:
        def e = thrown(BuildException)
        e.cause.cause.cause.message.contains("Error while validate Dockerfile")
    }

    def "valid Dockerfile validates"() {
        given:
        makeDefinition("good", "FROM centos", "")

        when:
        run("validate")

        then:
        noExceptionThrown()
    }
}
