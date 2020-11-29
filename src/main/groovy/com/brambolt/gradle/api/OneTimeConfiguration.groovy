package com.brambolt.gradle.api

/**
 * Many tasks carry out configuration actions that must not be repeated,
 * and some builds result in multiple configuration closures being passed
 * to instantiated tasks. This utility trait assists with the correct
 * implementation of tasks with these characteristics.
 *
 * The `configureDefaults` method is intended to be executed once, before
 * the normal build configuration sequence applies the first configuration
 * closure.
 *
 * The `configureOnce` method is also intended to be applied once, but
 * after the normal build configuration sequence applies the first
 * configuration closure.
 */
trait OneTimeConfiguration {

  TaskConfigurationState taskConfigurationState = TaskConfigurationState.NOT_CONFIGURED

  boolean isNotYetConfigured() {
    TaskConfigurationState.NOT_CONFIGURED.equals(taskConfigurationState)
  }

  void setConfigured() {
    taskConfigurationState = TaskConfigurationState.CONFIGURED
  }

  void maybeConfigureDefaults() {
    if (isNotYetConfigured())
      configureDefaults()
  }

  void configureDefaults() {}

  void maybeConfigureOnce() {
    if (isNotYetConfigured()) {
      configureOnce()
      setConfigured()
    }
  }

  void configureOnce() {}
}