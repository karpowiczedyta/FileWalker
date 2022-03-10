package filewalker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IgnoreHiddenFilesAndDirectories_2 {
    public static void main(String[] args) {

        String path = Filewalker.getPath();
        List<String> ignoredFileList = ignoreHiddenFileAndDirectory(path, true);
        printTable(ignoredFileList);

    }

    static List<String> ignoreHiddenFileAndDirectory(String path, boolean ignoreHiddenFileAndDirectory) {
        File file = new File(path);
        List<String> ignoredFileList = new ArrayList<>();
        if(file.exists()) {
            File[] fileList = file.listFiles();
            for(int i = 0;  i < fileList.length; i++) {

                if (ignoreHiddenFileAndDirectory == true) {
                    if (!fileList[i].isHidden()) {
                        ignoredFileList.add(fileList[i].getName());
                    }
                }else{
                    ignoredFileList.add(fileList[i].getName());
                }
            }
        }
        return ignoredFileList;
    }

    static void printTable(List<String> table){
        for(int i = 0;  i < table.size(); i++) {
            System.out.println(table.get(i));
        }
    }


}
