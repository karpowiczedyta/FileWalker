package filewalker;

import java.io.File;
import java.util.Scanner;

public class Filewalker {

    public static void main(String[] args) {

        String path = Filewalker.getPath();

//        int deepLimit = SomeTest.getDeep();

        String[][] categories = Filewalker.getCategories();

        String wordToSearching = getWordToSearching();

//        printAllDirectoryNameAndFileNameByGivenName(path);

//        ignoreHiddenFileAndDirectory(path,false);

//        distinguishFileFromDirectory(path);

        listAllFilesAndDirectoryRecursivelyFromGivenPath(path);

//        listAllFilesAndDirectoryRecursivelyFromGivenPathWithDeep(path, deepLimit, -1); //?

//        extensionFilesSplittedIntoSuitableCategories(path, categories);

//        moreReadableMemoryUnitsFilesAndDirectory(path, categories);

//        getNameSuitableMomoryUnit(10737418899L);

//        listAllFilesAndDirectoryWhichContainsGivenWord(path, categories, wordToSearching);

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

    static String getWordToSearching(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Give the word after which the filtering should be   ?");
        return scan.nextLine();
    }

    static int getDeep(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Give the deep limit?");
        return scan.nextInt();
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

    static void listAllFilesAndDirectoryWhichContainsGivenWord(String path, String[][] categories, String keyWordToFiltering){
        File file = new File(path);

        if(file.exists()) {
            File[] fileList = file.listFiles();

            for (int x = 0; x < categories.length; x++) {
                for (int y = 0; y < categories[x].length; y++) {
                    for (int i = 0; i < fileList.length; i++) {
                            if (fileList[i].isFile()) {
                                if (fileList[i].getName().endsWith(categories[x][y])) {
                                    if (fileList[i].getName().contains(keyWordToFiltering)) {
                                        String category = getCorrectCategory(x);
                                        System.out.println("(" + category + ") " + fileList[i].getName() + " (" + fileList[i].length() + " bytes)");
                                    }
                                }
                            } else if (fileList[i].getName().startsWith(categories[x][y])) {
                                if (fileList[i].getName().contains(keyWordToFiltering)) {
                                    String category = getCorrectCategory(x);
                                    System.out.println("[dir]" + fileList[i].getName() + " (" + fileList[i].length() + " bytes)");
                                    path = fileList[i].getAbsolutePath();
                                    listAllFilesAndDirectoryWhichContainsGivenWord(path, categories,keyWordToFiltering);
                                }
                            }
                        }
                    }
                }
            }
        }




    static void moreReadableMemoryUnitsFilesAndDirectory(String path, String[][] categories){
        File file = new File(path);

        if(file.exists()) {
            File[] fileList = file.listFiles();

            for (int x = 0; x < categories.length; x++) {
                for (int y = 0; y < categories[x].length; y++) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isFile()) {
                            if (fileList[i].getName().endsWith(categories[x][y])) {
                                String category = getCorrectCategory(x);
                                System.out.println("(" + category + ") " + fileList[i].getName()+ " (" + getNameSuitableMomoryUnit(fileList[i].length()) + " )");
                            }
                        } else if (fileList[i].getName().startsWith(categories[x][y])) {
                            String category = getCorrectCategory(x);
                            System.out.println("[dir]" + fileList[i].getName() + " (" + getNameSuitableMomoryUnit(fileList[i].length()) + " )");
                            path = fileList[i].getAbsolutePath();
                            moreReadableMemoryUnitsFilesAndDirectory(path, categories);
                        }
                    }

                }
            }
        }
    }



    static void extensionFilesSplittedIntoSuitableCategories(String path, String[][] categories){
        File file = new File(path);

        if(file.exists()) {
            File[] fileList = file.listFiles();

            for (int x = 0; x < categories.length; x++) {
                for (int y = 0; y < categories[x].length; y++) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isFile()) {
                            if (fileList[i].getName().endsWith(categories[x][y])) {
                                String category = getCorrectCategory(x);
                                System.out.println("(" + category + ") " + fileList[i].getName()+ " (" + fileList[i].length() + " bytes)");
                            }
                        } else if (fileList[i].getName().startsWith(categories[x][y])) {
                            String category = getCorrectCategory(x);
                            System.out.println("[dir]" + fileList[i].getName() + " (" +fileList[i].length() + " bytes)");
                            path = fileList[i].getAbsolutePath();
                            extensionFilesSplittedIntoSuitableCategories(path, categories);
                        }
                    }

                }
            }
        }
    }


    static void listAllFilesAndDirectoryRecursivelyFromGivenPathWithDeep(String path, int deep, int newDeep){
        File file = new File(path);
        newDeep ++;
        if(file.exists()){
            File[] fileList = file.listFiles();
            for(int i = 0;  i < fileList.length; i++) {
                if (fileList[i].isFile()) {
                    System.out.println(fileList[i].getName());
                } else {
                    if(newDeep < deep) {
                        System.out.println("[dir]: " + fileList[i].getName());
                        path = fileList[i].getAbsolutePath();
                        listAllFilesAndDirectoryRecursivelyFromGivenPathWithDeep(path, deep, newDeep);
                    }
                }
            }

        }
    }

    static void listAllFilesAndDirectoryRecursivelyFromGivenPath(String path){
        File file = new File(path);
        if(file.exists()){
            File[] fileList = file.listFiles();
            for(int i = 0;  i < fileList.length; i++) {
                if (fileList[i].isFile()) {
                    System.out.println(fileList[i].getName());
                } else {
                    System.out.println("[dir]: " + fileList[i].getName());
                     path = fileList[i].getAbsolutePath();
                    listAllFilesAndDirectoryRecursivelyFromGivenPath(path);
                }
            }

        }
    }

    static void distinguishFileFromDirectory(String path){
     File file = new File(path);

     if(file.exists()){
         File[] fileList = file.listFiles();
         for(int i = 0;  i < fileList.length; i++) {

             if(fileList[i].isDirectory()){
                 System.out.println("directory: " + fileList[i].getName());
             }else{
                 System.out.println("file: " + fileList[i].getName());
             }

         }
     }

    }

    static void ignoreHiddenFileAndDirectory(String path, boolean ignoreHiddenFileAndDirectory) {
        File file = new File(path);
        if(file.exists()) {
            File[] fileList = file.listFiles();
            for(int i = 0;  i < fileList.length; i++) {

                if (ignoreHiddenFileAndDirectory == true) {
                    if (!fileList[i].isHidden() && ignoreHiddenFileAndDirectory == true) {
                        System.out.println(fileList[i]);
                    }
                }else{
                    System.out.println(fileList[i]);
                }
            }
        }
    }

//    static void printAllDirectoryNameAndFileNameByGivenName(String path) {
//        File file = new File(path);
//        if(file.exists()) {
//            File[] fileList = file.listFiles();
//
//            for(int i = 0;  i < fileList.length; i++){
//                System.out.println(fileList[i].getName());
//            }
//        }
//    }


}




