package expressions;

import exceptions.MyException;
import Values.IValue;
import model.MyIDictionary;

public interface IExp {
    IValue eval(MyIDictionary<String, IValue> symbolTable) throws MyException;
    IExp deepCopy();
}
