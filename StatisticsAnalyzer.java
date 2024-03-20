package com.gradeproject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsAnalyzer {
    // Map to store grades for each student
    private Map<String, List<Grade>> studentGradesMap;

    public StatisticsAnalyzer() {
        studentGradesMap = new HashMap<>();
    }

    // Method to add grades for a student
    public void addGrade(String studentId, Grade grade) {
        if (!studentGradesMap.containsKey(studentId)) {
            studentGradesMap.put(studentId, new ArrayList<>());
        }
        studentGradesMap.get(studentId).add(grade);
    }

    // Method to compute GPA for a section
    public double computeSectionGPA(List<Grade> grades) {
    	int count = 0;
    	double avg = 0.0;
    	int size = grades.size();
        while(count < grades.size()) {
        	Grade grade = grades.get(count);
        	String g = grade.getGrade();
        	char letter = g.charAt(1);
        	char symbol = g.charAt(2);;
        	
        	switch(letter) {
        	case 'A':
        		if(symbol == '-') {
        			avg += 3.7;
        		}
        		else{
        			avg += 4.0;
        		}
        		break;
        	case 'B':
        		if(symbol == '+') {
        			avg += 3.3;
        		}
        		else if(symbol == '-') {
        			avg += 2.7;
        		}
        		else {
        			avg += 3.0;
        		}
        		break;
        	case 'C': 
        		if(symbol == '+') {
        			avg += 2.3;
        		}
        		else if(symbol == '-') {
        			avg += 1.7;
        		}
        		else {
        			avg += 2.0;
        		}
        		break;
        	case 'D':
        		if(symbol == '+') {
        			avg += 1.3;
        		}
        		else if(symbol == '-') {
        			avg += 0.7;
        		}
        		else {
        			avg += 1.0;
        		}
        		break;
        	case 'F':
        		avg += 0.0;
        		break;
        	default:
        		break;
        	}
        	count++;
        }
      
        return avg/size; // Placeholder, implement actual calculation
    }

    // Method to compute GPA for a group
    public double computeGroupGPA(List<Section> sections) {
        // Combine grades from all sections in the group
        List<Grade> allGrades = new ArrayList<>();
        for (Section section : sections) {
            allGrades.addAll(section.getGrades());
        }
        // Compute group GPA
        return computeSectionGPA(allGrades);
    }

    // Method to perform Z-test and determine if section GPA is significantly different than group GPA
    public boolean isSectionGPASignificantlyDifferent(double sectionGPA, double groupGPA, double groupStdDev, int numStudents) {
        double zScore = calculateZScore(sectionGPA, groupGPA, groupStdDev, numStudents);
        return Math.abs(zScore) >= 2.0; // Significance level set to 2
    }

    // Method to calculate Z-score
    private double calculateZScore(double sectionGPA, double groupGPA, double groupStdDev, int numStudents) {
        return (sectionGPA - groupGPA) / (groupStdDev / Math.sqrt(numStudents));
    }

    // Other methods for analyzing grades and generating reports as per project requirements...

}

class Section {
    private String sectionId;
    private List<Grade> grades;

    public Section(String sectionId, List<Grade> grades) {
        this.sectionId = sectionId;
        this.grades = grades;
    }

    public List<Grade> getGrades() {
        return grades;
    }
}

class Grade {
    private String courseId;
    private String grade;

    public Grade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }
}