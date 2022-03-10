package filewalker;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FilewalkerTest {

    //1
    @Test
    void invokeExistsMethodShouldReturnTrue(){

//        given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        File file = new File(path);
//        when
        boolean exists = file.exists();
//        then
        assertTrue(exists);

    }

    @Test
    void fileListShouldNotBeEmpty(){

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        File file = new File(path);

        //when
        boolean exists = file.exists();
        File[] files = file.listFiles();

        //then
        assertNotNull(files);

    }

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

    public File[] getAllFileAndDirectoryFromGivenPath(){

        File[] fileList = {
                new File("/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2/1.txt"),
                new File("/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2/.gitIgnore.txt"),
                new File("/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2/project_a"),
                new File("/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2/22.txt"),
        };

        return fileList;

    }

}