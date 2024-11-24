package exceptions;

public class OperandNotString extends MyException{
    public OperandNotString() {
        super("Operand is not a string");
    }
}
