package filewalker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SizeRangeFilesAndDirectory_10 {

    public static void main(String[] args) {
        String path = getPath();
        String[] memoryUnityList = getMemoryUnityList();
        printArray(memoryUnityList);
        int rangeFrom = getRangeFrom();
        String memoryUnityFrom = getMemoryUnity(memoryUnityList);
        int rangeTo = getRangeTo();//do
        String memoryUnityTo = getMemoryUnity(memoryUnityList);
        List<String> filesAndDirectoriesWithSizeList = new ArrayList<>();
        List<String> filesDetails = moreReadableMemoryUnitsFilesAndDirectory(path, getCategories(),filesAndDirectoriesWithSizeList);

//        System.out.println("=========================================");
        for(int i = 0 ; i < filesDetails.size(); i++){
            System.out.println(filesDetails.get(i));
        }

        List<String> dataWithTheAppropriateMemoryUnits = getDataWithTheAppropriateMemoryUnits(filesDetails, memoryUnityFrom, memoryUnityTo);
//        System.out.println("po filtrowaniu tylko kb i gb=========================================");
        for(int i = 0 ; i < dataWithTheAppropriateMemoryUnits.size(); i++){
            System.out.println(dataWithTheAppropriateMemoryUnits.get(i));
        }

        List<String> filesListAfterFiltering = filterTheDataByGivenData(dataWithTheAppropriateMemoryUnits, rangeFrom, rangeTo, memoryUnityFrom, memoryUnityTo);

//        System.out.println("po filtrowaniu ostatecznym =========================================");
        for(int i = 0 ; i < filesListAfterFiltering.size(); i++){
            System.out.println(filesListAfterFiltering.get(i));
        }
        int from = whichInIndex(memoryUnityList, memoryUnityFrom);
        int to = whichInIndex(memoryUnityList, memoryUnityTo);

        List<String> beetwenFromAndToValueList = getListBeetwenFromAndToValue(memoryUnityList, from, to);
        System.out.println(from);
        System.out.println(to);
//        System.out.println("posreodkurzeczy =========================================");
        for(int i = 0 ; i < beetwenFromAndToValueList.size(); i++){
            System.out.println(beetwenFromAndToValueList.get(i));
        }

        List<String> dataWithTheAddedAppropriateData = getDataWithTheAddedAppropriateData(filesDetails, beetwenFromAndToValueList);
//        System.out.println("posreodkurzeczyCalepliki =========================================");
        for(int i = 0 ; i < dataWithTheAddedAppropriateData.size(); i++){
            System.out.println(dataWithTheAddedAppropriateData.get(i));
        }

        String[] fromToRangeList = connectTheArrays(filesListAfterFiltering, dataWithTheAddedAppropriateData);
//        System.out.println("osatteczna lista po filtorwasniach =========================================");
        for(int i = 0 ; i < fromToRangeList.length; i++){
            System.out.println(fromToRangeList[i]);
        }

    }

    static String[] connectTheArrays(List<String> theEndsOfTheRange, List<String> theMiddleOfTheRange){

        String[] filteredList = new String[theEndsOfTheRange.size() + theMiddleOfTheRange.size()];

        Object[] endOfTheRangeArray = (Object[]) theEndsOfTheRange.toArray();
        Object[] middleOfTheRange = (Object[]) theMiddleOfTheRange.toArray();

        System.arraycopy(endOfTheRangeArray, 0, filteredList, 0, endOfTheRangeArray.length);
        System.arraycopy(middleOfTheRange, 0, filteredList, endOfTheRangeArray.length, middleOfTheRange.length);
        return  filteredList;

    }

    static List<String> getDataWithTheAddedAppropriateData(List<String> filesDetails, List<String> addedDataList){
        List<String> fileDetailsAfterFiltering = new ArrayList<>();
        String memoryUnity;

        for(int j = 0 ; j < addedDataList.size(); j++) {
            memoryUnity = addedDataList.get(j) + ")";
            for(int i = 0 ; i < filesDetails.size(); i++){

                if(filesDetails.get(i).endsWith(memoryUnity) ){
                    fileDetailsAfterFiltering.add(filesDetails.get(i));
                }
            }
        }

        return fileDetailsAfterFiltering;

    }

    static List<String> getListBeetwenFromAndToValue(String[] unitMemoryList, int fromValue, int toValue){
        List<String> fromToList = new ArrayList<>();

        for(int i = 0; i < unitMemoryList.length; i++) {
            if(i > fromValue && i < toValue ){
                fromToList.add( unitMemoryList[i]);
            }
        }

        return fromToList;

    }

    static int whichInIndex(String[] array, String memoryUnity){

        int index = -1;
        for(int i = 0; i < array.length; i++){

            if (array[i].equals(memoryUnity)){
                index = i;
                return index;
            }
        }
        return index;
    }

    static List<String> filterTheDataByGivenData(List<String> filesDetails, int rangeFrom, int rangeTo, String memoryUnityFrom, String memoryUnityTo){

        List<String> filesListAfterFiltering = new ArrayList<>();

        for(int i = 0 ; i < filesDetails.size(); i++) {

            String[] splitedRecords = filesDetails.get(i).split(" ");
            Integer fileSize = Integer.parseInt(splitedRecords[3]);

            if (fileSize >= rangeFrom && splitedRecords[4].equals(memoryUnityFrom + ")")
                    || fileSize <= rangeTo && splitedRecords[4].equals(memoryUnityTo + ")")
            ) {
                filesListAfterFiltering.add(filesDetails.get(i));
            }
        }
        return filesListAfterFiltering;

    }


    static List<String> getDataWithTheAppropriateMemoryUnits(List<String> filesDetails, String memoryUnityFrom, String memoryUnityTo){
        List<String> fileDetailsAfterFiltering = new ArrayList<>();
        memoryUnityFrom = memoryUnityFrom + ")";
        memoryUnityTo = memoryUnityTo + ")";
        String from;
        for(int i = 0 ; i < filesDetails.size(); i++){
            if(filesDetails.get(i).endsWith(memoryUnityFrom) || filesDetails.get(i).endsWith(memoryUnityTo)){
                fileDetailsAfterFiltering.add(filesDetails.get(i));
            }
        }
        return fileDetailsAfterFiltering;
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

    static String [][] getCategories() {
        String[][] extensionFiles = {
                {".jpg", ".jpeg", ".gif", ".png"},
                {".csv", ".pdf", ".txt"},
                {".sql", ".sh"}, {"project"}
        };
        return extensionFiles;
    }

    static int getRangeFrom(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter from what size you want to start sorting the results ?");
        return scan.nextInt();
    }

    static int getRangeTo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter to what size you want to end the sorting of the results?");
        return scan.nextInt();
    }

    static String getMemoryUnity(String[] array){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter memory unity");
        String memoryUnity = scan.next();

        for(int i = 0; i < array.length; i++){

            if (array[i].equals(memoryUnity)){
                return memoryUnity;
            }
        }
        return memoryUnity;
    }


    static String [] getMemoryUnityList() {
        String[] memoryUnityList = {
                "KB", "MB", "GB", "TB", "bytes"
        };
        return memoryUnityList;
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

    static void printList(List<String> array){
        System.out.println("Memory unit data: ");
        for (int i = 0 ; i < array.size() ; i++){
            System.out.print(array.get(i) + " ");
        }
        System.out.println();
    }

}
