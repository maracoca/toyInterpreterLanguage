package statements;

import Type.IType;
import Type.stringType;
import Values.IValue;
import Values.stringValue;
import exceptions.DictionaryException;
import exceptions.MyException;
import exceptions.OperandNotString;
import expressions.IExp;
import model.MyIDictionary;
import model.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt{
    private IExp expression;

    public closeRFile(IExp exp) {
        this.expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        IValue value = this.expression.eval(symTable, state.getHeap());
        if (value.getType().equals(new stringType())){
            stringValue strValue = (stringValue) value;
            BufferedReader br;
            try{
                br = state.getFileTable().get(strValue);
            } catch (DictionaryException e) {
                throw new MyException(e.getMessage());
            }
            try {
                br.close();
            } catch (IOException e) {
                throw new MyException("Error closing file: " + e.getMessage());
            }
            state.getFileTable().remove(strValue);
        }else{
            throw new OperandNotString();
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new closeRFile(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeExp = this.expression.typeCheck(typeEnv);
        if (typeExp.equals(new stringType())) {
            return typeEnv;
        } else {
            throw new OperandNotString();
        }
    }

    public String toString(){
        return "close file";
    }
}
