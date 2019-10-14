package nolambda.essence

data class ReportResult(
    val coveragePercentage: Float,
    val status: String,
    val code: Int
) {
    override fun toString(): String {
        return """
            Coverage: $coveragePercentage %
            Status: $status
        """.trimIndent()
    }
}