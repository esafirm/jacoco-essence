package nolambda

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.int

fun main(args: List<String>) = MainCommand().main(args)

class MainCommand : CliktCommand() {
    private val input: String by option(help = "Jacoco XML file").prompt()
    private val diff: String by option(help = "List of file diff. Can be acquired from git").prompt()
    private val min: Int by option(help = "Minimum percentage of the coverage").int().default(0)

    override fun run() {
        val report = ReportHelper.createReportFromPath(input)
        val affectedFiles = diff.lines()
        val reporter = Reporter(report, min.toFloat(), affectedFiles)

        println(reporter.getClassReport())
    }
}