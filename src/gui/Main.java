
package gui;

import dao.GradeManager;
import dao.StudentManager;
import dao.SubjectManager;
import util.Color;
import util.Input;
import util.Menu;
import util.Message;

public class Main {
    public static void main(String[] args) {
        
//        sm.add();
//        sm.updateOrDelete();
        
        StudentManager studentManager = new StudentManager();
        SubjectManager subjectManager = new SubjectManager();
        GradeManager gradeManager = new GradeManager(studentManager.getListStudent(), subjectManager.getListSubject());
        int choice;
        Menu menu = new Menu();
        do {
            choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    studentManager.add();
                    break;
                case 2:
                    studentManager.updateOrDelete();
                    break;
                case 3:
                    subjectManager.add();
                    break;
                case 4: 
                    subjectManager.updateOrDelete();
                    break;
                case 5:
                    gradeManager.addNewGrade();
                    break;
                case 6:
                    gradeManager.displayStudentGradeReport();
                    break;
                case 7:
                    gradeManager.displaySubjectGradeReport();
                    break;
            }
        } while(choice != 8);
                
    }
}
