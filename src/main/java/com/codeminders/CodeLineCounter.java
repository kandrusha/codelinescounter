package com.codeminders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CodeLineCounter {

  public Map<String, PathInfo> countCodeLines(String pathString) {
    Map<String, PathInfo> codeLinesMap = new LinkedHashMap<>();
    Path root = Paths.get(pathString);
    try (Stream<Path> paths = Files.walk(root)) {
      paths.filter(path -> Files.isRegularFile(path))
        .forEach(path -> {
          long codeLinesCount = countCodeLinesInFile(path);
          int parentsAmount = updateParents(codeLinesMap, root, path, codeLinesCount);
          PathInfo pathInfo = new PathInfo(path.getFileName().toString(), codeLinesCount, parentsAmount);
          codeLinesMap.put(path.toString(), pathInfo);
        });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return codeLinesMap;
  }

  private int updateParents(Map<String, PathInfo> codeLinesMap, Path root, Path file, long codeLines) {
    if (file.equals(root)) {
      return 0;
    }
    int parentsAmount = updateParents(codeLinesMap, root, file.getParent(), codeLines);

    PathInfo pathInfo = codeLinesMap.getOrDefault(file.getParent().toString(),
      new PathInfo(file.getParent().getFileName().toString()));

    pathInfo.setCodeLines(pathInfo.getCodeLines() + codeLines);
    pathInfo.setParentsAmount(parentsAmount);
    codeLinesMap.put(file.getParent().toString(), pathInfo);

    return parentsAmount + 1;

  }

  private long countCodeLinesInFile(Path path) {
    long codeLines = 0;
    try (BufferedReader reader = Files.newBufferedReader(path)) {
      boolean isCommentStart = false;
      String line;
      while ((line = reader.readLine()) != null) {
        if (LineValidator.isContainCode(line, isCommentStart)) {
          codeLines++;
        }
        isCommentStart = isCommentStart ? !LineValidator.isCommentEnded(line) : LineValidator.isCommentStarted(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return codeLines;
  }

}
