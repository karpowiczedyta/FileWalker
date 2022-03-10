package filewalker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterResultsToGivenNamePattern_9Test {

    @Test
    void sizeOfListedFiles() {

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        String[][] categories = {{".jpg", ".jpeg", ".gif", ".png"},
                {".csv", ".pdf", ".txt"},
                {".sql", ".sh"}, {"project"}};
        String wordToSearching = "a";
        List<String> allFileList = new ArrayList<>();

        //when
        List<String> allFilesList
                = FilterResultsToGivenNamePattern_9.listAllFilesAndDirectoryWhichContainsGivenWord(path, categories, wordToSearching, allFileList);

        //then
        assertEquals(allFileList.size(),3);

    }


}