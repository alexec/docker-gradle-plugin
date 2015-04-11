package com.alexecollins.docker.gradleplugin

import org.gradle.api.tasks.TaskAction

class ValidateTask extends DockerTask {

    @TaskAction
    def validate() {
        dockerOrchestrator().validate()
    }
}
