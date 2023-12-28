package grade;

import java.util.Scanner;
import java.text.DecimalFormat;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int NUM_SUBJECTS = 6;
        int MAX_MARKS_PER_SUBJECT = 100;
        String[] sub={"Language","English","Mathematics","Computer Science","Physics","Chemistry"};
        int totalMarks = 0;

        for (int i = 0; i < NUM_SUBJECTS; i++) {
            System.out.print("Enter marks obtained in " + sub[i] + " (out of " + MAX_MARKS_PER_SUBJECT + "): ");
            int marks = scanner.nextInt();

            if (marks < 0 || marks > MAX_MARKS_PER_SUBJECT) {
                System.out.println("Invalid marks. Please enter marks between 0 and " + MAX_MARKS_PER_SUBJECT);
                i--; 
            } 
            else {
                totalMarks += marks;
            }
        }

        double averagePercentage = (double) totalMarks / (NUM_SUBJECTS * MAX_MARKS_PER_SUBJECT) * 100;
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedAveragePercentage = df.format(averagePercentage);

        
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + formattedAveragePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
