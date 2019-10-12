import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "line")
class Line(
    @field:Element(name = "nr") val lineNumber: Int,
    @field:Element(name = "mi") val missedInstructions: Int,
    @field:Element(name = "mb") val missedBranches: Int,
    @field:Element(name = "cb") val coveredBranches: Int
)