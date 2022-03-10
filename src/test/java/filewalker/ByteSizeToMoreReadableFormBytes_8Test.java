package filewalker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ByteSizeToMoreReadableFormBytes_8Test {

    @Test
    void shouldReturnNameSuitableToMomoryUnit(){

        //given
        long memoryUnit = 1024;
        String memoryUnitExpected = 1 + " KB";

        //when
        String nameSuitableMomoryUnit = ByteSizeToMoreReadableFormBytes_8.getNameSuitableMomoryUnit(memoryUnit);

        //then
        assertEquals(nameSuitableMomoryUnit, memoryUnitExpected);

    }


    @Test
    void shouldCheckSizeOfListedFiles() {

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        String[][] categories = {{".jpg", ".jpeg", ".gif", ".png"},
                {".csv", ".pdf", ".txt"},
                {".sql", ".sh"}, {"project"}};
        String zero = "(0bytes)";
        String moreValue = "(4 KB)";
        String muchMoreValue = "(7 MB)";

        List<String> allFileList = new ArrayList<>();

        //when
        List<String> allFilesList = ByteSizeToMoreReadableFormBytes_8.moreReadableMemoryUnitsFilesAndDirectory(path, categories, allFileList);

        //then
        assertTrue(allFilesList.get(0).endsWith(zero));
        assertTrue(allFilesList.get(3).contains(moreValue));
        assertTrue(allFilesList.get(10).contains(muchMoreValue));

    }

}