package repository;

import exceptions.MyException;
import model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
//    PrgState getCurentState();
    void logPrgStateExec(PrgState programState) throws MyException;
    void addPrgState(PrgState newPrgState);
    void setPrgStates(List<PrgState> prgStates);
    List<PrgState> getPrgStates();
}
