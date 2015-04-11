package com.alexecollins.docker.gradleplugin

import org.gradle.api.tasks.TaskAction

class BuildTask extends DockerTask {

    @TaskAction
    def validate() {
        dockerOrchestrator().build()
    }
}
