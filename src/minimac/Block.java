package minimac;

import java.util.List;

public class Block implements Instruction{
    List<Instruction> block;
    public Block(List<Instruction> block){
        this.block=block;
    }

    @Override
    public void execute(MiniMac program) {
        for (Instruction instruction : block) {
            instruction.execute(program);
        }
    }
}
