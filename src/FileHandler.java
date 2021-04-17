import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.RandomAccessFile;

public class FileHandler {
    public static ArrayList<String> readFromFile(String fileName){
        // read the contents of a text file
        // using all the exception handling best practices

        ArrayList<String> contents = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                contents.add(line);
                line = br.readLine();
            }
            return contents;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
        finally {
            try{
                if (br != null){
                    br.close();
                }
            }
            catch (IOException e){
                System.out.println("error in closing the BufferedReader");
            }
        }
    }

    public static String randomRead(String fileName){
        // grab the character at the position where the user's name starts

        RandomAccessFile rf = null;
        try{
            rf = new RandomAccessFile(fileName, "rws");
            rf.seek(0);
            return rf.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
        finally{
            try{
                assert rf != null;
                rf.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}