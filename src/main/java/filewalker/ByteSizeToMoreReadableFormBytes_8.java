package filewalker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ByteSizeToMoreReadableFormBytes_8 {

    public static void main(String[] args) {
        String path = getPath();
        String[][] categories = getCategories();
        List<String> allFileList = new ArrayList<>();
        List<String> moreReadeableList = moreReadableMemoryUnitsFilesAndDirectory(path, categories, allFileList);
        printTable(moreReadeableList);
    }


    static List<String> moreReadableMemoryUnitsFilesAndDirectory(String path, String[][] categories, List<String> allFileList){
        File file = new File(path);

        if(file.exists()) {
            File[] fileList = file.listFiles();

            for (int x = 0; x < categories.length; x++) {
                for (int y = 0; y < categories[x].length; y++) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isFile()) {
                            if (fileList[i].getName().endsWith(categories[x][y])) {
                                String category = getCorrectCategory(x);
                                allFileList.add("(" + category + ") " + fileList[i].getName()+ " (" + getNameSuitableMomoryUnit(fileList[i].length()) + ")");
                            }
                        } else if (fileList[i].getName().startsWith(categories[x][y])) {
                            String category = getCorrectCategory(x);
                            allFileList.add("[dir]" + fileList[i].getName() + " (" + getNameSuitableMomoryUnit(fileList[i].length()) + ")");
                            path = fileList[i].getAbsolutePath();
                            moreReadableMemoryUnitsFilesAndDirectory(path, categories, allFileList);
                        }
                    }

                }
            }
        }
        return allFileList;
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


    static String getNameSuitableMomoryUnit(long memoryUnit){

        String memoryUnitTmp = String.valueOf(memoryUnit);
        int length = memoryUnitTmp.length();

        if(length >= 4 && length <= 7 &&  memoryUnit < 1048576){
            memoryUnitTmp = memoryUnit / 1024 + " KB";
        }else if(length >= 7 && length <= 10 &&  memoryUnit < 1073741824){
            memoryUnitTmp = memoryUnit / 1024 / 1024+ " MB";
        }else if(length >= 10 && length <= 13 &&  memoryUnit < 1099511627776L){
            memoryUnitTmp = memoryUnit / 1024 / 1024 / 1024+ " GB";
        }else if(length >= 13){
            memoryUnitTmp = memoryUnit / 1024 / 1024 / 1024 /1024 + "TB";
        }else{
            memoryUnitTmp = memoryUnit + "bytes";
        }
        return memoryUnitTmp;

    }

    //kategorie roznych rozszezrzen
    // 1 - >  dane
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
