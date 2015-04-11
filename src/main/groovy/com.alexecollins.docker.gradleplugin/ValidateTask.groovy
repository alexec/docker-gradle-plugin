package com.alexecollins.docker.gradleplugin

import com.alexecollins.docker.orchestration.DockerOrchestrator
import com.alexecollins.docker.orchestration.model.BuildFlag
import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.DockerException
import com.github.dockerjava.core.DockerClientBuilder
import com.github.dockerjava.core.DockerClientConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ValidateTask extends DefaultTask {

    @TaskAction
    def validate() {
        dockerOrchestrator().validate()
    }

    private DockerOrchestrator dockerOrchestrator() {
        def p = properties()
        def baseDir = project.projectDir

        return DockerOrchestrator
                .builder()
                .docker(dockerClient())
                .src(new File(baseDir, "src/main/docker"))
                .workDir(new File(project.buildDir, "docker"))
                .rootDir(baseDir)
                .user(p.getProperty("user.name"))
                .project(baseDir.name)
                .properties(p)
                .buildFlags(buildFlags())
                .build();
    }

    private Set<BuildFlag> buildFlags() {
        new HashSet<BuildFlag>();
    }

    private DockerClient dockerClient() throws DockerException {
        DockerClientBuilder.getInstance(DockerClientConfig.createDefaultConfigBuilder().build()).build()
    }

    private Properties properties() {
        System.getProperties()
    }
}
