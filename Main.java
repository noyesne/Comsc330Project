package com.gradeproject;

import java.io.*;
import com.gradeproject.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * UPDATED MAIN:
 * 3/19/24
 * IMPLEMENT A DATA STUCTURE IF POSSIBLE
 * PRINT ALL OF THE STATISTICS
 * MAKE THE FILEWRITER
 * 
 * 
 * 
 */


public class Main {
    private static String groupName = "";
    private static String sectionName ="";
    private static String[] A_array;
    private static String[] f_array;
    private static double sectionGPA;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("START RUN TEST");
        String[] arr = setRunFile();
      
        for(int x = 0; x < arr.length; x++){
           
        

        
        //LinkedList<String> grpList = new LinkedList<String>();
        //Make a queue? 
        // LinkedQueue<String> grpQ = new LinkedQueue<String>();
        String[] grpArray = runGRP(arr[x]);
        System.out.println("Group Name: " + groupName + "\n");
        for(int i = 0; i < grpArray.length; i++) {
        	sectionGPA = 0.0;
	        String[][] secArray = runSEC(grpArray[i]);
	        String[] grades = new String[secArray.length];
	        for(int j = 0; j < secArray.length; j++){
	        	grades[j] = secArray[j][2];
                
	        	
	            }
	        
	   
	        
	        runStats(sectionName, grades);
	        System.out.println("AVG GPA for section " + sectionName + ": "+ String.format("%,.2f", sectionGPA));
	       
        
            }
            
        }    
                
            
       



            
           /*
            Method For printing out the files in succession:
            Needs: to create an efficient way of reading through and accessing the appropriate data
            Ideas: 
                - Make the function recursive while using a global array of SECs that is changed every time a SEC is run
                - Use the function within a for loop (but that is the same way we have it now so its dumb)
            */

        
        
    }

    /*
     * Method: setRunFile
     * Arguments: void
     * 
     * Purpose: This method will set the run file up by scanning thorugh the .RUN file to get the respective
     * 
     * 
     * 
     * 
     */

    public static String[] setRunFile(){
        System.out.println("Please enter the .RUN file you wish to run");
        Scanner kybd;
        File f;
        FileScanner fScanner;
        String[] grpArray;
        try{
            kybd = new Scanner(System.in);
            String x = kybd.nextLine();
            System.out.println();
            f = new File(x);
            kybd.close();
            fScanner = new FileScanner(f);

            fScanner.setFileParent(f);
            //String directory = fScanner.getFileParent();

            fScanner.parseRunFile(f);

            grpArray = fScanner.getArray();

            return grpArray;

            
        }catch(Exception ex){
            System.out.println("Please make sure that the file you wish to run exists\n"+ ex);
        }
        
        return grpArray = new String[10];
        
    }
    //Original Parse method wont work here.
    //Can not iterate through
    public static String[] runGRP(String s)throws FileNotFoundException{
        String[] section; 
        File f = new File(s);
        FileScanner fileScanner = new FileScanner(f);
        fileScanner.setFileParent(f);
        fileScanner.parseRunFile(f);
        groupName = fileScanner.getName();

        section = fileScanner.getArray();

        return section;


        
    }

    public static String[][] runSEC(String s) throws FileNotFoundException{
       
        File f = new File(s);
        FileScanner fileScanner = new FileScanner(f);
        
        String[][] sections = fileScanner.sectionParse(f);
        
        sectionName = fileScanner.getSection();
        
        System.out.println("Section: " + fileScanner.getSection() + " Credit Hours: " + fileScanner.getGPA());
        return sections ; 
    }

    //Attempt at making the section name
    public static void runStats(String name, String[] grades){
        List<Grade> list = new LinkedList<Grade>();
          for(int i = 0; i < grades.length; i++) {
        	  Grade grade = new Grade(grades[i]);
        	  list.add(grade);
          }
          StatisticsAnalyzer stat = new StatisticsAnalyzer();
          sectionGPA += stat.computeSectionGPA(list);
          
            
           

    }






}
