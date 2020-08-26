package com.codeminders;

import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PathInfo {

    @NonNull
    private String fileName;
    @Builder.Default
    private long codeLines = 0;
    @Builder.Default
    private int parentsAmount = 0;

    public String getCodeLinesInfo() {
        return String.join("", Collections.nCopies(parentsAmount, "  ")) + fileName + ": " + codeLines;
    }
}
