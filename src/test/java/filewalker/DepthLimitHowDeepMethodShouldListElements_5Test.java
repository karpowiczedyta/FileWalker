package filewalker;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DepthLimitHowDeepMethodShouldListElements_5Test {

    @Test
    void DepthLimitHowDeepMethodShouldListElements(){

        //given
        String path = "/home/karpowic/Desktop/fireworks-p/src/main/resources/ROOT/2";
        int deepLimit = 2;
        int newDeep = -1;
        File file = new File(path);
        List<String> allFileList = new ArrayList<>();
        File[] fileList = file.listFiles();

        //when
        List<String> allFilesList =
                DepthLimitHowDeepMethodShouldListElements_5.listAllFilesAndDirectoryRecursivelyFromGivenPathWithDeep(path, deepLimit, newDeep, allFileList);//?

        String dir = "[dir]";
        assertEquals(9, allFilesList.size());

    }


}