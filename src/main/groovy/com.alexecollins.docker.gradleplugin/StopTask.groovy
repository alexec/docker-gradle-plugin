package com.alexecollins.docker.gradleplugin

import org.gradle.api.tasks.TaskAction

class StopTask extends DockerTask {

    @TaskAction
    def validate() {
        dockerOrchestrator().stop()
    }
}
