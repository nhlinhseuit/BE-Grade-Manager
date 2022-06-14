
package util;

import dto.Grade;
import dto.Student;
import dto.Subject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Input {
    
    Scanner sc;
    ArrayList<Student> listStudent;
    ArrayList<Subject> listSubject;
    Message ms;
            
    public Input(ArrayList<Student> listStudent, ArrayList<Subject> listSubject){
        sc = new Scanner(System.in);
        this.listStudent = listStudent;
        this.listSubject = listSubject;
        ms = new Message();
    }
    
    public float inputGrade(String label){
        int grade = 0;
        boolean isEnterString = true;
        do
        {   
            try {
                System.out.println("Enter " + label + ": ");
                grade = Integer.parseInt(sc.nextLine());
                isEnterString = false;
                
                if (grade < 0 || grade > 10)
                    ms.showErrorMessage(label + " is from 0 -> 10");
            } 
            catch(Exception e) {
                ms.showErrorMessage("Not allow string");
            }
        } while(grade < 0 || grade > 10 || isEnterString);
        return grade;
    }
    
    public Subject inputSubject() {
        String id = inputSubjectId();
        String name = inputString("subject's name");
        int credit = inputCredit();
        
        Subject subject = new Subject(id, name, credit);
        return subject;
    }
    
    public String inputSubjectId() {
        boolean isDuplicate = true;
        String id;
        
        do{
            System.out.println("Enter id: ");
            id = sc.nextLine();
            isDuplicate = checkDuplicateSubject(id);
            
            if (isDuplicate)
                System.err.println("Not allow duplicate!");
            if (id.isEmpty())
                System.err.println("Not allow empty!!");
            
        }while(id.isEmpty() || isDuplicate);
        
        return id;
    }
    
    public boolean checkDuplicateSubject(String id){
        for(Subject std : listSubject){//for each đặc biệt
            //sử dụng getId thay food.id để thể hiện tính đóng gói (encapsulation)
            if(std.getId().equals(id))
                return true;
        }
        return false;
    }
    
    public int inputCredit() {
        int credit = 0;
        boolean isEnterString = true;
        do
        {   
            try {
                System.out.println("Enter credit: ");
                credit = Integer.parseInt(sc.nextLine());
                isEnterString = false;
                
                if (credit < 0)
                    ms.showErrorMessage("Credit is greater than 0");
            } 
            catch(Exception e) {
                ms.showErrorMessage("Not allow string");
            }
        } while(credit < 0 || isEnterString);
        return credit;
    }
    
    
    
    
    
    public int inputUpdateOrDelete() {
        int choice;
        do {
            System.out.println("Select: ");
            System.out.println("1. Update");
            System.out.println("2. Delete");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine());
            if(choice != 1 && choice != 2) {
                ms.showErrorMessage("Please try again!");
            }
        } while(choice != 1 && choice != 2);
        return choice;
    }
    
    public int inputIdGetIndexStudentReport(){
        String id = inputString("Student id to display result: ");
        for (Student std:listStudent)
            if (std.getId().equals(id))
                return listStudent.indexOf(std);
        return -1;
    }
    
    public int inputIdGetIndexSubjectReport(){
        String id = inputString("Subject id to display result: ");
        for (Subject std : listSubject)
            if (std.getId().equals(id))
                return listSubject.indexOf(std);
        return -1;
    }
    
    public int inputIdGetIndex(){
        String id = inputString("Student id to update or delete");
        for (Student std:listStudent)
            if (std.getId().equals(id))
                return listStudent.indexOf(std);
        return -1;
    }
    
    public int inputIdGetIndexSubject(){
        String id = inputString("Subject id to update or delete");
        for (Subject std:listSubject)
            if (std.getId().equals(id))
                return listSubject.indexOf(std);
        return -1;
    }
    
    public Student inputStudent(){
        String id = inputID();
        String firstName = inputString("firstName");
        String lastName = inputString("lastName");
        String dateOfBirth = inputDate();
        String gender = inputGender();
        String phone = inputPhone();
        String email = inputEmail();
        
        Student newStudent = new Student(id, firstName, lastName, gender, dateOfBirth, email, phone);
        return newStudent;
    }
    
    public String inputPhone(){
        String phone;
        do {
            System.out.println("Enter phone: ");
            phone = sc.nextLine();
            if (phone.length() < 10 || phone.length() > 12)
                System.err.println("Please enter phone of 10 - 12 digits!");
        } while(phone.length() < 10 || phone.length() > 12);
        
        return phone;
    }
    
    public String inputGender(){
        String gender;
        do {
            System.out.println("Enter gender: ");
            gender = sc.nextLine();
            if (!gender.equals("male") && !gender.equals("female"))
                System.err.println("Please try again!");
        } while(!gender.equals("male") && !gender.equals("female"));
        
        return gender;
    }
    
    public String inputDate(){
        String date;
        do{
            System.out.println("Enter date: ");
            date = sc.nextLine();
            if(!checkDate(date))
                System.err.println("Date invalid!");
        }while(!checkDate(date));
        return date;
    }
    
    public boolean checkDate(String date){
        
        boolean result = true;
        try{
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            //cơ chế đổi String sang Date.
            //nếu ngày (date) mà sai thì LỖI
            
            df.setLenient(false); //cơ chế tắt chế độ tự sửa lỗi đổi 29/2 thành 1/3
            //29/2/2022 -> 1/3/2022
            df.parse(date);//nếu sai sẽ lỗi tại đây
        } catch(Exception e){
            result = false;
        }
        return result;
    }
    
    public String inputString(String label){
        String str;
        do {
            System.out.println("Enter " + label + ": ");
            str = sc.nextLine();
            if (str.isEmpty())
                System.err.println("Not allow empty");
        }while(str.isEmpty());
        return str;
    }
    
    public String inputID(){
        //empty?
        //duplicate?
        boolean isDuplicate = true;
        String id;
        
        do{
            System.out.println("Enter id: ");
            id = sc.nextLine();
            isDuplicate = checkDuplicate(id);
            
            if (isDuplicate)
                System.err.println("Not allow duplicate!");
            if (id.isEmpty())
                System.err.println("Not allow empty!!");
            
        }while(id.isEmpty() || isDuplicate);
        
        return id;
    }
    
    public boolean checkDuplicate(String id){
        for(Student std : listStudent){//for each đặc biệt
            //sử dụng getId thay food.id để thể hiện tính đóng gói (encapsulation)
            if(std.getId().equals(id))
                return true;
        }
        return false;
    }
    
    public boolean inputtoUpdateGrade(){
        //true -> continue
        //false - > hien ra lai menu
        String option;
        do {
            System.out.println("Student already have grade of this subject. Do you want to update grade? (yes/no) ");
            option = sc.nextLine();
            if (!option.equals("yes") && !option.equals("no")){
                System.err.println("Only allow yes/no!");
            }
        }while(!option.equals("yes") && !option.equals("no"));
        //yes/no
        return option.equals("yes");
    }
    
    public boolean inputContinue(){
        //true -> continue
        //false - > hien ra lai menu
        String option;
        do {
            System.out.println("Do you want to continue? (yes/no) ");
            option = sc.nextLine();
            if (!option.equals("yes") && !option.equals("no")){
                System.err.println("Only allow yes/no!");
            }
        }while(!option.equals("yes") && !option.equals("no"));
        //yes/no
        return option.equals("yes");
    }
    
    public String inputEmail(){
        //regex
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String email;
        
        do {
            System.out.println("Enter email: ");
            email = sc.nextLine();
            if(!email.matches(regex))
                System.err.println("Your email is invalid");
        }while(!email.matches(regex));
        return email;
    }
}
