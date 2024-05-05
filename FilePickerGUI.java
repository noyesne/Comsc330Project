package jsjf;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class FilePickerGUI extends JFrame implements ActionListener {
    private JButton openButton;
    private JButton calcButton;
    private JTextField filePathField;
    private JTextArea outTextField;
    private JTextPane byLine;
    
    
    private String[] arr = new String[1]; //stores the file path to the run file
    //Constructur which creates the certain fields
    public FilePickerGUI() {
        setTitle("Grade Calculator App");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        openButton = new JButton("Select File");
        calcButton = new JButton("Calculate");
        openButton.addActionListener(this);
        calcButton.addActionListener(this);
        add(openButton);
        add(calcButton);
        
        filePathField = new JTextField(50);
        filePathField.setEditable(false);
        
        add(filePathField);
        outTextField = new JTextArea("\t\t              INSTRUCTIONS:\n" + //
                        "\t1. Use the Select File Button to choose the file you want\n" + //
                        "\t2. When the file is selected click the Calculate Button to calculate the file \n" + //
                        "\t3. then follow the file path shown to find the Output File", 5, 50);
        outTextField.setAlignmentX(CENTER_ALIGNMENT);
        outTextField.setEditable(false);
        add(outTextField);

        byLine = new JTextPane();
        byLine.setSize(50, 10);
        byLine.setText("\n\n\n\n\nBy: Jack Noyes, Isabel Cyr, Nick Vieira, Dylan Gomes");
        byLine.setOpaque(false);
        add(byLine);
        
    }
    //This method checks to see the which button was pressed and what to do based off of that
    public void actionPerformed(ActionEvent e) { 
        /*
        * When the "Select File" button is clicked it opens up a window to explore the file and allows the use to choose that file
        * 
        */
        if (e.getSource() == openButton) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                filePathField.setText(fileChooser.getSelectedFile().getPath());
                String file = filePathField.getText();
                this.arr[0] = file;
            }
        }
        //This button will take in the file path from the "Select File" Button and push that to the Main class to perform the calculations
        //It will also catch if the file is not a .run file and promote the use to try again
        if (e.getSource() == calcButton){
            if(arr[0].contains(".run")){
                try{
                Main.main(arr);
                }catch(Exception ex){
                    outTextField.setText("Please make sure that the file exists" + ex);
                }
                String s = Main.getSendOver();
                outTextField.setText(s);
                openFileInNewWindow(Main.getOutput());
            }else{
                outTextField.setText("Please ensure that the file is a '.run' file");
            }
        }
    }

    //This method will open the Output file for the user so that he or she do not have to go searching for it, but the file path will still be listed in the text field
    public void openFileInNewWindow(String filePath) {
        JFrame frame = new JFrame("File Viewer");
        JTextArea textArea = new JTextArea(35, 60);
        JScrollPane scrollPane = new JScrollPane(textArea);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FilePickerGUI f = new FilePickerGUI();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            
        });
        
    }
}
