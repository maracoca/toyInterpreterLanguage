package statements;

import exceptions.VariableAlreadyDefined;
import exceptions.MyException;
import Type.IType;
import Values.IValue;
import model.MyIDictionary;
import model.PrgState;

public class VarDeclStmt implements IStmt{
    String name;
    IType type;

    public VarDeclStmt(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        if (!symTable.isDefined(name)) {
            symTable.put(name, type.getDefaultValue());
        }else{
            throw new VariableAlreadyDefined();
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, type.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        typeEnv.put(this.name, this.type);
        return typeEnv;
    }

    public String toString() {
        return type.toString() + " " + name;
    }
}
