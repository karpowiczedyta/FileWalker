package filewalker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SizeRangeFilesAndDirectory_10Test {

    //
    @Test
    void getDataWithTheAppropriateMemoryUnits() {

        //given
        String memoryUnityFrom = "KB";
        String memoryUnityTo = "TB";
        List<String> filesDetails = new ArrayList<>();
        filesDetails.add("(data) file_b.txt ( 12 bytes)");
        filesDetails.add("(data) file_c.txt ( 7 MB)");
        filesDetails.add("(script) script_c.sql ( 110 KB)");


        //when
        List<String> allFilesList
                = SizeRangeFilesAndDirectory_10.getDataWithTheAppropriateMemoryUnits(filesDetails, memoryUnityFrom, memoryUnityTo);
        //then
        assertEquals(allFilesList.size(),1);

    }

    @Test
    void filterTheDataByGivenData() {

        //given
        int rangeFrom = 6;
        int rangeTo = 120;
        String memoryUnityFrom = "KB";
        String memoryUnityTo = "TB";
        List<String> filesDetails = new ArrayList<>();
        filesDetails.add("(data) file_c.txt ( 7 KB)");
        filesDetails.add("(script) script_c.sql ( 110 TB)");

        //when

        List<String> strings = SizeRangeFilesAndDirectory_10.filterTheDataByGivenData(filesDetails, rangeFrom, rangeTo, memoryUnityFrom, memoryUnityTo);

        //then
        assertEquals(2,strings.size());

    }

    @Test
    void whichInIndex(){

        //given
        String[] memoryUnityList = {
                "KB", "MB", "GB", "TB", "bytes"
        };

        String memoryUnity = "KB";

        //when
        int i = SizeRangeFilesAndDirectory_10.whichInIndex(memoryUnityList, memoryUnity);

        //then
        assertEquals(i, 0);
    }

    @Test
    void getListBeetwenFromAndToValue(){

        //given
        String[] memoryUnityList = {
                "KB", "MB", "GB", "TB", "bytes"
        };

        int fromValue = 0;
        int toValue = 2;

        //when
        List<String> i = SizeRangeFilesAndDirectory_10.getListBeetwenFromAndToValue(memoryUnityList, fromValue, toValue);

        //then
        assertEquals(i.size(), 1);
    }

    @Test
    void getDataWithTheAddedAppropriateData(){

        //given
        List<String> memoryUnityList = List.of("KB", "MB", "GB");

        List<String> filesDetails = new ArrayList<>();
        filesDetails.add("(data) file_b.txt ( 12 bytes)");
        filesDetails.add("(data) file_c.txt ( 7 MB)");
        filesDetails.add("(script) script_c.sql ( 110 KB)");

        int fromValue = 0;
        int toValue = 2;

        //when
        List<String> i = SizeRangeFilesAndDirectory_10.getDataWithTheAddedAppropriateData(filesDetails, memoryUnityList);

        //then
        assertEquals(i.size(), 2);
    }

    @Test
    void connectTheArrays(){
        List<String> memoryUnityList = List.of("KB", "MB", "GB");

        List<String> filesDetails = new ArrayList<>();
        filesDetails.add("(data) file_b.txt ( 12 bytes)");
        filesDetails.add("(data) file_c.txt ( 7 MB)");
        filesDetails.add("(script) script_c.sql ( 110 KB)");

        List<String> filesDetailsAdd = new ArrayList<>();
        filesDetails.add("(data) file_b.txt ( 12 bytes)");
        filesDetails.add("(data) file_c.txt ( 7 MB)");
        filesDetails.add("(script) script_c.sql ( 110 KB)");

        int fromValue = 0;
        int toValue = 2;

        //when
        String[] i = SizeRangeFilesAndDirectory_10.connectTheArrays(filesDetails, filesDetailsAdd);

        //then
        assertEquals(i.length, 6);
    }

}