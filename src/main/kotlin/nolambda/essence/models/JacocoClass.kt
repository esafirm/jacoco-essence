package nolambda.essence.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "class")
class JacocoClass @JvmOverloads constructor(
    @field:Attribute var name: String = "",
    @field:ElementList(required = false, name = "method", inline = true) var methods: List<Method> = mutableListOf(),
    @field:ElementList(required = false, name = "counter", inline = true) var counters: List<Counter> = mutableListOf()
)