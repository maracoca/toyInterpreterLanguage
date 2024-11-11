package exceptions;

public class ExpressionNotBool extends MyException {
    public ExpressionNotBool() {
        super("Expression is not boolean");
    }
}
