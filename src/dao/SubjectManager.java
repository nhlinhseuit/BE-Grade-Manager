
package dao;

import database.FileHandler;
import dto.Student;
import dto.Subject;
import java.util.ArrayList;
import util.Input;
import util.Menu;
import util.Message;

public class SubjectManager implements ManagerAction{

    ArrayList<Subject> listSubject;
    Input input;
    Message ms;
    Menu menu;
    FileHandler fileHandler;
    
    public SubjectManager() {
        fileHandler = new FileHandler();
        listSubject = new ArrayList<>();
        input = new Input(null, listSubject);
        ms = new Message();
        menu = new Menu();
    }
    
    public ArrayList<Subject> getListSubject() {
        
        return this.listSubject;
    }

    public void updateOrDelete() {
        int index = input.inputIdGetIndexSubject();
        if (index == -1)
            ms.showErrorMessage("Subject does not exist");
        else {
//            int choice  = input.inputUpdateOrDelete();
            int choice = menu.getChoiceUpdateOrDelete("subject");
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
    public void add() {
        boolean isContinue;
        Subject subject = input.inputSubject();
        listSubject.add(subject);
        ms.showSuccessMessage("Add new subject success!");
        isContinue = input.inputContinue();
        fileHandler.saveListSubjectToFile(listSubject);
        if(isContinue)
            this.add();
    }

    @Override
    public void update(int index) {
        Subject sbj = listSubject.get(index);
        sbj.setName(input.inputString("subject's name"));
        ms.showSuccessMessage("Update sbj " + sbj.getId() + " success");
        fileHandler.saveListSubjectToFile(listSubject);
    }

    @Override
    public void delete(int index) {
        boolean isDelete = input.inputContinue();
        if (isDelete = true) {
            listSubject.remove(index);
            ms.showSuccessMessage("Delete success");
            fileHandler.saveListSubjectToFile(listSubject);
        }
        else {
            ms.showSuccessMessage("Cancel success!");
        }
    }
    
}
