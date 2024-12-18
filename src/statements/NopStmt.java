package statements;

import exceptions.MyException;
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

    public String toString() {
        return "Nop Statement";
    }
}
