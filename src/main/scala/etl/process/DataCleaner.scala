package etl.process

object DataCleaner {

    def deleteFilesFromDirectory(directory: String): Unit = {
        val dir = new java.io.File(directory)
        if (dir.exists && dir.isDirectory) {
            dir.listFiles.foreach { file =>
                if (file.isFile && file.delete()) {
                    println(s"Deleted file: ${file.getAbsolutePath}")
                } else {
                    println(s"Failed to delete file: ${file.getAbsolutePath}")
                }
            }
        } else {
            println(s"Directory '$directory' does not exist or is not a directory")
            }
        }

    def deleteFileInDirectory(filePath: String): Unit = {
        val file = new java.io.File(filePath)
        if (file.exists && file.isFile) {
            if (file.delete()) {
                println(s"Deleted file: ${file.getAbsolutePath}")
            } else {
                println(s"Failed to delete file: ${file.getAbsolutePath}")
            }
        } else {
            println(s"File '$filePath' does not exist or is not a file")
        }
    }
}
