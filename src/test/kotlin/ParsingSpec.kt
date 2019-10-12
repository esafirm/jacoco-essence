import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import org.simpleframework.xml.core.Persister

class ParsingSpec : StringSpec({


    val serializer = Persister()
    val result = serializer.read(Report::class.java, SpecHelper.getXmlFile())

    "it should not empty" {
        result shouldNotBe null
        result.name shouldBe "app"
        result.groups shouldBe null
        result.packages shouldNotBe null
        result.counters shouldNotBe  null

        val pack = result.packages!![0]
        pack.counters shouldNotBe null
    }
})