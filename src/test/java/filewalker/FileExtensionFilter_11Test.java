package filewalker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileExtensionFilter_11Test {

    @Test
    void filterDataToGivenByExtensionFile(){

        //given
        List<String> filesDetails = new ArrayList<>();
        filesDetails.add("(data) file_b.txt ( 12 bytes)");
        filesDetails.add("(data) file_c.sql ( 7 MB)");
        filesDetails.add("(script) script_c.sql ( 110 KB)");
        filesDetails.add("(data) file_b.png ( 12 bytes)");
        filesDetails.add("(data) file_c.png ( 7 MB)");
        filesDetails.add("(script) script_c.sql ( 110 KB)");

       String fileExtension = "sql";

        //when
        List<String> filteredData = FileExtensionFilter_11.filterDataToGivenByExtensionFile(filesDetails, fileExtension);

        //then
        assertEquals(filteredData.size(), 3);
    }

}