import java.util.ArrayList;

public class Session {
    private ArrayList<String> currentSession;
    // read in the 5th line of the xml file

    // read in the 4th line of the xml file
    public Session(){
        currentSession = FileHandler.readFromFile("C:\\Users\\abbie\\OneDrive\\Documents\\abbie\\ib\\computer science\\ia\\8edf45e0-9c01-42f6-881b-ae055d324020.txt");
        assert currentSession != null;
        for (String content : currentSession) {
            System.out.println(content);
        }
    }

    public ArrayList<String> getCurrentSession() {
        return currentSession;
    }

    // search the class found from the 5th line for the student found in 4th line
    // if the class already contains student with this name then add session
    // if not then create a student and add session

    // progress - check first level completed and the name and check against record
    // check last level completed and the name against record
    // update progress if they have completed a level and moved on

    // collect any errors in code made
    // add these to a students personal error collector
    // add to class collector
    // if the same error already appears then create a flag
    // if an error occurs more than 5-10 times then flag this to the teacher
}
