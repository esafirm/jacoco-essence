package nolambda.essence

object OutputFormatter {
    fun reportResultToMarkdown(result: ReportResult): String {
        return """
            ### Project Coverage:
            Coverage: ${result.coverage} %
            Status: ${result.status}
        """.trimIndent()
    }

    fun classReportResultToMarkdown(results: List<ClassReportResult>): String {

        val classReportString = when (results.isEmpty()) {
            true -> "No class reports"
            else -> results.map { it.toString() }.reduce { acc, s -> "$acc\n$s" }
        }

        return """
            ### Classes Coverage:
            $classReportString
        """.trimIndent()
    }
}