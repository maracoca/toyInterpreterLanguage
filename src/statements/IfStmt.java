package statements;

import exceptions.ExpressionNotBool;
import exceptions.MyException;
import Type.boolType;
import Values.IValue;
import Values.boolValue;
import expressions.IExp;
import model.MyIDictionary;
import model.MyIStack;
import model.PrgState;

public class IfStmt implements IStmt{
    private IExp condition;
    private IStmt thenBranch;
    private IStmt elseBranch;

    public IfStmt(IExp condition, IStmt thenBranch, IStmt elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        MyIStack<IStmt> stk = state.getStk();
        IValue condValue = condition.eval(symTable);
        if (condValue.getType().equals(new boolType())) {
            boolValue boolCondValue = (boolValue) condValue;
            if (boolCondValue.getValue()){
                stk.push(thenBranch);
            }
            else{
                stk.push(elseBranch);
            }
        }else{
            throw new ExpressionNotBool();
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(condition.deepCopy(), thenBranch.deepCopy(), elseBranch.deepCopy());
    }

    public String toString() {
        return "(IF("+ condition.toString()+") THEN(" +thenBranch.toString() +")ELSE("+elseBranch.toString()+"))";
    }
}
