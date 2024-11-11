package expressions;

import exceptions.MyException;
import Values.IValue;
import exceptions.VariableNotDefined;
import model.MyIDictionary;

public class VarExp implements IExp{
    private String variableName;

    public VarExp(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symbolTable) throws MyException {
        if (symbolTable.isDefined(variableName)) {
            return symbolTable.get(variableName);
        } else{
            throw new VariableNotDefined();
        }
    }

    public String toString() {
        return variableName;
    }
}
