import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "method")
class Method(
    @field:Element val name: String,
    @field:Element val desc: String,
    @field:Element val line: Int,
    @field:Element val counters: List<Counter>
)