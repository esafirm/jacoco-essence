import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "sessioninfo")
class SessionInfo(
    @field:Element val id: String,
    @field:Element val start: Int,
    @field:Element val dump: Int
)