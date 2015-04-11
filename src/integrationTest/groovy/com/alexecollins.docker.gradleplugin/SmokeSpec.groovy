package com.alexecollins.docker.gradleplugin

import org.gradle.tooling.BuildException

class SmokeSpec extends PluginSpec {

    def "garbage Dockerfile fails to validate"() {
        given:
        makeDefinition("garbage", "garbage", "")

        when:
        run("validateDocker")

        then:
        def e = thrown(BuildException)
        e.cause.cause.cause.message.contains("Error while validate Dockerfile")
    }

    def "valid Dockerfile validates"() {
        given:
        makeGoodDefinition()

        when:
        run("validateDocker")

        then:
        noExceptionThrown()
    }

    def "clean with valid definition passes"(){
        given:
        makeGoodDefinition()

        when:
        run("cleanDocker")

        then:
        noExceptionThrown()
    }

    def "build with valid definition builds"() {
        given:
        makeGoodDefinition()

        when:
        run("buildDocker")

        then:
        noExceptionThrown()
    }

    def "start with valid definition starts"() {
        given:
        makeGoodDefinition()

        when:
        run("startDocker")

        then:
        noExceptionThrown()
    }

    def "stop with valid definition stop"() {
        given:
        makeGoodDefinition()

        when:
        run("stopDocker")

        then:
        noExceptionThrown()
    }

    private File makeGoodDefinition() {
        makeDefinition("good", "FROM centos", "")
    }
}
