package controller;

import exceptions.ExecutionStackEmpty;
import exceptions.MyException;
import model.MyIStack;
import model.PrgState;
import repository.Repository;
import statements.IStmt;

import java.io.IOException;

public class Controller {
    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public PrgState executeOne(PrgState state) throws MyException{
        MyIStack<IStmt> exe = state.getStk();
        if (!exe.isEmpty()){
            IStmt topStatement = exe.pop();
            return topStatement.execute(state);
        }else{
            throw new ExecutionStackEmpty();
        }
    }

    public void executeAll() throws MyException {
        PrgState state = repository.getCurentState();
        System.out.println(state);
        MyIStack<IStmt> exe = state.getStk();
        while (!exe.isEmpty()) {
            executeOne(state);
            System.out.println(state);
        }
    }
}
