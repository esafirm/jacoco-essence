package nolambda.essence

data class ClassReportResult(
    val name: String,
    val coverage: Float,
    val status: String
) {
    override fun toString(): String {
        return "$name | $coverage | $status"
    }
}