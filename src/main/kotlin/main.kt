import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
    val commands = listOf("git rev-parse --short HEAD", "git branch --show-current", "git log -1 --format=%cd --date=local","git config --global user.email","git commit -m 'Message")
            for(x in commands)
            {
                print(runCommand(x))
            }
}

fun runCommand(command:String): String? {
    try {
        val parts = command.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .directory(File("."))
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(60, TimeUnit.MINUTES)
        return proc.inputStream.bufferedReader().readText()
    } catch(e: IOException) {
        e.printStackTrace()
        return null
    }
}