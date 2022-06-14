
package dao;

import database.FileHandler;
import dto.Student;
import java.util.ArrayList;
import util.Input;
import util.Menu;
import util.Message;

public class StudentManager implements ManagerAction{
    //nếu không khai báo public hay private thì nó sẽ là default: chỉ cho phép truy cập trong phạm vi package
    ArrayList<Student> listStudent;
    Input input;
    Menu menu;
    Message ms;
    FileHandler fileHandler;
    
    public StudentManager(){
        fileHandler = new FileHandler();
        //listStudent = new ArrayList<Student>;
        listStudent = new ArrayList<>();
        input = new Input(listStudent, null);
        menu = new Menu();
        ms = new Message();
    }
    
    public ArrayList<Student> getListStudent() {
        return this.listStudent;
    }
    
    @Override
    public void add(){
        boolean isContinue;
        Student std = input.inputStudent();
        listStudent.add(std);
        System.out.println("Add successfully!");
        isContinue = input.inputContinue();
        fileHandler.saveListStudentToFile(listStudent);
        if(isContinue)
            this.add();
                
    }
    
    public void updateOrDelete() {
        int index = input.inputIdGetIndex();
        if (index == -1)
            ms.showErrorMessage("Student does not exist");
        else {
//            int choice  = input.inputUpdateOrDelete();
            int choice = menu.getChoiceUpdateOrDelete("student");
            switch (choice) {
            case 1:
                update(index);
                break;
            case 2:
                delete(index);
                break;
            }
        }
    }

    @Override
    public void update(int index) {
        Student std = listStudent.get(index);
        std.setEmail(input.inputEmail());
        ms.showSuccessMessage("Update student " + std.getLastName() + " success");   
        fileHandler.saveListStudentToFile(listStudent);
    }

    @Override
    public void delete(int index) {
        // có student
        //có thật sự muốn xoá?
        boolean isDelete = input.inputContinue();
        if (isDelete = true) {
            listStudent.remove(index);
            ms.showSuccessMessage("Delete success");
            fileHandler.saveListStudentToFile(listStudent);
        }
        else {
            ms.showSuccessMessage("Cancel success!");
        }
    }
    
}
