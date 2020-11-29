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

package com.brambolt.gradle.util

import org.gradle.api.GradleException

class Assert {

  static void assertDirExists(String path, String message = '') {
    assertDirExists(new File(path), message)
  }

  static void assertDirExists(File dir, String message = '') {
    if (null == message || message.isEmpty())
      message = "Missing directory: ${dir.absolutePath}"
    if (!dir.exists() || !dir.isDirectory())
      throw new GradleException(message)
  }

  static void assertFileExists(String path, String message = '') {
    assertFileExists(new File(path), message)
  }

  static void assertFileExists(File file, String message = '') {
    if (null == message || message.isEmpty())
      message = "Missing file: ${file.absolutePath}"
    if (!file.exists() || !file.isFile())
      throw new GradleException(message)
  }

  static void assertFileContains(String path, String pattern, String message = '') {
    assertFileContains(new File(path), pattern, message)
  }

  static void assertFileContains(File file, String pattern, String message) {
    assertFileExists(file)
    if (null == message || message.isEmpty())
      message = "Pattern ${pattern} not found in file: ${file.absolutePath}"
    if (!file.text.contains(pattern))
      throw new GradleException(message)
  }
}
