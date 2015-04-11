package com.alexecollins.docker.gradleplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class DockerPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('validateDocker', type: ValidateTask)
        project.task('cleanDocker', type: CleanTask)
        project.task('buildDocker', type: BuildTask)
        project.task('startDocker', type: StartTask)
        project.task('stopDocker', type: StopTask)
        project.task('pushDocker', type: PushTask)
    }
}