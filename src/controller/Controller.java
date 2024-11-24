package controller;

import exceptions.ExecutionStackEmpty;
import exceptions.MyException;
import model.MyIStack;
import model.PrgState;
import repository.IRepository;
import repository.Repository;
import statements.IStmt;

import java.io.IOException;

public class Controller {
    private IRepository repository;

    public Controller(IRepository repository) {
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
        repository.logPrgStateExec();
        MyIStack<IStmt> exe = state.getStk();
        while (!exe.isEmpty()) {
            executeOne(state);
            repository.logPrgStateExec();
            System.out.println(state);
        }
    }

    public void reinitializeState(){
        PrgState curentState = repository.getCurentState();
        PrgState newState = new PrgState(curentState.getOriginalPrg());
        repository.addPrgState(newState);
    }
}
