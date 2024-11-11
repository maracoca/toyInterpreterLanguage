package statements;

import exceptions.MyException;
import model.PrgState;

public class NopStmt implements IStmt{

    public NopStmt() {}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    public String toString() {
        return "Nop Statement";
    }
}
