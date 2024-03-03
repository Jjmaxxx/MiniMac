package minimac;

public class Move implements Instruction{
    int src;
    int dest;
    public Move(int src, int dest){
        this.src = src;
        this.dest= dest;
    }

    public void execute(MiniMac program) {
        program.memory[dest] = program.memory[src];
    }
}
