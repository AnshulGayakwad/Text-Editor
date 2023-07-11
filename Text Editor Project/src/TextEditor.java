import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;      //items of File
    JMenuItem cut, copy, paste, selectAll, close;   //items of Edit toggle
    JTextArea textArea;
    TextEditor(){
        frame = new JFrame("Text Editor");
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        //Initializing menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //setting Items under file menu
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open file");
        saveFile = new JMenuItem("Save File");
        //add action Listener to every Item in File
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //adding items in file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //setting items under Edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //add action Listener to every Item in File
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //adding all items in Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //adding menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //set menu-bar in the frame
        frame.setJMenuBar(menuBar);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //can do same work as below code which is inside "//////////////"
        /*
        // Set the border with 5-pixel width
        textArea.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
        // Add the textArea to the center of the frame
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        * */
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //create Content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0, 0));
        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        //setting dimensions to frame
        frame.setBounds(0,0,500,600);
        frame.setVisible(true);
        //this will delete layouts which are coming from parent
        frame.setLayout(null);
        //we can exit code
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            //perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            //close text editor application
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //If we choose open button
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //get selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                //there might be no file
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialize Buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    //this will hold current line
                    String intermediate = "";
                    //this will hold entire text of selected file
                    String output = "";
                    //read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            //Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseoption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseoption == JFileChooser.APPROVE_OPTION){
                //create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            //just create a new object of TextEditor
            TextEditor newWindow = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}