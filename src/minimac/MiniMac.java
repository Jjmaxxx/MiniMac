package minimac;

import java.util.Arrays;
import java.util.List;
import java.io.Serializable;
import tools.Publisher;


public class MiniMac extends Publisher implements Serializable{
    int ip = 0;
    boolean halt= false;
    int size = 32;
    Integer[] memory;
    List<Instruction> instructionList;
    String content;
    public MiniMac(){
        memory = new Integer[size];
        Arrays.fill(memory, 0);
    }
    public void clear(){
        if( instructionList == null) return;
        Arrays.fill(memory, 0);
        notifySubscribers();
    }
    public void execute(){
        if(instructionList == null) return;
        halt=false;
        ip=0;
        while(ip<instructionList.size() && !halt){
            instructionList.get(ip).execute(this);
            ip++;
        }
        notifySubscribers();
    }
    public void setInstructionList(List<Instruction> instructionList){
        this.instructionList = instructionList;
        notifySubscribers();
    }
    public void setAllInstructions(String content){
        this.content = content;
        notifySubscribers();
    }
}
