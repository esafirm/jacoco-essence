package nolambda.essence.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false, name = "counter")
class Counter @JvmOverloads constructor(
    @field:Attribute var type: String? = null,
    @field:Attribute var missed: Int = 0,
    @field:Attribute var covered: Int = 0
)