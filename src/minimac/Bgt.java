package minimac;

public class Bgt implements Instruction{
    int location;
    int offset;
    public Bgt(int location, int offset){
        this.location = location;
        this.offset=offset;
    }

    @Override
    public void execute(MiniMac program) {
        if(program.memory[location] > 0){
            program.ip+=offset;
        }
    }
}
