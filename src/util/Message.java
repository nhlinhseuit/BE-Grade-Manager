
package util;
public class Message {
    public void showErrorMessage(String message){
        System.out.println(Color.RED_BACKGROUND + Color.WHITE_BOLD + message + Color.RESET);
    }
    
    public void showSuccessMessage(String message) {
        System.out.println(Color.GREEN_BACKGROUND + Color.WHITE_BOLD + message + Color.RESET);
    }
}
