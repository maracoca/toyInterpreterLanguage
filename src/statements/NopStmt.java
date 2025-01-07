package statements;

import Type.IType;
import exceptions.MyException;
import model.MyIDictionary;
import model.PrgState;

public class NopStmt implements IStmt{

    public NopStmt() {}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }

    public String toString() {
        return "Nop Statement";
    }
}
