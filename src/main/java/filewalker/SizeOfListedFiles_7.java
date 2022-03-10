package filewalker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SizeOfListedFiles_7 {

    public static void main(String[] args) {

        String path = getPath();
        List<String> allFileList = new ArrayList<>();
        String[][] categories = getCategories();
        List<String> filesWithSizeList = showSizeOfListedFiles(path, categories, allFileList);
        printTable(filesWithSizeList);

    }
    static List<String> showSizeOfListedFiles(String path, String[][] categories, List<String> allFileList){
        File file = new File(path);

        if(file.exists()) {
            File[] fileList = file.listFiles();

            for (int x = 0; x < categories.length; x++) {
                for (int y = 0; y < categories[x].length; y++) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isFile()) {
                            if (fileList[i].getName().endsWith(categories[x][y])) {
                                String category = getCorrectCategory(x);
                                allFileList.add("(" + category + ") " + fileList[i].getName()+ " (" + fileList[i].length() + " bytes)");
                            }
                        } else if (fileList[i].getName().startsWith(categories[x][y])) {
                            String category = getCorrectCategory(x);
                            allFileList.add("[dir]" + fileList[i].getName() + " (" + fileList[i].length() + " bytes)");
                            path = fileList[i].getAbsolutePath();
                            showSizeOfListedFiles(path, categories, allFileList);
                        }
                    }

                }
            }
        }
        return allFileList;
    }

    //kategorie roznych rozszezrzen
    //1 - > dane
    //2 -> obrazy
    //3 -> skrypty

    static String [][] getCategories() {
        String[][] extensionFiles = {
                {".jpg", ".jpeg", ".gif", ".png"},
                {".csv", ".pdf", ".txt"},
                {".sql", ".sh"}, {"project"}
        };
        return extensionFiles;
    }

    static String getCorrectCategory(int level){

        String categoryName = "";

        if(level == 0){
            return categoryName = "image";
        }else if(level == 1){
            return categoryName = "data";
        }else{
            return categoryName = "script";
        }

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