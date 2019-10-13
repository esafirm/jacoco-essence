package nolambda.essence.diff

import java.io.IOException
import java.util.concurrent.TimeUnit

class DefaultCommandExecutor : (String) -> String? {
    override fun invoke(param: String): String? {
        return param.runCommand()
    }

    private fun String.runCommand(): String? {
        return try {
            val parts = this.split("\\s".toRegex())
            val process = ProcessBuilder(*parts.toTypedArray())
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()

            process.waitFor(60, TimeUnit.SECONDS)
            process.inputStream.bufferedReader().readText()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}