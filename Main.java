package com.gradeproject;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;

/*
 * Things for this file to complete: 
 * --> Will be able to run for multiple run files
 * --> Get the Directory which all files are stored in
 * --> run the GRP's and respective SEC's in a orderly fasion
 *                  This might be a problem with run time, luckily file sizes are small
 * 
 * 
 * 
 * 
 * 
 * 
 */


public class Main {
    

    

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("START RUN TEST");
        String[] arr = setRunFile();
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
        System.out.println("END RUN FILE TEST \n\n");
        


            
            //String name = scan.nextLine();
            //scan.close();


            /*
             * PROBLEMS:
             *      I still do not know how to go through the array the right way
             * 
             * Author: Jack Noyes 
             * 
             * 
             */
            System.out.println("START GRP TEST");
            String[] arr2;
            for(int i = 0; i < arr.length; i++){
                arr2 = runGRP(arr[i]);
                for(int j = 0; j < arr2.length; j++){
                    System.out.println(arr2[j]);
                    String[][] arr3 = runSEC(arr2[j]);
                    System.out.println(arr3.length);
                }
            }
                
            
            System.out.println("END GRP TEST\n\n");



            System.out.println("START SEC TEST");
           


        
        
    }

    //returns an Array full of filepaths to the .GRP files
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

        section = fileScanner.getArray();

        return section;


        
    }

    public static String[][] runSEC(String s) throws FileNotFoundException{
        String[][] classes; 
        File f = new File(s);
        FileScanner fileScanner = new FileScanner(f);

        classes = fileScanner.sectionParse(f);
        System.out.println(fileScanner.getSection() + " " + fileScanner.getGPA());
        return classes; 
    }








}