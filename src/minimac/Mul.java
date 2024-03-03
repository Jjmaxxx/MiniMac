package minimac;

public class Mul implements Instruction{
    int src1;
    int src2;
    int dest;
    public Mul(int src1, int src2, int dest){
        this.src1=src1;
        this.src2=src2;
        this.dest=dest;
    }

    public void execute(MiniMac program) {
        program.memory[dest] = program.memory[src1] * program.memory[src2];
    }

}
