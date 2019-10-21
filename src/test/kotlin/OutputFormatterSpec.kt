import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import nolambda.essence.ClassReportResult
import nolambda.essence.OutputFormatter
import nolambda.essence.ReportResult

class OutputFormatterSpec : StringSpec({
    "It should format report result properly" {
        val reportResult = ReportResult(10F, "good", 0, false)

        OutputFormatter.reportResultToMarkdown(reportResult) shouldBe """
            ### Project Coverage:
            Coverage: 10.0 %
            Status: good
        """.trimIndent()
    }

    "It should format class report result properly" {
        val classReportResult = listOf(ClassReportResult(
            name = "ClassN",
            coverage = 100F,
            status = "Bagus",
            code = 0,
            isFail = false
        ))

        OutputFormatter.classReportResultToMarkdown(classReportResult) shouldBe """
            ### Classes Coverage:
            ClassN | 100.0 | Bagus
        """.trimIndent()
    }
})