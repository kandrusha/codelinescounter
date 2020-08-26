package com.codeminders;

import java.util.Collections;
import java.util.Objects;

public class FileInfo {

    private String fileName;
    private long codeLines = 0;
    private int parentsAmount = 0;

    public FileInfo(String fileName, long codeLines, int parentsAmount) {
        this.fileName = fileName;
        this.codeLines = codeLines;
        this.parentsAmount = parentsAmount;
    }

    public FileInfo(String fileName) {
        this.fileName = fileName;
    }

    public String getCodeLinesInfo() {
        return String.join("", Collections.nCopies(parentsAmount, "  ")) + fileName + ": " + codeLines;
    }

    public String getFileName() {
        return fileName;
    }

    public long getCodeLines() {
        return codeLines;
    }

    public int getParentsAmount() {
        return parentsAmount;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCodeLines(long codeLines) {
        this.codeLines = codeLines;
    }

    public void setParentsAmount(int parentsAmount) {
        this.parentsAmount = parentsAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return codeLines == fileInfo.codeLines &&
          parentsAmount == fileInfo.parentsAmount &&
          Objects.equals(fileName, fileInfo.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, codeLines, parentsAmount);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
          "fileName='" + fileName + '\'' +
          ", codeLines=" + codeLines +
          ", parentsAmount=" + parentsAmount +
          '}';
    }
}
