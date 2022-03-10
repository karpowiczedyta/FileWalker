package filewalker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepthLimitHowDeepMethodShouldListElements_5 {

    public static void main(String[] args) {

        String path = getPath();
        int deepLimit = getDeep();
        List<String> allFileList = new ArrayList<>();
        List<String> listWithDepthLimit = listAllFilesAndDirectoryRecursivelyFromGivenPathWithDeep(path, deepLimit, -1, allFileList);//?
        printTable(listWithDepthLimit);
    }

    static List<String> listAllFilesAndDirectoryRecursivelyFromGivenPathWithDeep(String path, int deep, int newDeep,List<String> allFileList){
        File file = new File(path);
        newDeep ++;
        if(file.exists()){
            File[] fileList = file.listFiles();
            for(int i = 0;  i < fileList.length; i++) {
                if (fileList[i].isFile()) {
                    allFileList.add(fileList[i].getName());
                } else {
                    if(newDeep < deep) {
                        allFileList.add("[dir]: " + fileList[i].getName());
                        path = fileList[i].getAbsolutePath();
                        listAllFilesAndDirectoryRecursivelyFromGivenPathWithDeep(path, deep, newDeep, allFileList);
                    }
                }
            }
        }
        return allFileList;
    }

    static int getDeep(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Give the deep limit?");
        return scan.nextInt();
    }

    static String getPath(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Give the path which you want read?");
        return scan.nextLine();
    }

    static void printTable(List<String> table){
        for(int i = 0;  i < table.size(); i++) {
            System.out.println(table.get(i));
        }
    }
}
