package com.gradeproject;
/*
 * FileScanner Class
 * Author: Jack Noyes
 * Date: 2/9/2024
 * 
 * Purpose
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScanner {
    private String filePath = "";
    private String filename = "";
    private String fileParent ="";
    private File file;
    private String sectionName = "";
    private String gpa = "";


    String name = "";

    public String[] classNames;

    //Non-Argument Constructor: Allows user to input the .RUN file they wish
    public FileScanner() throws FileNotFoundException{
        Scanner kybd = new Scanner(System.in);
        System.out.println("Please enter the complete path of the .RUN file");
        filePath = kybd.nextLine();
        
        try{
            file = new File(filePath);
            setFileName(file);
        }catch(Exception ex){
            System.err.println("Please make sure that the file is in the right directory and ends with .run");
        }
    }
    //Constructor, File argument: This constructor will be primarily used for the .GRP & .SEC files that will be parsed through later
    public FileScanner(File f) throws FileNotFoundException{
        try{
            Scanner fileReader = new Scanner(f);
            int length = 0;
            while(fileReader.hasNext()){
                fileReader.nextLine();
                length++;
            }
            classNames = new String[length-1];
            fileReader.close();
        }catch(Exception ex){
            System.err.println("Please make sure that the file is in the right directory and ends with .run");
        }
 
    


    //Getter and Setters
    }

    
    public String getName(){
       return this.name; 
    }
    public void setFileParent(File f){
        this.fileParent = f.getParent();
    }
    public String getFileParent(){
        return this.fileParent;
    }
    public void setFileName(File f){
        this.filename = f.getName();
    }
    public String getFileName(){
        return this.filename;
    }

    public String[] getArray(){
        return classNames;
    }

    public String getSection(){
        return sectionName;
    }

    public Double getGPA(){
        return Double.parseDouble(gpa);
    }
 
    /*
     * Method: parseRunFile
     * Argument: File obj
     * Return: void
     * 
     * Purpose: This will get the individual .GRP files from the .RUN file 
     *          And this will be used to get the .SEC files form the .GRP files;
     * 
     * Notes: during the loop the hope is that the exact file can be found by using the fileParent
     * 
     */
    public void parseRunFile(File f)throws FileNotFoundException{
        Scanner fileReader = new Scanner(f);
        name = fileReader.nextLine();
        String parent = this.fileParent;

        int iterator = 0;
        while(fileReader.hasNext()){
            classNames[iterator] = parent + "\\" + (fileReader.nextLine());
            
            iterator++;

        }
        

        fileReader.close();
    }
    /*
     * Method: sectionParse
     * Arguments: File object
     * Purpose: To seperate part of the section file and put them in a 2D array
     * 
     * Structure of Array: 
     *      1st Col: Student Last Name
     *      2nd Col: Student First Name (caused by the ","  between the first and last name due how split works)
     *      2nd Col: Student ID
     *      3rd Col: Letter Grade
     */

    public String[][] sectionParse(File f) throws FileNotFoundException{
        Scanner scan = new Scanner(f);
        String s = scan.nextLine();
        System.out.println(s);
        String[] temp = s.split("  ");
        sectionName = temp[0];
        gpa = temp[1];
        int iterator = 0;
        
        while(scan.hasNextLine()){
            iterator++;
            scan.nextLine();
        }
        System.out.println(iterator);
        scan.close();
        Scanner input = new Scanner(f);
        input.nextLine();
        String[][] section = new String[iterator][4];
        for(int i = 0; i < iterator; i++){
            String x = input.nextLine();
            String[] array = x.split(",");
            section[i][0] = array[0] + "," + array[1];
            section[i][1] = array[2];
            section[i][2] = array[3];
            
        }
        input.close();
        return section;

       
    }



    /*
     * Method: ParseFileString
     * Argument: FILE object
     * Purpose: To find the directory at which the .RUN file is stored, assuming all of the 
     *          .GRP's and .SEC's are in that same directory
     * 
     * Possible Errors: Due to the '\' character being used as a formatter 
     *                  there is a possible error where it 
     *                  runs into a problem piecing the string back together
     * 
     * TURNS OUT THIS IS NOT NEEDED SINCE FILE.GETPARENT() EXISTS FML
     * 
     *
    public void parseFileString(File f){ //Question Should this be a void or is it better to just return the String
        //Need to figure how to just get the path;

         //This will get C:\Users\jackn\Documents\sample.txt
        filePath = f.getAbsolutePath(); 
        
       //This spilts the string where-ever there is a '\' (hopefully)
        String arrString[] = filePath.split("'\'"); 
        
        //For loop: Will get everything other than the actual file name since that will be produced by the constructor
        for(int i = 0; i < arrString.length - 1; i++){
            fileParent+= arrString[i] + "'\'"; //creating the prefix as a string such that it can be used to find the .GRP and .SEC files --> if they are in the same directory
        }

        }
     */
       
    

    


}