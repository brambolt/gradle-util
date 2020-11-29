package com.brambolt.gradle.api.artifacts

trait Classpaths {

    String classpathFor(configuration) {
        configuration.asPath
            .split(System.getProperty('path.separator'))
            .collect({ new File(it).toURI() }).join(' ')
    }
}