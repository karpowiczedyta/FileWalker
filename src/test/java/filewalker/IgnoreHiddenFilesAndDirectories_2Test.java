package filewalker;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IgnoreHiddenFilesAndDirectories_2Test {
    //2

    @Test
    void ignoreFileAndDirectories(){

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        File file = new File(path);


        //when
        File[] files = file.listFiles();
        List<String> ignoredFilesList =
                IgnoreHiddenFilesAndDirectories_2.ignoreHiddenFileAndDirectory(path, true);

        //then
        assertNotEquals(files.length,ignoredFilesList.size());

    }

    @Test
    void doNotIgnoreFileAndDirectories(){

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        File file = new File(path);


        //when
        File[] files = file.listFiles();
        List<String> ignoredFilesList =
                IgnoreHiddenFilesAndDirectories_2.ignoreHiddenFileAndDirectory(path, false);

        //then
        assertEquals(files.length,ignoredFilesList.size());

    }

}