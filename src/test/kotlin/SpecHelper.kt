import nolambda.models.Report
import org.simpleframework.xml.core.Persister

object SpecHelper {

    private const val XML_FILE = "jacoco.xml"

    fun getXmlFile(testFile: String = XML_FILE): String {
        val file = ClassLoader.getSystemClassLoader().getResourceAsStream(testFile)
        val content = file ?: throw Exception()
        return content.bufferedReader(Charsets.UTF_8).readText()
    }

    fun createReport(): Report {
        val serializer = Persister()
        return serializer.read(Report::class.java, SpecHelper.getXmlFile())
    }
}