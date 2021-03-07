/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cheryjeff
 */
    import java.io.BufferedReader ;

    import java.io.BufferedWriter ;

    import java.io.FileNotFoundException ;

    import java.io.FileReader ;

    import java.io.FileWriter ;

    import java.io.IOException ;

    import java.util.Iterator ;

    import java.util.Map.Entry ;

    import java.util.TreeMap ;

    public class TreeGradeBook implements GradeBooking {

        private TreeMap<Student, Grade> gradeTreeMap = new TreeMap<>();

        public void csvImport(String fileName) {

            BufferedReader br = null;

            try {

                br = new BufferedReader(new FileReader(fileName));

                String line = "";

                String demlimiter = ",";

                while ((line = br.readLine()) != null) {

                    if (!(line.isEmpty())) {

                        String str[] = line.split(demlimiter);

                        String firstName = str[0].replace("\"", "").trim();

                        String lastName = str[1].replace("\"", "").trim();

                        int pid = Integer.parseInt(str[2].replace("\"", "").trim());

                        Student student = new Student(firstName, lastName, pid);

                        String grade = str[3].replace("\"", "").trim();

                        Grade g = new Grade(grade);

                        gradeTreeMap.put(student, g);

                    }

                }

            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } finally {

                try {

                    br.close();

                } catch (IOException e) {
                }

            }

        }

        public void csvExport(String fileName) {

            BufferedWriter bw = null;

            FileWriter fw = null;

            try {

                String line = " ";

                fw = new FileWriter(fileName);

                bw = new BufferedWriter(fw);

                for (Entry<Student, Grade> entry : gradeTreeMap.entrySet()) {

                    Student stu = entry.getKey();

                    Grade gr = entry.getValue();

                    line = stu.getFirst() + ", " + stu.getLast() + ", " + stu.getID() + ", " + gr.getLetter() + "\n";

                    bw.write(line);

                }

                System.out.println("Tree File Export completed");

            } catch (IOException e) {
            } finally {

                try {

                    if (bw != null) {
                        bw.close();
                    }

                    if (fw != null) {
                        fw.close();
                    }

                } catch (IOException ex) {
                }

            }

        }

        public void csvExport(String fileName, Grade grade) {


        }

        public void addGrade(String firstName, String lastName, int pID, String grade) {

            boolean isFound = false;

            Student Studs = new Student(firstName, lastName, pID);

            for (Entry<Student, Grade> entry : gradeTreeMap.entrySet()) {

                Student stu = entry.getKey();

                int res = stu.compareTo(Studs);

                if (res == 0) {


                    entry.setValue(new Grade(grade));

                    isFound = true;

                    break;

                }

            }

            if (!isFound) {

                gradeTreeMap.put(new Student(firstName, lastName, pID), new Grade(grade));

            }

        }

        public Grade findGrade(String firstName, String lastName, int pID) {

            Student student = new Student(firstName, lastName, pID);

            Grade g = null;

            for (Entry<Student, Grade> entry : gradeTreeMap.entrySet()) {

                Student key = entry.getKey();

                int res = key.compareTo(student);

                if (res == 0) {

                    g = entry.getValue();

                    return g;

                }

            }

            if (g == null) {

                throw new IllegalArgumentException("student was not in the list");

            }

            return null;

        }

        public void removeGrade(String firstName, String lastName, int pID) {

            Student student = new Student(firstName, lastName, pID);

            Iterator<Student> it = gradeTreeMap.keySet().iterator();

            while (it.hasNext()) {

                Student stu = it.next();

                int res = stu.compareTo(student);

                if (res == 0) {

                    it.remove();

                }

            }

        }

        public double findAverage() {

            double result = 0.0;

            if (gradeTreeMap.size() == 0) {

                throw new IllegalStateException("TreeMap empty");

            }

            for (Entry<Student, Grade> entry : gradeTreeMap.entrySet()) {

                Grade gr = entry.getValue();

                result += gr.getValue();

            }

            return result;

        }

    }
