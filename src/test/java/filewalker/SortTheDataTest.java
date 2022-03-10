package filewalker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortTheDataTest {

    @Test
    void shouldReturnStrategyFromUser() {

        //given
        String[] extensionFiles = {"size", "name", "extension"};
        String strategy = "name";


        //when
        String strategyFromUser = SortTheData.getStrategyFromUser(extensionFiles, strategy);

        //then
        assertEquals(strategyFromUser, strategy);

    }

    @Test
    void shouldReturnSizeFromCreateNewFileFromFilesAndDirectoryObtainedRecursivelyFromGivenPath() {

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        String[][] extensionFiles = {
                {".jpg", ".jpeg", ".gif", ".png"},
                {".csv", ".pdf", ".txt"},
                {".sql", ".sh"}, {"project"}
        };
        List<List<SortTheData.NewFile>> allFileList = new ArrayList<>();

        //when
        List<SortTheData.NewFile> newFileFromFilesAndDirectoryObtainedRecursivelyFromGivenPath
                = SortTheData.createNewFileFromFilesAndDirectoryObtainedRecursivelyFromGivenPath(path, extensionFiles, allFileList);

        //then
        assertEquals(newFileFromFilesAndDirectoryObtainedRecursivelyFromGivenPath.size(), 4);
        assertEquals(allFileList.size(), 3);

    }

    @Test
    void printNewFileListByExtensionDesc() {

        String des = "desc";
        List<SortTheData.NewFile> table = new ArrayList();
        table.add(new SortTheData.NewFile("picture", "b.bb", 0L,"bb"));
        table.add(new SortTheData.NewFile("picture", "a.aa", 0L,"aa"));
        table.add(new SortTheData.NewFile("picture", "c.txt", 0L,"cc"));

        List<SortTheData.NewFile> tableS = new ArrayList();
        tableS.add(new SortTheData.NewFile("picture", "e.txt", 0L,"bb"));
        tableS.add(new SortTheData.NewFile("picture", "d.txt", 0L,"aa"));
        tableS.add(new SortTheData.NewFile("picture", "f.txt", 0L,"cc"));

        List<List<SortTheData.NewFile>> tableSecond = new ArrayList<>();

        tableSecond.add(table);
        tableSecond.add(tableS);

        //when
        SortTheData.printNewFileListByExtension(tableSecond, des);

        //then
        assertEquals(tableSecond.get(0).get(0).extension, "aa");

    }

    @Test
    void printNewFileListByExtensionAsc() {

        String des = "asc";
        List<SortTheData.NewFile> table = new ArrayList();
        table.add(new SortTheData.NewFile("picture", "b.bb", 0L,"bb"));
        table.add(new SortTheData.NewFile("picture", "a.aa", 0L,"aa"));
        table.add(new SortTheData.NewFile("picture", "c.txt", 0L,"cc"));

        List<SortTheData.NewFile> tableS = new ArrayList();
        tableS.add(new SortTheData.NewFile("picture", "e.txt", 0L,"bb"));
        tableS.add(new SortTheData.NewFile("picture", "d.txt", 0L,"aa"));
        tableS.add(new SortTheData.NewFile("picture", "f.txt", 0L,"cc"));

        List<List<SortTheData.NewFile>> tableSecond = new ArrayList<>();

        tableSecond.add(table);
        tableSecond.add(tableS);

        //when
        SortTheData.printNewFileListByExtension(tableSecond, des);

        //then
        assertEquals(tableSecond.get(0).get(0).extension, "cc");

    }



}

