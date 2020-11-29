/*
 * Copyright 2017-2020 Brambolt ehf.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
