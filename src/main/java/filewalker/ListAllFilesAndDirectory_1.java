package filewalker;

import java.io.File;

public class ListAllFilesAndDirectory_1 {
    public static void main(String[] args) {

        String path = Filewalker.getPath();
        printAllDirectoryNameAndFileNameByGivenPath(path);

    }

    static void printAllDirectoryNameAndFileNameByGivenPath(String path) {
        File file = new File(path);
        if(file.exists()) {
            File[] fileList = file.listFiles();

            for(int i = 0;  i < fileList.length; i++){
                System.out.println(fileList[i].getName());
            }
        }
    }

}
