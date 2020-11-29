package com.brambolt.gradle.nio;

import groovy.lang.Closure;
import org.gradle.api.GradleException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a utility class for working with zip file systems.
 */
public class ZipFileSystems {

  /**
   * Unzips a zip file and returns a zip file system object.
   * @param zipFile The zip file to open
   * @return A zip file system object fronting the parameter zip file
   * @throws IOException If unable to open the parameter zip file
   */
  public static FileSystem unzip(File zipFile) throws IOException {
    URI uri = URI.create("jar:file:///" + zipFile.getAbsolutePath().replaceAll("\\\\", "/"));
    Map<String, Object> properties = new HashMap<>();
    properties.put("create", false);
    return FileSystems.newFileSystem(uri, properties);
  }

  /**
   * Applies the parameter closure to the parameter zip file.
   * @param zipFile The zip file to process
   * @param xMessage A message to include when wrapping an exception thrown during processing
   * @param closure The processing closure to apply to the parameter zip file
   * @throws GradleException If unable to apply the closure to the zip file
   */
  public static void process(File zipFile, String xMessage, Closure<Void> closure) {
    try (FileSystem zipfs = unzip(zipFile)) {
      closure.call(zipfs);
    } catch (Exception x) {
      throw new GradleException(xMessage + " [" + zipFile.getAbsolutePath() + "]", x);
    }
  }
}
