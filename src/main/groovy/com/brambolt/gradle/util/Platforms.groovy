package com.brambolt.gradle.util

class Platforms {

    static Boolean isWindows() {
        System.getProperty('os.name').toLowerCase().contains('windows')
    }

    /**
     * Escapes Windows file separators for inclusion in JSON strings, Java
     * property files or the like.
     *
     * If the parameter path instead contains Unix file separators then
     * nothing is done and the parameter is returned unmodified.
     *
     * @param path The path to rewrite
     * @return The rewritten path, with backslashes escaped
     */
    static String rewritePath(String path) {
        rewritePath(path, isWindows())
    }

    static String rewritePath(String path, boolean forWindows) {
        forWindows
          ? path.replaceAll("\\\\", '\\\\\\\\')
          : path
    }
}