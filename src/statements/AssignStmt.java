package statements;

import exceptions.MyException;
import Type.IType;
import Values.IValue;
import exceptions.VariableNotDefined;
import exceptions.WrongTypeAssign;
import expressions.IExp;
import model.MyIDictionary;
import model.PrgState;

public class AssignStmt implements IStmt {
    private String variable;
    private IExp expression;

    public AssignStmt(String variable, IExp expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        if (symTable.isDefined(variable)) {
            IValue value = expression.eval(symTable);
            IType varType = symTable.get(variable).getType();
            if (varType.equals(value.getType())) {
                symTable.put(variable, value);
            }else{
                throw new WrongTypeAssign();
            }
        }else{
            throw new VariableNotDefined();
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(variable, expression.deepCopy());
    }

    public String toString(){
        return variable + "=" + expression.toString();
    }
}
