package nolambda.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "sourcefile")
class SourceFile(
    @field:Element val name: String,
    @field:Element val counters: List<Counter>
)