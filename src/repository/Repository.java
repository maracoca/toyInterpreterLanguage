package repository;

import exceptions.FileException;
import exceptions.MyException;
import model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> prgStates;
//    private PrgState state;
    private String logFilePath;

    public Repository(PrgState state, String logFilePath) {
        this.prgStates = new ArrayList<PrgState>();
        this.prgStates.add(state);
//        this.state = state;
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCurentState() {
//        return state;
        return this.prgStates.get(0);
    }

    @Override
    public void logPrgStateExec() throws MyException {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            throw new FileException();
        }
        logFile.println(this.getCurentState().toString());
        logFile.close();
    }

    @Override
    public void addPrgState(PrgState newPrgState) {
        this.prgStates.set(0, newPrgState);
//        state = newPrgState;
    }

    public List<PrgState> getPrgStates() {
        return this.prgStates;
    }

    public void setPrgStates(List<PrgState> prgStates) {
        this.prgStates = prgStates;
    }
}
