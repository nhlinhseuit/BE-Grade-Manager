
package dao;

import dto.Grade;
import dto.Student;
import dto.Subject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import util.Input;
import util.Message;
import database.FileHandler;

public class GradeManager {
    Input input;
    Message ms;
    ArrayList<Grade> listGrade;
    List<Student> listStudent;
    List<Subject> listSubject;
    FileHandler fileHandler;
    
    public GradeManager(ArrayList<Student> listStudent, ArrayList<Subject> listSubject){
        fileHandler = new FileHandler();
        this.listStudent = listStudent;
        this.listSubject = listSubject;
        input = new Input(listStudent, listSubject);
        ms = new Message();
        listGrade = new ArrayList<>();
    }
    
    public void addNewGrade(){
        int studentIdex = input.inputIdGetIndex();
        int subjectIdex = input.inputIdGetIndexSubject();
        
        if (studentIdex == -1){
            ms.showErrorMessage("Student does not exist");
            return;
        }
        
        if (subjectIdex == -1){
            ms.showErrorMessage("Subject does not exist");
            return;
        }
        
        Student student = listStudent.get(studentIdex);
        Subject subject = listSubject.get(subjectIdex);
        Grade grade = isHaveGradeAlready(student, subject);
        
        if (grade == null) {
            //trường hợp không có điểm
            float labs = input.inputGrade("labs");
            float progress = input.inputGrade("progress");
            float finalGrade = input.inputGrade("final");
            grade = new Grade(labs, progress, finalGrade, student, subject);
            listGrade.add(grade);
            ms.showSuccessMessage("Add new score for student " + student.getLastName() + " successfully!");
            fileHandler.saveListGradeToFile(listGrade);
        }
        else
        {
            //có điểm ồi, trả về poiter của grade
            boolean isUpdate = input.inputtoUpdateGrade();
            if (isUpdate) {
                float labs = input.inputGrade("labs");
                float progress = input.inputGrade("progress");
                float finalGrade = input.inputGrade("finalGrade");
                grade.setLab(labs);
                grade.setFinalExam(finalGrade);
                grade.setProgressTest(progress);
                ms.showSuccessMessage("update score for student " + student.getLastName() + "successfully!");
                fileHandler.saveListGradeToFile(listGrade);
            } else {
                ms.showSuccessMessage("Cancle update successfully");
            }
                
        }
        
        // có đồng thời cả student và subject
        
        
    }
    
    public Grade isHaveGradeAlready(Student student, Subject subject) {
        for (Grade grade: listGrade)
        {
            if (grade.getStudent() == student && grade.getSubject() == subject) 
            {
                return grade;
            }
        }
        return null;
    }
    
    public void displayStudentGradeReport(){
        int No = 1;
        
        //Tìm được học sinh mà mình muốn in kết quả
        int studentIdex = input.inputIdGetIndexStudentReport();
        if (studentIdex == -1){
            ms.showErrorMessage("Student does not exist");
            return;
        }
        Student student = listStudent.get(studentIdex);
        
        //Với mỗi môn học của học sinh thì tính điểm trung bình môn học đó
        for (Subject subject : listSubject)
        {
            Grade grade = isHaveGradeAlready(student, subject);
            //Tính điểm trung bình môn học
            grade.setAverageMark(calculateAverageMark(grade));
            //Set trạng thái pass/notpass của môn học
            grade.setStatus(statusOfGrade(grade));  
            System.out.println("okkkk");
        }
        
        //In ra các hàng kết quả môn học của học sinh
        System.out.printf("%20s %20s %20s %20s\n", "No", "Subject name", "Average Mark", "Status");
        for (Subject subject : listSubject) {
            Grade grade = isHaveGradeAlready(student, subject);
            System.out.printf("%20s %20s %20s %20s\n", No++, grade.getSubject().getName(), grade.getAverageMark(), grade.getStatus());
        }
        
    }
    
    public void displaySubjectGradeReport(){
        //Tìm được môn học mà mình muốn in kết quả
        int subjectIdex = input.inputIdGetIndexSubjectReport();
        if (subjectIdex == -1){
            ms.showErrorMessage("Subject does not exist");
            return;
        }
        Subject subject = listSubject.get(subjectIdex);
        
        //Với mỗi môn học của học sinh thì tính điểm trung bình môn học đó
        for (Student student : listStudent)
        {
            Grade grade = isHaveGradeAlready(student, subject);
            //Tính điểm trung bình môn học
            grade.setAverageMark(calculateAverageMark(grade));
//            grade.setAverageMark(2);
            //Set trạng thái pass/notpass của môn học
            grade.setStatus(statusOfGrade(grade));     
//            grade.setStatus(1); 
        }
        
        //In ra các hàng kết quả môn học của các học sinh
        System.out.printf("%25s %25s %25s %25s\n", "Student ID", "Student name", "Average Mark", "Status");
        for (Student student : listStudent) {
            Grade grade = isHaveGradeAlready(student, subject);
            System.out.printf("%25s %25s %25s %25s\n", grade.getStudent().getId(), grade.getStudent().getFirstName() + grade.getStudent().getLastName(), grade.getAverageMark(), grade.getStatus());
        }
        
    }
    
    public float calculateAverageMark(Grade grade){
        float avg = ( 30*grade.getLab() + 30*grade.getProgressTest() + 30*grade.getFinalExam() ) / 100;
        return avg;
    }
    
    public String statusOfGrade(Grade grade) {
        int status;
        if (grade.getAverageMark() > 4)
            return "Pass";
        else
            return "Not pass";
    }
    
}
