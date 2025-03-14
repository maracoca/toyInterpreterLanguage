package statements;

import Type.IType;
import exceptions.ExpressionNotBool;
import exceptions.MyException;
import Type.boolType;
import Values.IValue;
import Values.boolValue;
import exceptions.OperandNotBool;
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
        IValue condValue = condition.eval(symTable, state.getHeap());
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
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(condition.deepCopy(), thenBranch.deepCopy(), elseBranch.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeExp = this.condition.typeCheck(typeEnv);
        if (typeExp.equals(new boolType())) {
            this.thenBranch.typeCheck(typeEnv.deepCopy());
            this.elseBranch.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else {
            throw new OperandNotBool();
        }
    }

    public String toString() {
        return "(IF("+ condition.toString()+") THEN(" +thenBranch.toString() +")ELSE("+elseBranch.toString()+"))";
    }
}
