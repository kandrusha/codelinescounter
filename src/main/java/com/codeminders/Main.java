package com.codeminders;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    System.out.println("Please provide file/folder name");

    Scanner scanner = new Scanner(System.in);
    String fileName = scanner.next();
    CodeLineCounter lineCounter = new CodeLineCounter();

    lineCounter.countCodeLines(fileName).forEach((s, pathInfo) ->
      System.out.println(pathInfo.getCodeLinesInfo()));
  }
}
