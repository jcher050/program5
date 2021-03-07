/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cheryjeff
 */
interface GradeBooking {
    
    public void csvImport(String fileName);

    public void csvExport(String fileName);

    public void csvExport(String fileName, Grade grade);

    void addGrade(String firstName, String lastName, int pID, String grade);

    Grade findGrade(String firstName, String lastName, int pID);

    void removeGrade(String firstName, String lastName, int pID);

    double findAverage();

}
