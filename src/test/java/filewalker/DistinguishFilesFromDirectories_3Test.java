package filewalker;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DistinguishFilesFromDirectories_3Test {

    //1
    @Test
    void invokeExistsMethodShouldReturnTrue(){

//        given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        File file = new File(path);
        File[] fileList = file.listFiles();
//        when
        boolean isDirectory = fileList[2].isDirectory();
//        then
        assertTrue(isDirectory);

    }

    @Test
    void ignoreFileAndDirectories(){

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        File file = new File(path);

        //when
        List<String> distinguishedFilesList =
                DistinguishFilesFromDirectories_3.distinguishFileFromDirectory(path);

        String dir = "[dir]";
        assertTrue(distinguishedFilesList.get(2).contains(dir));

    }

}