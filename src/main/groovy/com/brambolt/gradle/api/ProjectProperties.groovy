package com.brambolt.gradle.api

import org.gradle.api.GradleException
import org.gradle.api.Project

import static com.brambolt.gradle.util.Properties.asProperties

trait ProjectProperties {

  abstract Project getProject()

  Properties readMandatoryProperties(String relativePath) {
    readMandatoryProperties(new Properties(), relativePath)
  }

  Properties readMandatoryProperties(Properties properties, String relativePath) {
    if (null == relativePath || relativePath.isEmpty())
      throw new GradleException("No property file provided")
    File file = getProject().file(relativePath)
    if (!file.exists())
      throw new GradleException("File not found: ${file.absolutePath}")
    asProperties(properties, file)
  }

  Properties readOptionalProperties(String relativePath) {
    readOptionalProperties(new Properties(), relativePath)
  }

  Properties readOptionalProperties(Properties properties, String relativePath) {
    if (null == relativePath || relativePath.isEmpty())
      return properties
    asProperties(properties, getProject().file(relativePath))
  }
}

