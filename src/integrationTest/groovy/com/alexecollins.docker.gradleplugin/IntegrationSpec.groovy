package com.alexecollins.docker.gradleplugin


import org.gradle.tooling.BuildLauncher
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.model.GradleProject
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification


abstract class IntegrationSpec extends Specification {
    @Rule
    final TemporaryFolder dir = new TemporaryFolder()

    final OutputStream stderr = new ByteArrayOutputStream()
    final OutputStream stdout = new ByteArrayOutputStream()

    protected GradleProject run(String... tasks) {
        ProjectConnection connection = GradleConnector
                .newConnector()
                .useInstallation(new File(System.getenv("GRADLE_HOME")))
                .forProjectDirectory(dir.root)
                .connect()

        try {
            BuildLauncher builder = connection.newBuild()
            builder.standardError = stderr
            builder.standardOutput = stdout
            builder.forTasks(tasks).run()
            return connection.getModel(GradleProject)
        } finally {
            connection?.close()
        }
    }

    protected String getLocalRepo() {
        new File("build/localRepo").canonicalPath
    }

    protected File getBuildFile() {
        file('build.gradle')
    }

    private File directory(String path) {
        new File(dir.root, path).with {
            mkdirs()
            it
        }
    }

    private File file(String path) {
        def splitted = path.split('/')
        def directory = splitted.size() > 1 ? directory(splitted[0..-2].join('/')) : dir.root
        def file = new File(directory, splitted[-1])
        file.createNewFile()
        file
    }
}