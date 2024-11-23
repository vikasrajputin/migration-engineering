package org.openrewrite.ai.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CodeUtils {

    public String extractCodeFromResponse(String input){
        // Find the first occurrence of the triple backticks
        int startIndex = input.indexOf("```");
        if (startIndex == -1) {
            return "No code block found.";
        }

        // Find the newline after the opening triple backticks
        int languageHintEnd = input.indexOf("\n", startIndex + 3);
        if (languageHintEnd == -1) {
            return "No newline after language identifier.";
        }

        // Find the closing triple backticks
        int endIndex = input.indexOf("```", languageHintEnd);
        if (endIndex == -1) {
            return "No closing backticks found.";
        }

        // Extract the content between the language hint and the closing backticks
        return input.substring(languageHintEnd + 1, endIndex).trim();
    }
}
