package minimac;

public class Loop implements Instruction{
    int count;
    Instruction instruction;
    public Loop(int count, Instruction instruction ){
        this.count = count;
        this.instruction=instruction;
    }

    @Override
    public void execute(MiniMac program) {
        for(int i=0;i<count;i++){
            instruction.execute(program);
        }
    }
}
