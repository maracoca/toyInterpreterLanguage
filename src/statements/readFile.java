package statements;

import Type.IType;
import Type.intType;
import Type.stringType;
import Values.IValue;
import Values.intValue;
import Values.stringValue;
import exceptions.DictionaryException;
import exceptions.MyException;
import exceptions.OperandNotInt;
import exceptions.OperandNotString;
import expressions.IExp;
import model.MyIDictionary;
import model.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt{
    private IExp expression;
    private String varName;

    public readFile(IExp exp, String varName) {
        this.expression = exp;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymTable();
        IValue varValue = symbolTable.get(varName);
        if (varValue.getType().equals(new intType())) {
            IValue fileNameValue= expression.eval(symbolTable, state.getHeap());
            if(fileNameValue.getType().equals(new stringType())){
                stringValue fileName = (stringValue) fileNameValue;
                BufferedReader br;
                try {
                    br = state.getFileTable().get(fileName);
                } catch (DictionaryException e){
                    throw new MyException(e.getMessage());
                }
                try{
                    String line = br.readLine();
                    if (line != null) {
                        symbolTable.put(varName, new intValue(Integer.parseInt(line)));
                    }
                    else{
                        symbolTable.put(varName, new intType().getDefaultValue());
                    }
                }catch (IOException e){
                    throw new MyException("Error reading from file "+e.getMessage());
                }

            }else{
                throw new OperandNotString();
            }
        }else{
            throw new OperandNotInt();
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new readFile(expression.deepCopy(), varName);
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeExp = this.expression.typeCheck(typeEnv);
        IType typeVar = typeEnv.get(this.varName);
        if (typeExp.equals(new stringType())) {
            if (typeVar.equals(new intType())) {
                return typeEnv;
            } else {
                throw new OperandNotInt();
            }
        } else {
            throw new OperandNotString();
        }
    }
}
