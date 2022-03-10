package filewalker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SizeOfListedFiles_7Test {

    @Test
    void sizeOfListedFiles() {

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        String[][] categories = {{".jpg", ".jpeg", ".gif", ".png"},
                {".csv", ".pdf", ".txt"},
                {".sql", ".sh"}, {"project"}};
        String zero = "(0 bytes)";
        String moreValue = "(4096 bytes)";
        String muchMoreValue = "(113549 bytes)";

        List<String> allFileList = new ArrayList<>();

        //when
        List<String> allFilesList = SizeOfListedFiles_7.showSizeOfListedFiles(path, categories, allFileList);

        //then
        assertTrue(allFilesList.get(0).endsWith(zero));
        assertTrue(allFilesList.get(3).contains(moreValue));
        assertTrue(allFilesList.get(11).contains(muchMoreValue));

    }

}