package minimac;
import java.io.Serializable;

public interface Instruction extends Serializable {

    public void execute(MiniMac program);
}
