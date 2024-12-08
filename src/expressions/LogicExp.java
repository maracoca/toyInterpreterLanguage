package expressions;

import exceptions.MyException;
import Type.boolType;
import Values.IValue;
import Values.boolValue;
import exceptions.OperandNotBool;
import model.MyIDictionary;
import model.MyIHeap;

public class LogicExp implements IExp{
    IExp expression1;
    IExp expression2;
    int op;

    public LogicExp(IExp exp1, IExp exp2, int op) {
        this.expression1 = exp1;
        this.expression2 = exp2;
        this.op = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symbolTable, MyIHeap<IValue> heapTable) throws MyException {
        IValue value1 = expression1.eval(symbolTable, heapTable);
        IValue value2 = expression2.eval(symbolTable, heapTable);
        if (value1.getType().equals(new boolType())){
            if (value2.getType().equals(new boolType())) {
                boolValue firstBoolValue = (boolValue) value1;
                boolValue secondBoolValue = (boolValue) value2;
                boolean firstValue = firstBoolValue.getValue();
                boolean secondValue = secondBoolValue.getValue();
                if (op == 1) {
                    return new boolValue(firstValue && secondValue);
                }
                else {
                    return new boolValue(firstValue || secondValue);
                }
            }else{
                throw new OperandNotBool();
            }
        }
        else{
            throw new OperandNotBool();
        }

    }

    @Override
    public IExp deepCopy() {
        return new LogicExp(expression1.deepCopy(), expression2.deepCopy(), op);
    }

    public String toString() {
        String operationSymbol;
        if (op == 1) {
            operationSymbol = "AND";
        }
        else {
            operationSymbol = "OR";
        }

        return "(" + expression1 + operationSymbol + expression2 + ")";
    }
}
