package model;

import Values.IValue;
import Values.stringValue;
import statements.IStmt;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exe;
    private MyIDictionary<String, IValue> symTable;
    private MyIList<IValue> out;
    private MyIDictionary<stringValue, BufferedReader> fileTable;
    private IStmt originalProgram;
    private MyIHeap<IValue> heap;

    public PrgState(MyIStack<IStmt> exe, MyIDictionary<String, IValue> symTable, MyIList<IValue> out, MyDictionary<stringValue, BufferedReader> fileTable, MyIHeap<IValue> heapTable, IStmt originalProgram) {
        this.exe=exe;
        this.symTable=symTable;
        this.out=out;
        this.fileTable=fileTable;
        this.heap = heapTable;
        this.originalProgram=originalProgram.deepCopy();
        this.exe.push(originalProgram);
    }

    public PrgState(IStmt statement){
        this.exe = new MyStack<IStmt>();
        this.symTable = new MyDictionary<>();
        this.out= new MyList<>();
        this.fileTable = new MyDictionary<>();
        this.heap=new MyHeap<>();
        this.originalProgram=statement;
        this.exe.push(statement);
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

    public MyIDictionary<stringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IStmt getOriginalPrg(){
        return originalProgram;
    }

    public MyIHeap<IValue> getHeap(){
        return heap;
    }

    public String toString() {
        return "Execution Stack: " + exe.toString() + "\nSymbol table: " + symTable.toString() + "\nOutput: " + out.toString()  + "\nHeap Table: "+ heap.toString()+ "\n File Table: " + fileTable.toString() + "\n";
    }
}
