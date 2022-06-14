/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import dto.Grade;
import dto.Student;
import dto.Subject;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class FileHandler {
    //final: biến không bao giờ thay đổi
    private static final String studentFile = "student.dat";
    private static final String subjectFile = "subject.dat";
    private static final String gradeFile = "grade.dat";
    
    //save to file
    public void saveListStudentToFile(ArrayList<Student> listStudent){
        try {
            FileOutputStream file = new FileOutputStream(studentFile);
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(listStudent);

            file.close();
            output.close();
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void saveListSubjectToFile(ArrayList<Subject> listSubject){
        try {
            FileOutputStream file = new FileOutputStream(subjectFile);
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(listSubject);

            file.close();
            output.close();
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void saveListGradeToFile(ArrayList<Grade> listGrade){
        try {
            FileOutputStream file = new FileOutputStream(studentFile);
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(listGrade);

            file.close();
            output.close();
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ArrayList<Student> getListStudentFromFile(){
        try {
            FileInputStream file = new FileInputStream(studentFile);
            ObjectInputStream input  = new ObjectInputStream(file);
            //casting: ép kiểu cho nó sang kiểu ArrayList<Student>, vì dưới file thì nó chỉ hiểu là object chung chung
            return (ArrayList<Student>) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public ArrayList<Subject> getListSubjectFromFile(){
        try {
            FileInputStream file = new FileInputStream(subjectFile);
            ObjectInputStream input  = new ObjectInputStream(file);
            return (ArrayList<Subject>) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public ArrayList<Grade> getListGradeFromFile(){
        try {
            FileInputStream file = new FileInputStream(studentFile);
            ObjectInputStream input  = new ObjectInputStream(file);
            //casting: ép kiểu cho nó sang kiểu ArrayList<Student>, vì dưới file thì nó chỉ hiểu là object chung chung
            return (ArrayList<Grade>) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
