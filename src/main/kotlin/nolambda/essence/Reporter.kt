package nolambda.essence

import nolambda.essence.models.Counter
import nolambda.essence.models.JacocoClass
import nolambda.essence.models.Report
import kotlin.math.floor

class Reporter(
    private val report: Report,
    private val minimumPercentage: Float,
    private val affectedClasses: List<String> = emptyList()
) {

    companion object {
        const val TYPE_INSTRUCTION = "INSTRUCTION"
    }

    private fun createCoverageStatus(coveragePercentage: Float): String {
        return when {
            coveragePercentage < (minimumPercentage / 2) -> ":skull"
            coveragePercentage < minimumPercentage -> ":warning"
            else -> ":white_check_mark:"
        }
    }

    fun getTotalPercentage(): ReportResult {
        val counter = report.counters?.find { it.type == TYPE_INSTRUCTION }
        checkNotNull(counter)

        val covered = checkNotNull(counter.covered)
        val missed = checkNotNull(counter.missed)

        val totalInstruction = covered + missed
        val coveragePercentage: Float = (covered * 100F / totalInstruction)

        return ReportResult(
            coveragePercentage = coveragePercentage,
            status = createCoverageStatus(coveragePercentage)
        )
    }

    private fun getClasssReport(clazz: JacocoClass): Counter {
        val counters = clazz.counters
        val branchCounter = counters.firstOrNull() { it.type == "BRANCH" }
        val lineCounter = counters.firstOrNull() { it.type == "LINE" }

        val resultCounter = when (branchCounter == null) {
            true -> lineCounter
            else -> branchCounter
        }

        checkNotNull(resultCounter) { "No coverage data found for ${clazz.name}" }

        return resultCounter
    }

    fun getClassReport(): List<String> {
        val jacocoClass = getAffectedJacocoClass()
        return jacocoClass.map {
            val classReport = getClasssReport(it)
            val total = classReport.covered + classReport.missed
            val coverage = floor(classReport.covered / total * 100F)
            "${it.name} | $coverage | ${createCoverageStatus(coverage)}"
        }
    }

    private fun getAffectedJacocoClass(): List<JacocoClass> {
        val affectedClass = affectedClasses.map { it.split(".").first() }
        val jacocoClassess = report.packages.asSequence().map { it.classes }.flatten()

        return jacocoClassess.filter {
            affectedClass.contains(it.name)
        }.toList()
    }


}