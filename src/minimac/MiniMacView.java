package minimac;

import tools.Subscriber;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Scanner;

public class MiniMacView extends JPanel implements Subscriber{
    MiniMac minimac;
    DefaultListModel<String> memoryContent = new DefaultListModel<>();
    DefaultListModel<String> executedProgram = new DefaultListModel<>();
    public MiniMacView(MiniMac minimac) {
        this.minimac = minimac;
        minimac.subscribe(this);
        this.setLayout((new GridLayout(2, 1)));
        setSize(500, 500);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);

        JList<String> list1 = new JList<>(memoryContent);
        JScrollPane scrollPane1 = new JScrollPane(list1);
        add(scrollPane1);

        JList<String> list2 = new JList<>(executedProgram);
        JScrollPane scrollPane2 = new JScrollPane(list2);
        add(scrollPane2);

    }

    public void update() {
        //top list
        memoryContent.clear();
        Integer[] memory = minimac.memory;
        for(int i=0;i<memory.length;i++){
            memoryContent.addElement("memory[" +i+"] =" + memory[i]);
        }

        //bottom list
        executedProgram.clear();
        String content = minimac.content;
        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            executedProgram.addElement(scanner.nextLine());
        }
        scanner.close();

    }
}
