package com.alexecollins.docker.gradleplugin

class SmokeSpec extends PluginSpec {

    def "smoke"() {
        when:
        run("validate")

        then:
        assert true
    }
}
