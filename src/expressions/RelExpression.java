package expressions;

import Type.intType;
import Values.IValue;
import Values.boolValue;
import Values.intValue;
import exceptions.ExpressionOperation;
import exceptions.MyException;
import exceptions.OperandNotInt;
import model.MyIDictionary;
import model.MyIHeap;

public class RelExpression implements IExp{
    private IExp expression1;
    private IExp expression2;
    private String operation;

    public RelExpression(IExp exp1, IExp exp2, String operation) {
        this.expression1 = exp1;
        this.expression2 = exp2;
        this.operation = operation;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symbolTable, MyIHeap<IValue> heapTable) throws MyException {
        IValue  value1 = this.expression1.eval(symbolTable, heapTable);
        if (value1.getType().equals(new intType())) {
            IValue value2 = this.expression2.eval(symbolTable, heapTable);
            if (value2.getType().equals(new intType())) {
                intValue intValue1 = (intValue) value1;
                intValue intValue2 = (intValue) value2;
                int num1 = intValue1.getValue();
                int num2 = intValue2.getValue();
                if (operation.equals(">")) {
                    return new boolValue(num1 > num2);
                }
                else if (operation.equals("<")) {
                    return new boolValue(num1 < num2);
                }
                else if (operation.equals(">=")) {
                    return new boolValue(num1 >= num2);
                }
                else if (operation.equals("<=")) {
                    return new boolValue(num1 <= num2);
                }
                else if (operation.equals("==")) {
                    return new boolValue(num1 == num2);
                }
                else if (operation.equals("!=")) {
                    return new boolValue(num1 != num2);
                }else{
                    throw new ExpressionOperation();
                }
            }else{
                throw new OperandNotInt();
            }
        }else{
            throw new OperandNotInt();
        }
    }

    @Override
    public IExp deepCopy() {
        return new RelExpression(expression1.deepCopy(), expression2.deepCopy(), operation);
    }

    public String toString() {
        return this.expression1.toString() + " " + this.operation + " " + this.expression2.toString();
    }
}
