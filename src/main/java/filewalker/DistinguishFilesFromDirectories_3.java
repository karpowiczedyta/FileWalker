package filewalker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DistinguishFilesFromDirectories_3 {

    public static void main(String[] args) {
        String aa = Filewalker.getPath();
        String path = Filewalker.getPath();
        List<String> distinguishedFileList = distinguishFileFromDirectory(path);
        printTable(distinguishedFileList);

    }

    static List<String> distinguishFileFromDirectory(String path){
        File file = new File(path);
        List<String> distinguishedFileList = new ArrayList<>();

        if(file.exists()){
            File[] fileList = file.listFiles();
            for(int i = 0;  i < fileList.length; i++) {

                if(fileList[i].isDirectory()){
                    distinguishedFileList.add("[dir]: " + fileList[i].getName());
                }else{
                    distinguishedFileList.add(fileList[i].getName());
                }

            }
        }
        return distinguishedFileList;

    }

    static void printTable(List<String> table){
        for(int i = 0;  i < table.size(); i++) {
            System.out.println(table.get(i));
        }
    }
}
