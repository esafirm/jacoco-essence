package nolambda.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "method")
class Method @JvmOverloads constructor(
    @field:Attribute(required = false) var name: String = "",
    @field:Element(required = false) var desc: String = "",
    @field:Element(required = false) var line: Int = 0,
    @field:ElementList(required = false, name = "counter", inline = true) var counters: List<Counter> = mutableListOf()
)