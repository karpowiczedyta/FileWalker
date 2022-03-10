package filewalker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarkKnownExtensionsIntoCategories_6Test {


    @Test
    void getCorrectCategory() {
        //given
        int level = 1;
        String category = "data";

        //when
        String correctCategory = MarkKnownExtensionsIntoCategories_6.getCorrectCategory(level);

        //then
        assertEquals(category, correctCategory);

    }


    @Test
    void fileExtensionSplittedIntoCategories() {

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        String[][] categories = {{".jpg", ".jpeg", ".gif", ".png"},
                {".csv", ".pdf", ".txt"},
                {".sql", ".sh"}, {"project"}};
        String data = "(data)";
        String image = "(image)";
        String dir = "[dir]";

        List<String> allFileList = new ArrayList<>();

        //when
        List<String> allFilesList = MarkKnownExtensionsIntoCategories_6.extensionFilesSplittedIntoSuitableCategories(path, categories, allFileList);

        //then
        assertTrue(allFilesList.get(0).contains(data));
        assertTrue(allFilesList.get(7).contains(image));
        assertTrue(allFilesList.get(3).contains(dir));

    }

}