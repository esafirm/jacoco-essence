package nolambda.essence

data class ClassReportResult(
    val name: String,
    val coverage: Float,
    val status: String,
    val isFail: Boolean
) {
    override fun toString(): String {
        return "$name | $coverage | $status"
    }
}