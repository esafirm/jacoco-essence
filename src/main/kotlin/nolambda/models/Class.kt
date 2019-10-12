package nolambda.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "class")
class Class @JvmOverloads constructor(
    @field:Attribute var name: String = "",
    @field:ElementList(required = false) var methods: List<Method> = mutableListOf(),
    @field:ElementList(required = false) var counters: List<Counter> = mutableListOf()
)