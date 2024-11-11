package model;

import Values.IValue;
import statements.IStmt;

public class PrgState {
    private MyIStack<IStmt> exe;
    private MyIDictionary<String, IValue> symTable;
    private MyIList<IValue> out;

    public PrgState(MyIStack<IStmt> exe, MyIDictionary<String, IValue> symTable, MyIList<IValue> out){
        this.exe=exe;
        this.symTable=symTable;
        this.out=out;
    }

    public MyIStack<IStmt> getStk(){
        return exe;
    }

    public MyIDictionary<String, IValue> getSymTable(){
        return symTable;
    }

    public MyIList<IValue> getOut(){
        return out;
    }

    public String toString() {
        return "Execution Stack: " + exe.toString() + "\nSymbol table: " + symTable.toString() + "\nOutput: " + out.toString() + "\n";
    }
}
