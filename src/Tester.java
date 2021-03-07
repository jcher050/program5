/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cheryjeff
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        HashGradeBook hash1 = new HashGradeBook();

        hash1.csvImport("student.csv");

        hash1.addGrade("east", "west", 22222, "A+");

        Grade scores = hash1.findGrade("east", "west", 22222);

        System.out.println("East grade is " + scores.getLetter());

        hash1.removeGrade("will", "smith", 11111);

        hash1.csvExport("hashoutput.csv");

        TreeGradeBook tree = new TreeGradeBook();

        tree.csvImport("student.csv");

        scores = tree.findGrade("Alex", "Foster", 12345);

        System.out.println("Alex grade is " + scores.getLetter());

        double avg = tree.findAverage();

        System.out.println("TreeMap average grade= " + avg);

        tree.addGrade("south ", "north", 22222, "A+");

        scores = tree.findGrade("south ", "north", 22222);

        System.out.println("south was added to the list " + scores.getLetter());

        tree.removeGrade("south", "north", 22222);

        tree.csvExport("treeoutput.csv");

    }

}
    
    

