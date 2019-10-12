package nolambda.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "class")
class Class(
    @field:Element val name: String,
    @field:Element val methods: List<Method>,
    @field:Element val counters: List<Counter>
)