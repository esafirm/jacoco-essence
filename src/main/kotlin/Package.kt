import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
class Package @JvmOverloads constructor(
    @field:Attribute(required = false) var name: String? = null,
    @field:ElementList(required = false, name = "sourcefiles") var sourceFiles: List<SourceFile>? = null,
    @field:ElementList(required = false, name = "class_names") var classNames: List<Class>? = null,
    @field:ElementList(name = "counter", inline = true) var counters: List<Counter>? = null
)