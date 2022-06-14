package util;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    ArrayList<String> listMenu;
    Scanner sc;
    Message ms;
    
    public Menu() {
        listMenu = new ArrayList();
        sc = new Scanner(System.in);
        ms = new Message();
        
        listMenu.add("1. Add new student");
        listMenu.add("2. Update student");
        listMenu.add("3. Add new subject");
        listMenu.add("4. Update subject");
        listMenu.add("5. Enter grade");
        listMenu.add("6. Display Student grade report");
        listMenu.add("7. Display Subject grade report");
        listMenu.add("8. Quit");
    }    
    
    public void printMenu() {
        listMenu.forEach(System.out::println);
    }          
    
    public int getUserChoice(){
        printMenu();  
        int choice = getChoice(1, 8);
        return choice;
    }
    
    public int getChoice(int start, int end) {
        boolean isString = false;
        int choice = 0;
        do {
            try {
                
                System.out.println("Enter your choice");
                choice = Integer.parseInt(sc.nextLine());

                if (choice < start || choice > end)
                    ms.showErrorMessage("Please enter " +start+ "->"+ end);
                isString = false;
                
            } catch (Exception e) {
                isString = true;
                ms.showErrorMessage("Not allow string!");
            }
        } while(choice < start || choice > end || isString);
        return choice;
    }
    
    public void printUpdateOrDelete(String obj){
        System.out.println(Color.CYAN_BOLD+"UPDATE: " + obj + Color.RESET);
        System.out.println(Color.CYAN_BOLD+"\t 1. Update " + obj + Color.RESET);
        System.out.println(Color.CYAN_BOLD+"\t 2. Delete " + obj + Color.RESET);
    }
    
    public int getChoiceUpdateOrDelete(String obj) {
        int choice;
        printUpdateOrDelete(obj);
        choice = getChoice(1, 2);
        return choice;
    }
}
