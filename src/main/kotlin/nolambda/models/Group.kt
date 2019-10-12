package nolambda.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "group")
class Group(
    @field:Element val name: String,
    @field:Element val groups: List<Group>,
    @field:Element val packages: List<Package>,
    @field:Element val counters: List<Counter>
)