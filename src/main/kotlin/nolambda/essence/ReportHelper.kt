package nolambda.essence

import nolambda.essence.models.Report
import org.simpleframework.xml.core.Persister
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object ReportHelper {

    private const val DEFAULT_DEST_BRANCH = "master"

    fun createReportFromPath(filePath: String): Report {
        val fileContent = File(filePath).readText(Charsets.UTF_8)
        return createReport(fileContent)
    }

    fun createReport(data: String): Report {
        val serializer = Persister()
        return serializer.read(Report::class.java, data)
    }

    fun createGitDif(destinationBranch: String? = DEFAULT_DEST_BRANCH): String? {
        val destination = destinationBranch ?: DEFAULT_DEST_BRANCH
        val base = "git merge-base $destination HEAD".runCommand()?.trim()
        return "git diff --name-only $base".runCommand()
    }

    private fun String.runCommand(): String? {
        return try {
            val parts = this.split("\\s".toRegex())
            val process = ProcessBuilder(*parts.toTypedArray())
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()

            process.waitFor(60, TimeUnit.SECONDS)
            process.inputStream.bufferedReader().readText()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}