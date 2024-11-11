package exceptions;

public class VariableAlreadyDefined extends MyException {
    public VariableAlreadyDefined() {
        super("Variable already declared");
    }
}
