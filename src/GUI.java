import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import java.awt.event.*;

public class GUI implements ActionListener, DocumentListener {

    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 500;
    private final int LEFT_MARGIN = 100;
    private final int TOP_MARGIN = 20;
    private final int BUTTON_Y = 150;
    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 40;
    private final int TEXT_WIDTH = 200;
    private final int TEXT_HEIGHT = 30;

    private JFrame frame;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;


    private int userNameLength = 0;
    private int passwordLength = 0;

    public Login login;

    public GUI(){
        login = new Login();
        frame = new JFrame();

        // instruction for user to follow as a text label
        JLabel instructions = new JLabel("Enter your user name");
        instructions.setBounds(LEFT_MARGIN, TOP_MARGIN, TEXT_WIDTH, TEXT_HEIGHT);
        frame.add(instructions);

        // text field where user can input whatever they want i don't give a damn
        // preferably a username though since that's the whole point
        userNameField = new JTextField();
        userNameField.setBounds(LEFT_MARGIN, TOP_MARGIN + TEXT_HEIGHT, TEXT_WIDTH, TEXT_HEIGHT);
        userNameField.getDocument().addDocumentListener(this);
        userNameField.getDocument().putProperty("name", "username");
        frame.add(userNameField);

        // instructions for user to follow as a text label
        JLabel passwordInstructions = new JLabel("enter your password pls");
        passwordInstructions.setBounds(LEFT_MARGIN, TOP_MARGIN + (2*TEXT_HEIGHT), TEXT_WIDTH, TEXT_HEIGHT);
        frame.add(passwordInstructions);

        // text field where user enters a random string and it hides the characters as cute lil dots
        passwordField = new JPasswordField();
        passwordField.setBounds(LEFT_MARGIN, TOP_MARGIN + (3*TEXT_HEIGHT), TEXT_WIDTH, TEXT_HEIGHT);
        passwordField.getDocument().addDocumentListener(this);
        passwordField.getDocument().putProperty("name", "password");
        frame.add(passwordField);

        // ok button
        okButton = new JButton("OK");
        okButton.setBounds(LEFT_MARGIN, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        okButton.addActionListener(this);
        okButton.setEnabled(false);
        frame.add(okButton);

        // cancel button
        cancelButton = new JButton("CANCEL");
        cancelButton.setBounds(LEFT_MARGIN+BUTTON_WIDTH+10, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        cancelButton.addActionListener(this);
        cancelButton.setEnabled(false);
        frame.add(cancelButton);

        // other frame attributes that need to be put in their place
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("OK")){
            JLabel passwordMismatch = new JLabel("Password does not match username!");
            passwordMismatch.setBounds(LEFT_MARGIN, BUTTON_Y + (2*TEXT_HEIGHT), TEXT_WIDTH, TEXT_HEIGHT);
            JLabel userNameMismatch = new JLabel("Username does not exist!");
            userNameMismatch.setBounds(LEFT_MARGIN, BUTTON_Y + (2*TEXT_HEIGHT), TEXT_WIDTH, TEXT_HEIGHT);
            // if ok button is pressed then check the password is correct
            if(login.checkUserName(userNameField.getText())){
                if(login.checkPassword(passwordField.getText())){
                    frame.removeAll();
                    frame.setLayout(null);
                    frame.setVisible(true);
                    // insert message to welcome user
                } else{
                    // display message to alert user that password does not match
                    frame.remove(userNameMismatch);
                    frame.add(passwordMismatch);
                    frame.setLayout(null);
                    frame.setVisible(true);
                }
            } else{
                // display message to alert user that username does not exist in database
                frame.add(userNameMismatch);
            }
        }
        if(e.getActionCommand().equals("CANCEL")){
            // if cancel button is pressed the set both text fields to empty
            // set both lengths to zero to make sure the cancel button is not enabled.
            userNameField.setText("");
            passwordField.setText("");
            userNameLength = 0;
            passwordLength = 0;
            cancelButton.setEnabled(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e){
        // triggers when the style of the text changes
    }

    @Override
    public void removeUpdate(DocumentEvent e){
        // when a character is removed, run a method to update the length
        updateLengthRemove(e);

        // if either of the text fields have a length of zero, the ok button should not be enabled
        if((userNameLength == 0) || (passwordLength == 0)){
            okButton.setEnabled(false);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e){
        // when a character is added, run a method to update the length
        updateLengthInsert(e);

        // if both of the text fields have any text, the ok button should be enable
        if((userNameLength > 0) && (passwordLength > 0)){
            okButton.setEnabled(true);
        }

        // if either of the text fields have text, then the cancel button is enabled
        if((userNameLength > 0) || (passwordLength > 0)){
            cancelButton.setEnabled(true);
        }
    }

    private void updateLengthInsert(DocumentEvent e){
        Document doc = (Document)e.getDocument();
        if(doc.getProperty("name").equals("username")){
            userNameLength = userNameLength + e.getLength();
        } else if(doc.getProperty("name").equals("password")){
            passwordLength = passwordLength + e.getLength();
        }
    }

    private void updateLengthRemove(DocumentEvent e){
        Document doc = (Document)e.getDocument();
        if(doc.getProperty("name").equals("username")){
            userNameLength = userNameLength - e.getLength();
        } else if(doc.getProperty("name").equals("password")){
            passwordLength = passwordLength - e.getLength();
        }
    }

}
