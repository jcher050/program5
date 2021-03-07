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
    import java.util.HashMap ;
    import java.util.Iterator ;

    public class HashGradeBook implements GradeBooking {

        private HashMap<Student, Grade> gradeHashMap = new HashMap<>();

        public void csvImport(String fileName) {
            BufferedReader buffer = null;
            try {
                buffer = new BufferedReader(new FileReader(fileName));
                String line = "";
                String demlimiter = ",";

                while ((line = buffer.readLine()) != null) {
                    if (!(line.isEmpty())) {
                        String str[] = line.split(demlimiter);
                        String firstName = str[0].replace("\"", "").trim();
                        String lastName = str[1].replace("\"", "").trim();
                        int pid = Integer.parseInt(str[2].replace("\"", "").trim());
                        Student studs = new Student(firstName, lastName, pid);
                        String letter = str[3].replace("\"", "").trim();
                        Grade scores = new Grade(letter);
                        gradeHashMap.put(studs, scores);
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } finally {
                try {
                    buffer.close();
                } catch (IOException e) {

                }
            }
        }

        public void csvExport(String fileName) {
            BufferedWriter bfwriter = null;
            FileWriter fwriter = null;
            try {
                String space = " ";
                fwriter = new FileWriter(fileName);
                bfwriter = new BufferedWriter(fwriter);
                for (HashMap.Entry<Student, Grade> entry : gradeHashMap.entrySet()) {
                    Grade grad = entry.getValue();
                    Student stud = entry.getKey();
                    space = stud.getFirst() + ", " + stud.getLast() + ", " + stud.getID() + ", " + grad.getLetter() + "\n";
                    bfwriter.write(space);
                }
                System.out.println("Hash File Export completed");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bfwriter != null) {
                        bfwriter.close();
                    }
                    if (fwriter != null) {
                        fwriter.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public void csvExport(String fileName, Grade grade) {
            BufferedWriter bw = null;
            FileWriter fw = null;
            try {
                String line = " ";
                fw = new FileWriter(fileName);
                bw = new BufferedWriter(fw);
                for (HashMap.Entry<Student, Grade> entry : gradeHashMap.entrySet()) {
                    Grade gr = entry.getValue();
                    if (gr.getLetter().equals(grade.getLetter()) == false) {
                        continue;
                    }
                    Student stu = entry.getKey();
                    line = stu.getFirst() + ", " + stu.getLast() + ", " + stu.getID() + ", " + gr.getLetter() + "\n";
                    bw.write(line);
                }
                System.out.println("Done writting");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        public void addGrade(String firstName, String lastName, int pID, String grade) {
            boolean isFound = false;
            Student student = new Student(firstName, lastName, pID);
            for (HashMap.Entry<Student, Grade> entry : gradeHashMap.entrySet()) {
                Student stu = entry.getKey();
                int res = stu.compareTo(student);
                if (res == 0) {
                    entry.setValue(new Grade(grade));
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                gradeHashMap.put(new Student(firstName, lastName, pID), new Grade(grade));
            }
        }

        public Grade findGrade(String firstName, String lastName, int pID) {
            Student student = new Student(firstName, lastName, pID);
            Grade g = null;
            for (HashMap.Entry<Student, Grade> entry : gradeHashMap.entrySet()) {
                Student stu = entry.getKey();
                int res = stu.compareTo(student);
                if (res == 0) {
                    g = entry.getValue();
                    return g;
                }
            }
            if (g == null) {
                throw new IllegalArgumentException("No student found");
            }
            return g;
        }


        @Override
        public void removeGrade(String firstName, String lastName, int pID) {
            Student student = new Student(firstName, lastName, pID);
            Iterator<Student> iterator = gradeHashMap.keySet().iterator();
            while (iterator.hasNext()) {
                Student stu = iterator.next();
                int res = student.compareTo(stu);
                if (res == 0) {
                    iterator.remove();
                    System.out.println("Student was successfully removed from list");
                }
            }
        }
        public double findAverage() {
            double result = 0.0;
            if (gradeHashMap.size() == 0) {
                throw new IllegalStateException("Hashmap is empty");
            }
            for (HashMap.Entry<Student, Grade> entry : gradeHashMap.entrySet()) {
                Grade gr = entry.getValue();
                result += gr.getValue();
            }
            return result;
        }
    }
