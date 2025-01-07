package statements;

import Type.IType;
import exceptions.MyException;
import model.MyIDictionary;
import model.MyIStack;
import model.PrgState;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk =state.getStk();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return this.second.typeCheck(this.first.typeCheck(typeEnv));
    }

    public String toString(){
        return "(" + first.toString() + "; " + second.toString() + ")";
    }

}
