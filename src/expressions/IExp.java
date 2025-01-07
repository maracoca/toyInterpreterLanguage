package expressions;

import Type.IType;
import exceptions.MyException;
import Values.IValue;
import model.MyIDictionary;
import model.MyIHeap;

public interface IExp {
    IValue eval(MyIDictionary<String, IValue> symbolTable, MyIHeap<IValue> heapTable) throws MyException;
    IExp deepCopy();
    IType typeCheck(MyIDictionary<String,IType> typeEnv) throws MyException;
}
