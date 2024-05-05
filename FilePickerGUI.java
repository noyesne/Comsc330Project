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
    
    
    private String[] arr = new String[1];
    
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
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                filePathField.setText(fileChooser.getSelectedFile().getPath());
                String file = filePathField.getText();
                this.arr[0] = file;
            }
        }

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
            
             // ImageIcon icon = new ImageIcon(myImg);
                
// use icon here
            
        });
        
    }
}