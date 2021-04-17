import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    Scanner in;
    private final ArrayList<String> usernames;
    private final ArrayList<String> passwords;
    private int checkDigit;
    // each teacher has a specific log in and can only see the classes that they teach

    // l.villazon
    // k.powell
    // r.leaman
    // a.hewitt (for the sake of coding the ia)

    // v1llazon
    // p0well
    // l3aman
    // hew1tt

    public Login(){
        // i don't know if this is the write coding but all the colours are right
        // and that is what i'm basing my mental state on right now
        // pretty colours
        in = new Scanner(System.in);

        // take in usernames and passwords that are correct
        usernames = FileHandler.readFromFile("C:\\Users\\abbie\\OneDrive\\Documents\\abbie\\ib\\computer science\\ia\\usernames.txt");
        passwords = FileHandler.readFromFile("C:\\Users\\abbie\\OneDrive\\Documents\\abbie\\ib\\computer science\\ia\\passwords.txt");
    }

    public boolean checkUserName(String userInput){
        // take in user input from another method and check this against the usernames found in a text file.
        for (int i=0; i <usernames.size(); i++) {
            if (usernames.get(i).equals(userInput)) {
                checkDigit = i;
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String userInput){
        // take in user input from another method and check this against the usernames found in a text file.
        // only check the password that matches up with
        return passwords.get(checkDigit).equals(userInput);
    }

}
