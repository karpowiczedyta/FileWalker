package filewalker;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListAllFilesAndCatalogsRecursively_4Test {

    @Test
    void listAllFilesAndCatalogsRecursively(){

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        File file = new File(path);
        List<List<String>> allFileList = new ArrayList<>();
        File[] fileList = file.listFiles();

        //when
        List<String> allFilesList =
                ListAllFilesAndCatalogsRecursively_4.listAllFilesAndDirectoryRecursivelyFromGivenPath(path,allFileList);

        assertEquals(4, allFilesList.size());

    }

    @Test
    void listAllFilesAndCatalogsContainsChoosenNameOfFile(){

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        File file = new File(path);
        List<List<String>>  allFileList = new ArrayList<>();
        File[] fileList = file.listFiles();

        //when
        List<String> allFilesList =
                ListAllFilesAndCatalogsRecursively_4.listAllFilesAndDirectoryRecursivelyFromGivenPath(path, allFileList);

        String dir = "[dir]";
        assertTrue(allFilesList.get(2).contains(dir));

    }

}