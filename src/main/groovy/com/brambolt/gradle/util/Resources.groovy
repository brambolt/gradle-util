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

