package nolambda.essence

import nolambda.essence.models.Counter
import nolambda.essence.models.JacocoClass
import nolambda.essence.models.Report
import kotlin.math.floor

class Reporter(
    private val report: Report,
    private val minimumPercentage: Float,
    private val minimumClassPercentage: Float,
    private val affectedClasses: List<String> = emptyList()
) {

    companion object {
        const val TYPE_INSTRUCTION = "INSTRUCTION"
    }

    private fun createCoverageStatus(
        minPercentage: Float,
        coveragePercentage: Float
    ): Pair<Boolean, String> {
        val isFailed = coveragePercentage < minPercentage
        return when {
            coveragePercentage < (minPercentage / 2) -> true to ":skull:"
            isFailed -> false to ":warning:"
            else -> false to ":white_check_mark:"
        }
    }

    private fun createClassReport(clazz: JacocoClass): Counter {
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

    fun getTotalReport(): ReportResult {
        val counter = report.counters?.find { it.type == TYPE_INSTRUCTION }
        checkNotNull(counter)

        val covered = checkNotNull(counter.covered)
        val missed = checkNotNull(counter.missed)

        val totalInstruction = covered + missed
        val coveragePercentage: Float = (covered * 100F / totalInstruction)

        val (isFailed, status) = createCoverageStatus(minimumPercentage, coveragePercentage)
        return ReportResult(
            coveragePercentage = coveragePercentage,
            status = status,
            isFail = isFailed
        )
    }

    fun getClassReport(): List<ClassReportResult> {
        val jacocoClass = getAffectedJacocoClass()
        return jacocoClass.map {
            val classReport = createClassReport(it)
            val total = classReport.covered + classReport.missed
            val coveragePercentage = floor(classReport.covered / total * 100F)

            val (isFailed, status) = createCoverageStatus(minimumClassPercentage, coveragePercentage)
            ClassReportResult(
                name = it.name,
                coverage = coveragePercentage,
                status = status,
                isFail = isFailed
            )
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