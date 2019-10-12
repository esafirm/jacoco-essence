import io.kotlintest.matchers.collections.shouldNotBeEmpty
import io.kotlintest.matchers.floats.shouldBeGreaterThan
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import nolambda.essence.Reporter

class ReporterSpec : StringSpec({

    val reporter = Reporter(
        SpecHelper.createReport(),
        10F,
        listOf(
            "com/esafirm/androidplayground/test/ClassToTest.kt"
        )
    )

    val result = reporter.getTotalPercentage()

    println(
        """
        result: ${result.coveragePercentage}
        status: ${result.status}
    """.trimIndent()
    )

    "it should have result" {
        result.coveragePercentage shouldBeGreaterThan 0F
        result.status shouldNotBe null
    }

    "it should have class result" {
        reporter.getClassReport().shouldNotBeEmpty()

        println("Class Report: ${reporter.getClassReport()}")
    }


})