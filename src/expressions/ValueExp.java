package expressions;

import Type.IType;
import exceptions.MyException;
import Values.IValue;
import model.MyIDictionary;
import model.MyIHeap;

public class ValueExp implements IExp{
    private IValue value;

    public ValueExp(IValue value) {
        this.value = value;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symbolTable, MyIHeap<IValue> heapTable) throws MyException {
        return value;
    }

    @Override
    public IExp deepCopy() {
        return new ValueExp(value.deepCopy());
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return this.value.getType();
    }

    public String toString() {
        return value.toString();
    }
}
