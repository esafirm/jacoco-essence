import nolambda.essence.ReportHelper

object SpecHelper {

    private const val XML_FILE = "jacoco.xml"

    fun getXmlFile(testFile: String = XML_FILE): String {
        val file = ClassLoader.getSystemClassLoader().getResourceAsStream(testFile)
        val content = file ?: throw Exception()
        return content.bufferedReader(Charsets.UTF_8).readText()
    }

    fun createReport() = ReportHelper.createReport(getXmlFile())
}