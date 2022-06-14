
package dto;

import java.io.Serializable;

public class Grade implements Serializable{
    float lab, progressTest, finalExam;
    Student student;
    Subject subject;
    float averageMark;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Grade(float lab, float progressTest, float finalExam, Student student, Subject subject) {
        this.lab = lab;
        this.progressTest = progressTest;
        this.finalExam = finalExam;
        this.student = student;
        this.subject = subject;
    }

    public float getLab() {
        return lab;
    }

    public void setLab(float lab) {
        this.lab = lab;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }

    public float getProgressTest() {
        return progressTest;
    }

    public void setProgressTest(float progressTest) {
        this.progressTest = progressTest;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    
}
