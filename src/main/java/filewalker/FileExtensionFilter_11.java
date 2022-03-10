package filewalker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileExtensionFilter_11 {

    public static void main(String[] args) {
        String path = getPath();
        List<String> filesAndDirectoriesWithSizeList = new ArrayList<>();
        List<String> filesDetails = moreReadableMemoryUnitsFilesAndDirectory(path, getCategories(),filesAndDirectoriesWithSizeList);
        String fileExtension = getExtensionfile();
        List<String> filteredList = filterDataToGivenByExtensionFile(filesDetails, fileExtension);
        filteredDate(filteredList);
    }

    static void filteredDate(List<String> filteredList){
        if(filteredList.size() != 0){
            printList(filteredList);
        }else{
            System.out.println("That record not found");
        }
    }


    static List<String> filterDataToGivenByExtensionFile(List<String> filesDetails, String fileExtension){
        List<String> filteredByExtensionFileList = new ArrayList<>();

        for(int i = 0 ; i < filesDetails.size(); i++){
           if(filesDetails.get(i).contains("." + fileExtension)){
               filteredByExtensionFileList.add(filesDetails.get(i));
           }
        }
        return filteredByExtensionFileList;
    }


    static List<String> moreReadableMemoryUnitsFilesAndDirectory(String path, String[][] categories, List<String> filesAndDirectoriesWithSizeList){
        File file = new File(path);
        String fullInfoAboutFilesAndDirectories = null;

        if(file.exists()) {
            File[] fileList = file.listFiles();

            for (int x = 0; x < categories.length; x++) {
                for (int y = 0; y < categories[x].length; y++) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isFile()) {
                            if (fileList[i].getName().endsWith(categories[x][y])) {
                                String category = getCorrectCategory(x);
                                fullInfoAboutFilesAndDirectories = "(" + category + ") " + fileList[i].getName()+ " ( " + getNameSuitableMomoryUnit(fileList[i].length()) + ")";
                                filesAndDirectoriesWithSizeList.add(fullInfoAboutFilesAndDirectories);
                            }
                        } else if (fileList[i].getName().startsWith(categories[x][y])) {
                            String category = getCorrectCategory(x);
                            path = fileList[i].getAbsolutePath();
                            moreReadableMemoryUnitsFilesAndDirectory(path, categories,filesAndDirectoriesWithSizeList);
                        }

                    }

                }
            }
        }
        return filesAndDirectoriesWithSizeList;
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
            memoryUnitTmp = memoryUnit / 1024 / 1024 / 1024 /1024 + " TB";
        }else{
            memoryUnitTmp = memoryUnit + " bytes";
        }
        return memoryUnitTmp;

    }

    static String  getExtensionfile() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Give the file extension after which filtering should start ");
        return scan.next();
    }


    static String [] getMemoryUnityList() {
        String[] memoryUnityList = {
                "KB", "MB", "GB", "TB", "bytes"
        };
        return memoryUnityList;
    }

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

    static void printArray(String[] array){
        System.out.println("Memory unit data: ");
        for (int i = 0 ; i < array.length ; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    static void printList(List<String> list){
        for(int i = 0 ; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

}
