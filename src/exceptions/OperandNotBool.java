package exceptions;

public class OperandNotBool extends MyException {
    public OperandNotBool() {
        super("Operand is not a bool");
    }
}
