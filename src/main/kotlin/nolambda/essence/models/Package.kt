package nolambda.essence.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
class Package @JvmOverloads constructor(
    @field:Attribute(required = false) var name: String? = null,
    @field:ElementList(required = false, name = "sourcefiles") var sourceFiles: List<SourceFile>? = null,
    @field:ElementList(name = "class", inline = true) var classes: List<JacocoClass> = mutableListOf(),
    @field:ElementList(name = "counter", inline = true) var counters: List<Counter> = mutableListOf()
)