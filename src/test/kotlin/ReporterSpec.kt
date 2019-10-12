import io.kotlintest.specs.StringSpec
import nolambda.Reporter

class ReporterSpec : StringSpec({

    val reporter = Reporter(
        SpecHelper.createReport(),
        10F
    )

    val result = reporter.getTotalPercentage()

    println("""
        result: ${result.coveragePercentage}
        status: ${result.status}
    """.trimIndent())
})