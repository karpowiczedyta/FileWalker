package filewalker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListAllFilesAndCatalogsRecursively_4 {

    public static void main(String[] args) {

        String path = Filewalker.getPath();
        List<List<String>> allFileList = new ArrayList<>();
        List<String> strings = listAllFilesAndDirectoryRecursivelyFromGivenPath(path, allFileList);
        allFileList.add(strings);
        printTable(strings);
        System.out.println("=================");
        printTable2(allFileList);

    }

    static List<String> listAllFilesAndDirectoryRecursivelyFromGivenPath(String path, List<List<String>> allFile){
        File file = new File(path);
        List<String> allFileList = new ArrayList<>();
        if(file.exists()){
            File[] fileList = file.listFiles();
            for(int i = 0;  i < fileList.length; i++) {
                if (fileList[i].isFile()) {
                    allFileList.add(fileList[i].getName());
                } else {
                    allFileList.add("[dir]: " + fileList[i].getName());
                    path = fileList[i].getAbsolutePath();
                    allFile.add(listAllFilesAndDirectoryRecursivelyFromGivenPath(path,allFile));
                }
            }
        }
        return allFileList;
    }


    static void printTable(List<String> table){
        for(int i = 0;  i < table.size(); i++) {
            System.out.println(table.get(i));
        }
    }

    static void printTable2(List<List<String>> table){
        for(int i = 0;  i < table.size(); i++) {
            for(int j = 0;  j < table.get(i).size(); j++) {
                System.out.println(table.get(i).get(j));
            }
            System.out.println("================");
        }
    }
}

