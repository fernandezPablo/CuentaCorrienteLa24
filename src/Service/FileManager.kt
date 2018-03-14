package Service

import java.io.File
import java.io.InputStream

class FileManager {

    private object holder {val INSTANCE = FileManager()}

    companion object fileManagerCompanion {
        val instance: FileManager by lazy { holder.INSTANCE }
    }

    fun readTextFile(path: String): String {
        val inputStream: InputStream = File(path).inputStream()

        val inputString = inputStream.bufferedReader().use { it.readText() }
        println(inputString)
        return inputString
    }


}