package com.brambolt.gradle.buildscripts

import org.gradle.api.Project

class Classpaths {

  static void setVersion(Project project, String prefix, String version, File buildScript) {
    buildScript.text = buildScript.text.replaceAll(
      "classpath '${prefix}:[^']*'",
      "classpath '${prefix}:${version}'")
  }
}
