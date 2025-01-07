package expressions;

import Type.IType;
import exceptions.DivisionByZero;
import exceptions.MyException;
import Type.intType;
import Values.IValue;
import Values.intValue;
import exceptions.OperandNotInt;
import model.MyIDictionary;
import model.MyIHeap;

public class ArithExp implements IExp {
    private IExp expression1;
    private IExp expression2;
    private int op;

    public ArithExp(int op, IExp exp1, IExp exp2) {
        this.expression1 = exp1;
        this.expression2 = exp2;
        this.op = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symbolTable, MyIHeap<IValue> heapTable) throws MyException {
        IValue value1 = expression1.eval(symbolTable, heapTable);
        if (value1.getType().equals(new intType())) {
            IValue value2 = expression2.eval(symbolTable, heapTable);
            if (value2.getType().equals(new intType())) {
                intValue int1 = (intValue) value1;
                intValue int2 = (intValue) value2;
                int nr1, nr2;
                nr1 = int1.getValue();
                nr2 = int2.getValue();
                if (op == 1) {
                    return new intValue(nr1 + nr2);
                } else if (op == 2) {
                    return new intValue(nr1 - nr2);
                }else if (op == 3) {
                    if (nr2 == 0)
                        throw new DivisionByZero();
                    return new intValue(nr1 / nr2);
                }else {
                    return new intValue(nr1 * nr2);
                }
            } else
                throw new OperandNotInt();
        } else
            throw new OperandNotInt();
    }

    @Override
    public IExp deepCopy() {
        return new ArithExp(this.op, this.expression1.deepCopy(), this.expression2.deepCopy());
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type1, type2;
        type1 = this.expression1.typeCheck(typeEnv);
        type2 = this.expression2.typeCheck(typeEnv);
        if (type1.equals(new intType())) {
            if (type2.equals(new intType())) {
                return new intType();
            } else {
                throw new OperandNotInt();
            }
        } else {
            throw new OperandNotInt();
        }
    }

    public String toString() {
        char operationSymbol;
        if (op == 1) {
            operationSymbol = '+';
        }
        else if (op == 2) {
            operationSymbol = '-';
        }else if (op == 3) {
            operationSymbol = '/';
        }else {
            operationSymbol = '*';
        }
        return expression1.toString() + operationSymbol + expression2.toString();
    }
}