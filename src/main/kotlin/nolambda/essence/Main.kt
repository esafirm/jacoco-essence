package nolambda.essence

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.float
import nolambda.essence.diff.GitDiff
import kotlin.system.exitProcess

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

    private val min: Float by option(help = "Minimum percentage of the coverage").float().default(0F)

    private val minClass: Float? by option(
        help = """
        Minimum percentage of the coverage, if not defined will use minimum coverage value
    """.trimIndent()
    ).float()

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
        val reporter = Reporter(report, min, minClass ?: min, affectedFiles)

        val reportResult = reporter.getTotalReport()
        println(OutputFormatter.reportResultToMarkdown(reportResult))

        val classReportResult = reporter.getClassReport()
        println("\n")
        println(OutputFormatter.classReportResultToMarkdown(classReportResult))

        // Exit 1 if any of coverage fail
        if (reportResult.isFail) {
            exitProcess(1)
        }
        if (classReportResult.any { it.isFail }) {
            exitProcess(1)
        }
    }
}
