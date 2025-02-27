package etl.process

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import java.nio.file.Files
import java.io.File

class DataCleanerSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {

    "DataCleaner.deleteFilesFromDirectory" should "delete all files in a directory" in {
        val tempDir = Files.createTempDirectory("dataCleanerTestDir")
        try {
            // Create some temporary files in the directory
            val file1 = Files.createTempFile(tempDir, "testFile1", ".txt").toFile
            val file2 = Files.createTempFile(tempDir, "testFile2", ".txt").toFile
            file1.exists() shouldBe true
            file2.exists() shouldBe true

            // Perform deletion of files in the directory
            DataCleaner.deleteFilesFromDirectory(tempDir.toString)

            // Verify the files have been deleted
            file1.exists() shouldBe false
            file2.exists() shouldBe false
        } finally {
            // Clean up the directory if needed
            Option(tempDir.toFile.listFiles).foreach(_.foreach(_.delete()))
            tempDir.toFile.delete()
        }
    }

    "DataCleaner.deleteFileInDirectory" should "delete a specific file" in {
        // Create a temporary file
        val tempFile = Files.createTempFile("dataCleanerTestFile", ".txt").toFile
        try {
            tempFile.exists() shouldBe true

            // Delete the file using the function under test
            DataCleaner.deleteFileInDirectory(tempFile.getAbsolutePath)

            // Verify the file is deleted
            tempFile.exists() shouldBe false
        } finally {
            if (tempFile.exists()) tempFile.delete()
        }
    }

    "DataCleaner.deleteFilesFromDirectory" should "handle non-existent directory gracefully" in {
        val nonExistentDir = "/non/existent/directory"
        noException should be thrownBy {
            DataCleaner.deleteFilesFromDirectory(nonExistentDir)
        }
    }

    "DataCleaner.deleteFileInDirectory" should "handle non-existent file gracefully" in {
        val nonExistentFile = "/non/existent/file.txt"
        noException should be thrownBy {
            DataCleaner.deleteFileInDirectory(nonExistentFile)
        }
    }
}
