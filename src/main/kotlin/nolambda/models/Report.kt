package nolambda.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "report")
class Report @JvmOverloads constructor(
    @field:Attribute var name: String? = null,
    @field:Element(name = "group", required = false) var groups: List<Group>? = null,
    @field:ElementList(name = "package", inline = true) var packages: List<Package>? = null,
    @field:ElementList(name = "counter", inline = true) var counters: List<Counter>? = null
)