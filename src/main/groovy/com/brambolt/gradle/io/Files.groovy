package com.brambolt.gradle.io

import java.nio.file.Paths
import java.text.SimpleDateFormat

class Files {

  static void recursiveDelete(File f) {
    java.nio.file.Files.walk(Paths.get(f.absolutePath))
      .map(
      // Path::toFile)
      { it.toFile() })
      .sorted(
      // (o1, o2) -> o2.compareTo(o1))
      { o1, o2 -> o2.compareTo(o2) })
      .forEach(
      // File.delete)
      { it.delete() })
  }

  static String createTimeStamp() {
    createTimeStamp("yyyyMMddHHmmss")
  }

  static String createTimeStamp(String format) {
    new SimpleDateFormat(format).format(new Date())
  }

  static File createTempFile(String prefix, String suffix, String content) {
    File tmp = java.nio.file.Files.createTempFile(prefix, suffix).toFile()
    tmp.deleteOnExit()
    tmp.text = content
    tmp
  }

  static InputStream stream(File file) {
    new FileInputStream(file)
  }
}
