package com.codeminders;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CodeLineCounterTest {

    private CodeLineCounter codeLineCounter = new CodeLineCounter();

    @Test
    public void shouldReturnFileWithCodeLines() {
        Path file = Paths.get("src","test","resources", "codeline", "CodeLineFile.java");
        Map<String, FileInfo> pathMap = codeLineCounter.countCodeLines(file.toAbsolutePath().toString());

        assertThat(pathMap).containsOnlyKeys(file.toAbsolutePath().toString());
        assertThat(pathMap).containsValues(new FileInfo(file.getFileName().toString(), 8, 0));
    }

    @Test
    public void shouldReturnFileDirectoriesWithCodeLines() {
        Path fileDirectory = Paths.get("src","test","resources", "codeline");
        Map<String, FileInfo> pathMap = codeLineCounter.countCodeLines(fileDirectory.toAbsolutePath().toString());

        Map<String, FileInfo> expected = buildCodeLinesMap();
        assertThat(pathMap).containsExactly(expected.entrySet().toArray(new Map.Entry[0]));
    }

    private Map<String, FileInfo> buildCodeLinesMap() {
        Map<String, FileInfo> expectedMap = new LinkedHashMap<>();
        getAbsolutePath();
        expectedMap.put(getAbsolutePath("codeline"),
                new FileInfo("codeline", 32, 0));
        expectedMap.put(getAbsolutePath("codeline", "CodeLineFile.java"),
                new FileInfo("CodeLineFile.java", 8, 1));
        expectedMap.put(getAbsolutePath("codeline", "dir1"),
                new FileInfo("dir1", 16, 1));
        expectedMap.put(getAbsolutePath("codeline", "dir1", "CodeLineFile1.java"),
                new FileInfo("CodeLineFile1.java", 8, 2));
        expectedMap.put(getAbsolutePath("codeline", "dir1", "CodeLineFile2.java"),
                new FileInfo("CodeLineFile2.java", 8, 2));
        expectedMap.put(getAbsolutePath("codeline", "dir2"),
                new FileInfo("dir2", 8, 1));
        expectedMap.put(getAbsolutePath("codeline", "dir2", "CodeLineFile3.java"),
                new FileInfo("CodeLineFile3.java", 8, 2));

        return expectedMap;
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionOnIncorrectPath() {
        codeLineCounter.countCodeLines("incorrectPath");
    }

    private String getAbsolutePath(String... args) {
        String[] pathArgs = {"test", "resources"};
        return Paths.get("src", ArrayUtils.addAll(pathArgs, args)).toAbsolutePath().toString();
    }


}
