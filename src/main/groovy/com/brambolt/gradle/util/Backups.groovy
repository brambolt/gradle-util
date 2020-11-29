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

import org.gradle.api.Project

/**
 * Simple utilities for creating temporary backups of files.
 */
class Backups {

  static final String BACKUP_EXTENSION = '-bkup'

  /**
   * Creates a backup copy of the parameter file.
   *
   * If the parameter file is <code>/a/b/c.txt</code>
   * then a copy will be created at <code>/a/b/c.txt-bkup</code>.
   *
   * @param project The Gradle project being executed.
   * @param file The file to back up
   */
  static void createBackup(Project project, File file) {
    File dir = file.getParentFile()
    project.copy {
      from file
      into dir
      rename { String basename -> "${basename}${BACKUP_EXTENSION}" }
    }
  }

  /**
   * Restores the parameter file from a backup copy.
   *
   * If the parameter file is <code>/a/b/c.txt</code> then the
   * contents of that file will be replaced with the contents of
   * <code>/a/b/c.txt-bkup</code>, and the backup file will be
   * removed.
   *
   * @param project The Gradle project being executed
   * @param file The file to restore from backup
   */
  static void restoreFromBackup(Project project, File file) {
    project.delete(file)
    File dir = file.getParentFile()
    File backup = new File(dir, "${file.getName()}${BACKUP_EXTENSION}")
    backup.renameTo(file)
  }
}
