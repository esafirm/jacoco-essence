package nolambda

import nolambda.models.Report

class Reporter(
    private val report: Report,
    private val minimumPercentage: Float
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
}