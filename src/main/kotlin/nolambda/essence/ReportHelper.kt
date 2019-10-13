package nolambda.essence

import nolambda.essence.models.Report
import org.simpleframework.xml.core.Persister
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object ReportHelper {

    fun createReportFromPath(filePath: String): Report {
        val fileContent = File(filePath).readText(Charsets.UTF_8)
        return createReport(fileContent)
    }

    fun createReport(data: String): Report {
        val serializer = Persister()
        return serializer.read(Report::class.java, data)
    }
}