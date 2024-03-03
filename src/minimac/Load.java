package minimac;

public class Load implements Instruction{
    int location;
    int value;
    public Load(int location, int value){
        this.location = location;
        this.value=value;
    }

    @Override
    public void execute(MiniMac program) {
        program.memory[location] = value;
    }
}
