package nolambda.essence

data class ReportResult(
    val coverage: Float,
    val status: String,
    val code: Int,
    val isFail: Boolean
)
