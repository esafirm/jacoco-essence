package nolambda.essence

data class ReportResult(
    val coveragePercentage: Float,
    val status: String,
    val isFail: Boolean
)