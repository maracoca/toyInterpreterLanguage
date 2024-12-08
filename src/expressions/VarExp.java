package expressions;

import exceptions.MyException;
import Values.IValue;
import exceptions.VariableNotDefined;
import model.MyIDictionary;
import model.MyIHeap;

public class VarExp implements IExp{
    private String variableName;

    public VarExp(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symbolTable, MyIHeap<IValue> heapTable) throws MyException {
        if (symbolTable.isDefined(variableName)) {
            return symbolTable.get(variableName);
        } else{
            throw new VariableNotDefined();
        }
    }

    @Override
    public IExp deepCopy() {
        return new VarExp(variableName);
    }

    public String toString() {
        return variableName;
    }
}
