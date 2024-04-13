package jsjf;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * MAIN FILE FOR PROJECT
 * 
 * ADD MORE COMMENTS HERE
 * 
 */


public class Main {
	/*
	 * These are the global Variables used through this class and are at subject to change
	 */
    private static String groupName = "";
    private static String fileParent = "";
    private static String sectionName ="";
    private static String runName = ""; 
    private static double[][] sec_avg;
    private static LinkedList<Double> individual = new LinkedList<Double>(); //this gets the individual students grade
    public static LinkedList<String> firstaList = new LinkedList<String>();
    public static LinkedList<String> firstfList = new LinkedList<String>();
    public static LinkedList<String> aList = new LinkedList<String>();
    public static LinkedList<String> fList = new LinkedList<String>();
    private static String [] studentNums;
    private static double sectionGPA;
    private static double sectionCH;
    private static double groupGPA;
    private static int totalStudents;
    private static double totalCH;
    private static int iterator = 0;
    private static StatisticsAnalyzer stats = new StatisticsAnalyzer();
    private static boolean stopper = true;
    static int counter = 1; 
    
    public static void main(String[] args) throws FileNotFoundException {
    	
    	
        String[] arr = setRunFile(); //This gets the Array of .grp files
        if(arr == null) {
        	stopper = false; // attempted loop iterator
        	return;
        }
       
        try {
        	FileWriter fw = new FileWriter(fileParent + "\\Output"+ counter + ".txt");
        	
        	fw.write(runName + "\n\n\n\n");
        for(int x = 0; x < arr.length; x++){ //runs for the amount of files there are within the .run
        
        String[] grpArray = runGRP(arr[x]); // makes an array of SEC in the x-th spot in the RUN array
        //System.out.println("Group Name: " + groupName + "\n"); 
        sec_avg = new double[grpArray.length][arr.length]; //?
        studentNums = new String[grpArray.length]; //stores the name of each section in the grp. 
        for(int i = 0; i < grpArray.length; i++) { //loops through the length of the GRP file
        	sectionGPA = 0.0;
	        String[][] secArray = runSEC(grpArray[i]); //making a 2D array for the student's Name[i][0], student's ID [i][1], and student's letter grade [i][2];
	        
           
            
	        String[] grades = new String[secArray.length]; // stores all of the letter grades
            String[] names = new String[secArray.length]; // will store all of the names and will be used for the A and F students
            
	        for(int j = 0; j < secArray.length; j++){ //stores grades and names in respective array
	        	grades[j] = secArray[j][2];
                names[j] = secArray[j][0];
	            }
	        studentNums[i] = sectionName;  //stores each individual section name for the purposes of formatting the output file
            runStats(names, grades);
            
            groupGPA += sectionGPA * sectionCH;
            totalCH += sectionCH;
	        
            sec_avg[i][iterator] = sectionGPA; 
            
        
            } //end of grp file( all of the sections) not to be confused with and of all grp.
        	double gpaAvg = groupGPA/totalCH;
           // System.out.println(gpaAvg);
            double stDev = standardDeviation(gpaAvg);
            	fw.write("GROUP: " + groupName + " GPA: " + String.format("%,.2f", gpaAvg) + " \n");
				fw.write("Total Significant (NOT “I”, “W”, “P”, “NP” ) Students: " + totalStudents + "\nSTDEV: "  + stDev + "\n");
				for(int k = 0; k < sec_avg.length; k++) {
	                fw.write(studentNums[k] + " GPA: " +  String.format("%,.2f",sec_avg[k][iterator]) + " | ");
	                double z = stats.calculateZScore(sec_avg[k][iterator], gpaAvg, stDev );
	                fw.write("Z Score: " + z + " | ");
	                if(z >= 2.0){
	                    fw.write("is Signifacntly different: TRUE \n");
	                    break;
	                }else{
	                    if(z<= -2.0){
	                    	fw.write("is Signifacntly different: TRUE \n");
	                        break;
	                    }
	                    fw.write("is Signifacntly different: FALSE \n");
	                }
	                
	        		//System.out.println("Section "+ studentNums[k] + " is: " + stats.isSectionGPASignificantlyDifferent(sec_avg[k][iterator], gpaAvg, stDev, totalStudents));
	        	}
				fw.write("\n");
	        	
	        	aList = fixList(firstaList);
	        	if(aList.isEmpty()) {
	        		fw.write("There were no students in " + groupName + " that had more than one A(+ or -)\n");
	        	}else {
		        	fw.write("List of Students with more than one A(+ or -) in " + groupName + ": \n\n" );
		        	while(!aList.isEmpty()) {
		        		String s = aList.remove();
		        		fw.write(s + " \n");
		        	}
		        	fw.write("\n");
	        	}
	        	fList = fixList(firstfList);
	        	if(fList.isEmpty()) {
	        		fw.write("There were no students in " + groupName + " that had more than one D(+ or -) or F \n");
	        	}else {
	        		fw.write("List of Students with more than one D(+ or -) or F in " + groupName + ": \n");
		        	while(!fList.isEmpty()) {
		        		fw.write(fList.remove()+ "\n");
		        	}
	        	}
        	//end main loop
	        	fw.write("\n\n");
	        	iterator++;
        	}
        	System.out.println("RUN COMPLETE!\n file is stored as " + fileParent + "\\Output" + counter + ".txt");
        	fw.close();
        	counter++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
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
            
            
            
            
            f = new File(x);
            runName = f.getName();
            kybd.close();
            fScanner = new FileScanner(f);

            fScanner.setFileParent(f);
            fileParent = fScanner.getFileParent();
            //String directory = fScanner.getFileParent();

            fScanner.parseRunFile(f);

            grpArray = fScanner.getArray();

            return grpArray;

            
        }catch(Exception ex){
            System.out.println("Please make sure that the file you wish to run exists\n"+ ex);
        }
        
        return grpArray = new String[0];
        
    }
    /*
     * runGRP: 
     * Purpose: TO create an array of all SEC file with their specific paths, uses fileScanner class's method of parseRunFile(File f) 
     *
     */
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
        fileScanner.getstudentNumbers();
        sectionName = fileScanner.getSection();
        sectionCH = fileScanner.getGPA();
        
        //System.out.println("Section: " + fileScanner.getSection() + " Credit Hours: " + fileScanner.getGPA());
        return sections ; 
    }

    /*
     * runStats makes a list of type Grade(StatisticAnalyzer) by adding the grades from the 2D array maybe by sectionParse(FileScanner)
     * 			it then uses the StatisticAnalyzer function of computeSectionGPA with the names array and Grade list plugged in and adds it to the section GPA
     */
    public static void runStats(String[] names, String[] grades){
        List<Grade> list = new LinkedList<Grade>();
          for(int i = 0; i < grades.length; i++) {
        	  Grade grade = new Grade(grades[i]);
        	  list.add(grade);
          }
          StatisticsAnalyzer stat = new StatisticsAnalyzer();
          sectionGPA += stat.computeSectionGPA(list, names);
          System.out.println(sectionGPA);
    }
    public static double standardDeviation(double gpaAvg){
    // calculate the standard deviation
    
    double mean = gpaAvg;
    double standardDeviation = 0.0;
    int iterator = 0;
    totalStudents = individual.size();
    while(!individual.isEmpty()) {
    	double num = individual.remove();
    	standardDeviation += Math.pow((num - mean), 2);
    	
    }
   
    return Math.sqrt(standardDeviation / totalStudents);

    }
    
    //these 3 methods are called in the Statistic analyzer class as grades are being changed from letter to grade-point

    public void addElement(double e){
    	individual.add(e);
    }

    public void addA(String s){
        firstaList.add(s);
      
    }
    public void addF(String s){
        firstfList.add(s);
    }
    
    /*
     * This method will take in either firstfList or firstaList and  will check through to see if there is more than one name is the same within the respective list 
     */
    public static LinkedList<String> fixList(LinkedList<String> l){
    	LinkedList<String> temp = new LinkedList<String>();
    	while(!l.isEmpty()) {
	    	String s = l.remove();
	    	//System.out.println(l.contains(s));
	    	if(l.contains(s)) {
	    		temp.add(s);
	    	}
    	}
    	return temp;
    }





}




