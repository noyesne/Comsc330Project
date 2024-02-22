import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.gradeproject.FileScanner;
public class TestFileScanner {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the file");
        File file = new File(input.nextLine());
        FileScanner f = new FileScanner(file);
        input.close();  
        f.parseRunFile(file);
        String[] testArray = f.getArray();
        f.setFileParent(file);
        System.out.println("Directory: " + f.getFileParent());
        System.out.println("Semester: " + f.getName());

        for(int i = 0; i < testArray.length; i++){
            System.out.println(f.getFileParent() + testArray[i]);
        }





        System.out.println(f.getFileParent() + testArray[0]);

        File file2 = new File(f.getFileParent() + testArray[0]); 
        FileScanner f1 = new FileScanner(file2);
        f1.setFileParent(file2);
        System.out.println("Directory: " + f1.getFileParent());
        System.out.println("File Name: " + file2.getName());
        f1.parseRunFile(file2); 

        System.out.println(f1.getName());
        String[] arr1 = f1.getArray();
        for(int i = 0; i < arr1.length; i++){
            System.out.println(arr1[i]);
        }

        File file3 = new File(arr1[0]);
        FileScanner f2 = new FileScanner(file3);
        f2.setFileParent(file3);
        System.out.println("\n");
        System.out.println("Directory: " + f2.getFileParent());
        System.out.println("File Name: " + file3.getName());
        
        String[][] section = f2.sectionParse(file3);
        for(int i = 0; i < section.length; i++){
            System.out.println("Student Name: " + section[i][0] + " Student ID: "+ section[i][1] + " Letter Grade: " + section[i][2] + "\n\n");
        }

       
       
    }
}
