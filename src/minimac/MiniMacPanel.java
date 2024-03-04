package minimac;

import tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class MiniMacPanel  extends JPanel implements ActionListener {
    private ControlPanel controls;
    private MiniMacView view;
    MiniMac miniMac = new MiniMac();
    JFrame frame;
    public MiniMacPanel() {
        view = new MiniMacView(miniMac);
        controls = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(view);

        frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("MiniMac");
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Parse","Run","Clear"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "Parse":
                    String file = Utilities.getFileName(null, true);
                    if(file ==null) break;
//                    String file = inputFile();
                    String content = new String(Files.readAllBytes(Paths.get(file)));
                    miniMac.setAllInstructions(content);
                    List<Instruction> instructionList = MiniMacParser.parse(content);
                    miniMac.setInstructionList(instructionList);
                    break;
                case "Run":
                    miniMac.execute();
                    break;

                case "Save": {
                    try{
                        String fName = Utilities.getFileName((String) null, false);
                        if(fName==null) break;
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                        os.writeObject(this.miniMac);
                        os.close();
                    }catch (Exception err){
                        Utilities.error(err);
                    }

                    break;
                }
                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        if(fName == null) break;
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        miniMac = (MiniMac) is.readObject();
                        view.setMiniMac(miniMac);
                        is.close();
                    }

                    break;

                }

                case "Clear":{
                    miniMac.clear();
                    break;
                }

                case "New": {
                    MiniMacPanel newApp = new MiniMacPanel();
                    break;
                }

                case "Quit": {
                    frame.dispose();
                    break;
                }

                case "About": {
                    Utilities.inform("MiniMac Simulator, 2024. All rights reserved.");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "Parse: Input in a text file with line by line assembly commands.",
                            "Run: Read through the parsed text file. Will show the result on the side.",
                            "Clear: Clear all memory cells that may have been altered by the assembly commands."
                    };
                    Utilities.inform(cmmds);
                    break;

                }

                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }
    //    public String inputFile(){
//        JFrame frame = new JFrame("Input File Name");
//        frame.setSize(200, 150);
//
//        JPanel panel = new JPanel();
//
//        panel.add(new JLabel("Enter program file name"));
//
//        JTextField textField = new JTextField(15);
//        panel.add(textField);
//
//        int result = JOptionPane.showConfirmDialog(null, panel, "", JOptionPane.OK_CANCEL_OPTION);
//        if (result == JOptionPane.OK_OPTION) {
//            return textField.getText();
//        } else {
//            return null;
//        }
//    }
    class ControlPanel extends JPanel {
        public ControlPanel() {
            setLayout(new BorderLayout());
            JPanel p = new JPanel(new GridLayout(0, 1));
            JButton parse = new JButton("Parse");
            parse.addActionListener(MiniMacPanel.this);
            p.add(parse);

            JButton run = new JButton("Run");
            run.addActionListener(MiniMacPanel.this);
            p.add(run);

            JButton clear = new JButton("Clear");
            clear.addActionListener(MiniMacPanel.this);
            p.add(clear);
            add(p);
        }
    }

    public static void main(String[] args) {
        MiniMacPanel app = new MiniMacPanel();
    }
}