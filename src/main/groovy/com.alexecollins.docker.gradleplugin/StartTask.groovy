package com.alexecollins.docker.gradleplugin

import org.gradle.api.tasks.TaskAction

class StartTask extends DockerTask {

    @TaskAction
    def validate() {
        dockerOrchestrator().start()
    }
}
