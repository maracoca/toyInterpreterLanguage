package statements;

import Type.IType;
import model.MyIDictionary;
import model.PrgState;
import exceptions.MyException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    IStmt deepCopy();
    MyIDictionary<String, IType> typeCheck(MyIDictionary<String,IType> typeEnv) throws MyException;
}
