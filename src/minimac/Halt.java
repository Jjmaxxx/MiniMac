package minimac;

public class Halt implements Instruction{
    @Override
    public void execute(MiniMac program) {
        program.halt = true;
    }
}
