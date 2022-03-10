package filewalker;

import java.io.File;
import java.util.*;

public class SortTheData {

    public static void main(String[] args) {
        String[] strategy = getStrategy();
        printTable(strategy);
        String strategyFromUser = null;
        strategyFromUser = getStrategyFromUser(strategy, strategyFromUser);
        chooseTheStrategyOfSorting(strategyFromUser);

    }

    static void chooseTheStrategyOfSorting(String strategy){
        System.out.println(strategy);
        switch(strategy){

            case "size":
                sortBySize();
                break;

            case "name":
                sortResultByNameAlphabetically();
                break;

            case "extension":
                sortByExtension();
                break;

            default:
                System.out.println("There is no such option, enter other date");

        }

    }

    static void  sortByExtension(){
        String sortingOption = getSortingOption();
        String path = getPath();
        List<List<NewFile>> allFileList = new ArrayList<>();
        String[][] categories = getCategories();
        List<NewFile> filesWithSizeList = createNewFileFromFiles(path, categories, allFileList);
        allFileList.add(filesWithSizeList);
        System.out.println("===================");

        printNewFileListByExtension(allFileList,sortingOption);
        System.out.println("===================");

    }

    static List<NewFile> createNewFileFromFiles(String path, String[][] categories , List<List<NewFile>> allFile) {
        File file = new File(path);
        List<NewFile> allFileList = new ArrayList<>();
        if (file.exists()) {
            File[] fileList = file.listFiles();
            for (int x = 0; x < categories.length; x++) {
                for (int y = 0; y < categories[x].length; y++) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isFile()) {
                            if (fileList[i].getName().endsWith(categories[x][y])) {
                                String category = getCorrectCategory(x);
                                String[] split = fileList[i].getName().split("\\.");
                                if(fileList[i].getName().startsWith(".")){
                                    allFileList.add(new NewFile(category, fileList[i].getName(), fileList[i].length(), split[2]));
                                }else{
                                    allFileList.add(new NewFile(category, fileList[i].getName(), fileList[i].length(), split[1]));
                                }
                                System.out.println("(" + category + ") " + fileList[i].getName()+ " (" + fileList[i].length() + " bytes)");
                            }
                        } else if (fileList[i].getName().startsWith(categories[x][y])) {
                            String category = getCorrectCategory(x);
                            allFileList.add(new NewFile("dir", fileList[i].getName(), fileList[i].length(), "-1"));
                            path = fileList[i].getAbsolutePath();
                            allFile.add(createNewFileFromFiles(path,categories ,allFile));//ZLE
                        }
                    }
                }
            }

        }
        return allFileList;
    }

    static void printNewFileListByExtension(List<List<NewFile>> table, String des){
        for(int i = table.size() - 1;  i >= 0; i--) {
            for(int j = 0;  j < table.get(i).size() ; j++) {
                Collections.sort(table.get(i), new Comparator<NewFile>() {
                    @Override
                    public int compare(NewFile o1, NewFile o2) {

                         if(o1.extension != "-1" ) {
                             //rosnaco
                             if(des.equals("asc")){
                                 return  -1 * o1.extension.compareTo(o2.extension);
                             }else{
                                 return   o1.extension.compareTo(o2.extension);
                             }
                        }
                        return 0;
                    }
                });
                System.out.println(table.get(i).get(j));
            }
            System.out.println("================");
        }
    }



    static void sortBySize(){
        String sortingOption = getSortingOption();
        String path = getPath();
        List<List<NewFile>> allFileList = new ArrayList<>();
        String[][] categories = getCategories();
        List<NewFile> filesWithSizeList = createNewFileFromFilesAndDirectoryObtainedRecursivelyFromGivenPath(path, categories, allFileList);
        allFileList.add(filesWithSizeList);
        System.out.println("===================");

        printNewFileList(allFileList, sortingOption);
        System.out.println("===================");
    }


    static List<NewFile> createNewFileFromFilesAndDirectoryObtainedRecursivelyFromGivenPath(String path, String[][] categories , List<List<NewFile>> allFile) {
        File file = new File(path);
        List<NewFile> allFileList = new ArrayList<>();
        if (file.exists()) {
            File[] fileList = file.listFiles();
            for (int x = 0; x < categories.length; x++) {
                for (int y = 0; y < categories[x].length; y++) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isFile()) {
                            if (fileList[i].getName().endsWith(categories[x][y])) {
                                String category = getCorrectCategory(x);
                                allFileList.add(new NewFile(category, fileList[i].getName(), fileList[i].length()));
                                System.out.println("(" + category + ") " + fileList[i].getName()+ " (" + fileList[i].length() + " bytes)");
                            }
                        } else if (fileList[i].getName().startsWith(categories[x][y])) {
                            String category = getCorrectCategory(x);
                            allFileList.add(new NewFile("dir", fileList[i].getName(), fileList[i].length()));
                            path = fileList[i].getAbsolutePath();
                            allFile.add(createNewFileFromFilesAndDirectoryObtainedRecursivelyFromGivenPath(path,categories ,allFile));//ZLE
                        }
                    }
                }
            }

        }
        return allFileList;
    }

    static class NewFile implements Comparable{
        String categories;
        String fileName;
        Long fileSize;
        String extension;

        public NewFile(String categories, String fileName, Long fileSize) {
            this.categories = categories;
            this.fileName = fileName;
            this.fileSize = fileSize;
        }

        public NewFile(String categories, String fileName, Long fileSize, String extension) {
            this.categories = categories;
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.extension = extension;

        }

        @Override
        public int compareTo(Object o) {

            NewFile file  = ((NewFile) o);

            if(this.fileSize > file.fileSize) {
                return 1;
            }else if(this.fileSize < file.fileSize){
                return -1;
            }
            return 0;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public Long getFileSize() {
            return fileSize;
        }

        public void setFileSize(Long fileSize) {
            this.fileSize = fileSize;
        }

        @Override
        public String toString() {
            return "(" + this.categories + ") " + this.fileName+ " (" + this.fileSize + " bytes)";
        }
    }
    
    
    static void sortResultByNameAlphabetically(){
        String sortingOption = getSortingOption();
        List<String> fileList = new ArrayList<>();
        String path = getPath();
        List<List<String>> allFileList = new ArrayList<>();
        List<String> strings = listAllFilesAndDirectoryRecursivelyFromGivenPath(path, allFileList);
        allFileList.add(strings);
        printTable(allFileList, sortingOption);
    }

    static void printTable(List<List<String>> table, String option){
        for(int i = table.size() - 1;  i >= 0; i--) {
            for(int j = 0;  j < table.get(i).size() ; j++) {
                if(option.equals("desc")){
                    Collections.sort(table.get(i), (a,b) -> {
                        if(!a.startsWith("[dir]")) {
                            return -1 * a.compareTo(b);
                        }
                        return 0;
                    });
                }else{

                    Collections.sort(table.get(i), (a,b) -> {return a.compareTo(b);});
                }
                System.out.println(table.get(i).get(j));
            }
            System.out.println("================");
        }
    }

    static void printNewFileList(List<List<NewFile>> table,String option){
        for(int i = table.size() - 1;  i >= 0; i--) {
            for(int j = 0;  j < table.get(i).size() ; j++) {
                if(option.equals("desc")){
                    Collections.sort(table.get(i),Collections.reverseOrder());
                }else{
                    Collections.sort(table.get(i));
                }
                System.out.println(table.get(i).get(j));
            }
            System.out.println("================");
        }
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
                    Collections.sort(allFileList, (a,b) -> {return -1 * a.compareTo(b);});
                    allFileList.add("[dir]: " + fileList[i].getName());
                    path = fileList[i].getAbsolutePath();
                    allFile.add(listAllFilesAndDirectoryRecursivelyFromGivenPath(path,allFile));
                }
            }
        }
        return allFileList;
    }

    static List<String> sortList(List<String> list){
        Collections.sort(list);
        return list;
    }

    static List<String> fileList(List<String> list, String path){
      List<String> fileList = new ArrayList<>();
      for(int i = 0; i < list.size(); i++){
          fileList.add(path + "/" + fileList);
      }
      return fileList;
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

    static String getSortingOption(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose sorting option: ");
        System.out.println("Avaiable option asc - ascending, desc - descending");
        String option = scan.nextLine();

        if("asc".equals(option) || "desc".equals(option)){
            return option;
        }else{
            getSortingOption();
        }

        return option;
    }

    static String getStrategyFromUser(String[] strategies, String strategy){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the strategy of sorting?");
        strategy = scan.nextLine();
        String ifMatch = null;
        for(int i =  0; i < strategies.length; i++) {
            if (strategies[i].equals(strategy)) {
                ifMatch = strategy;
            }
        }

        if(ifMatch != null){
            return strategy;
        }else{
            strategy = getStrategyFromUser(strategies,strategy);

        }
        return strategy;
    }

    static String[] getStrategy(){
         String[] extensionFiles = {
              "size", "name", "extension"
        };

         return extensionFiles;
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

    static void printList(List<?> list){
        for(int i = 0 ; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    static void printTable(String[] list){
        System.out.print("Available strategies: ");
        for(int i = 0 ; i < list.length; i++){
            System.out.print(list[i]+" ");
        }
        System.out.println();
    }

}
