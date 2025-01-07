package statements;

import Type.IType;
import exceptions.MyException;
import Values.IValue;
import expressions.IExp;
import model.*;

public class PrintStmt implements IStmt{
    private IExp exp;

    public PrintStmt(IExp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<IValue> out = state.getOut();
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        IValue valueToPrint = exp.eval(symTable, state.getHeap());
        out.add(valueToPrint);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        this.exp.typeCheck(typeEnv);
        return typeEnv;
    }

    public String toString(){
        return "print(" +exp.toString()+")";
    }
}
