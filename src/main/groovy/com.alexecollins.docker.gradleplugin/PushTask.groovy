package com.alexecollins.docker.gradleplugin

import org.gradle.api.tasks.TaskAction

class PushTask extends DockerTask {

    @TaskAction
    def validate() {
        dockerOrchestrator().push()
    }
}
