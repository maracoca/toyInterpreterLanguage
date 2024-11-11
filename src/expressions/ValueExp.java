package expressions;

import exceptions.MyException;
import Values.IValue;
import model.MyIDictionary;

public class ValueExp implements IExp{
    private IValue value;

    public ValueExp(IValue value) {
        this.value = value;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symbolTable) throws MyException {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
