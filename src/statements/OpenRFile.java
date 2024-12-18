package statements;

import Type.stringType;
import Values.IValue;
import Values.stringValue;
import exceptions.FileException;
import exceptions.MyException;
import exceptions.OperandNotString;
import expressions.IExp;
import model.PrgState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStmt {
    private IExp expression;

    public OpenRFile(IExp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue value = this.expression.eval(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new stringType())) {
            stringValue strValue = (stringValue) value;
            if (!state.getFileTable().isDefined(strValue)) {
                try {
                    BufferedReader fileDescriptor = new BufferedReader(new FileReader(strValue.getValue()));
                    state.getFileTable().put(strValue, fileDescriptor);
                } catch (FileNotFoundException e) {
                    throw new FileException();
                }
            }else {
                throw new FileException();
            }
        } else {
            throw new OperandNotString();
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFile(this.expression.deepCopy());
    }
}
