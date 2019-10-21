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

    private fun createExitCode(covPercent: Float) : Int {
        return when{
            covPercent < (minimumPercentage / 2) -> 2
            covPercent < minimumPercentage -> 1
            else -> 0
        }
    }

    private fun createCoverageStatus(code: Int): String {
        return when(code) {
            2 -> ":skull:"
            1 -> ":warning:"
            else -> ":white_check_mark:"
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
        val coverage: Float = (covered * 100F / totalInstruction)
        val code = createExitCode(coverage)
        val status = createCoverageStatus(code)
        
        return ReportResult(
            coverage = coverage,
            status = status,
            code = code,
            isFail = (code > 0)
        )
    }

    fun getClassReport(): List<ClassReportResult> {
        val jacocoClass = getAffectedJacocoClass()
        return jacocoClass.map {
            val classReport = createClassReport(it)
            val total = classReport.covered + classReport.missed
            val coverage = floor(classReport.covered / total * 100F)
            val code = createExitCode(coverage)
            val status = createCoverageStatus(code)
            ClassReportResult(
                name = it.name,
                coverage = coverage,
                status = status,
                code = code,
                isFail = (code > 0)
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