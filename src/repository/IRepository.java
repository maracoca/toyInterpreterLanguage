package repository;

import exceptions.MyException;
import model.PrgState;

import java.io.IOException;

public interface IRepository {
    PrgState getCurentState();
    void logPrgStateExec() throws MyException;
    void addPrgState(PrgState newPrgState);
}
