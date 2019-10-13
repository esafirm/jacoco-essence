package nolambda.essence

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.int
import nolambda.essence.diff.GitDiff

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        MainCommand().main(args)
    }
}

object DebugMain {
    @JvmStatic
    fun main(args: Array<String>) {
        MainCommand().main(
            listOf(
                "--input=src/test/resources/jacoco.xml",
                "--diff=com/esafirm/androidplayground/test/ClassToTest.kt"
            )
        )
    }
}

class MainCommand : CliktCommand() {
    private val input: String by option(help = "Jacoco XML file").prompt()
    private val diff: String? by option(help = "List of file diff. Can be acquired from git")
    private val min: Int by option(help = "Minimum percentage of the coverage").int().default(0)

    private val useGit: Boolean
            by option(
                names = *arrayOf("-g"),
                help = "Use git to create the diff. Set destination branch with --dest"
            ).flag()

    private val dest: String? by option(help = "Branch destination. This is used if --useGit or -g is enabled")

    private val gitDiff by lazy { GitDiff() }

    private fun createDiff(): String {
        if (useGit) {
            return createDiffFromGit()
        }
        return diff ?: createDiffFromGit()
    }

    private fun createDiffFromGit(): String {
        return gitDiff.createGitDif(dest) ?: throw IllegalArgumentException("Diff not found!")
    }

    override fun run() {
        val report = ReportHelper.createReportFromPath(input)
        val affectedFiles = createDiff().lines()
        val reporter = Reporter(report, min.toFloat(), affectedFiles)

        println("Project Coverage:")
        println(reporter.getTotalReport())

        println("\nClasses Coverage:")
        println(reporter.getClassReport().map { it.toString() }.reduce { acc, s -> "$acc\n$s" })
    }
}