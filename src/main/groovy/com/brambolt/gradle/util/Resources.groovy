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

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

import static com.brambolt.text.Templates.bind
import static com.brambolt.util.Resources.scan

class Resources {

  static File createFileFromResource(String resourcePath, String filePath, Map<String, String> bindings, String charset) {
    createFileFromResource(resourcePath, new File(filePath), bindings, charset)
  }

  static File createTemporaryFileFromResource(String resourcePath, String prefix, String suffix, Map<String, String> bindings = null, Charset charset = StandardCharsets.UTF_8, boolean deleteOnExit = true) {
    File tmpFile = File.createTempFile(prefix, suffix)
    if (deleteOnExit)
      tmpFile.deleteOnExit()
    createFileFromResource(resourcePath, tmpFile, bindings, charset)
  }

  static File createFileFromResource(String resourcePath, File targetFile, Map<String, String> bindings, String charset) {
    createFileFromResource(resourcePath, targetFile, bindings, Charset.forName(charset))
  }

  static File createFileFromResource(String resourcePath, File targetFile, Map<String, String> bindings, Charset charset) {
    if (!targetFile.exists())
      targetFile.createNewFile()
    String content = scan(resourcePath, charset)
    if (null == content)
      throw new IllegalArgumentException("No resource found: ${resourcePath}")
    targetFile.text = bind(content, bindings)
    targetFile
  }

  static final String DEFAULT_CHARSET = 'UTF-8'

  static File createFileFromResource(String resourcePath, String filePath, Map<String, String> bindings) {
    createFileFromResource(resourcePath, new File(filePath), bindings, DEFAULT_CHARSET)
  }

  static File createFileFromResource(String resourcePath, File targetFile, Map<String, String> bindings) {
    createFileFromResource(resourcePath, targetFile, bindings, DEFAULT_CHARSET)
  }
}

