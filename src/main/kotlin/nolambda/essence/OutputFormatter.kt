package nolambda.essence

object OutputFormatter {
    fun reportResultToMarkdown(result: ReportResult): String {
        return """
            ### Project Coverage:
            Coverage: ${result.coveragePercentage} %
            Status: ${result.status}
        """.trimIndent()
    }

    fun classReportResultToMarkdown(results: List<ClassReportResult>): String {
        return """
            ### Classes Coverage:
            ${results.map { it.toString() }.reduce { acc, s -> "$acc\n$s" }}
        """.trimIndent()
    }
}