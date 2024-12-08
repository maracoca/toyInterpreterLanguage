package controller;

import Values.IValue;
import Values.refValue;
import exceptions.ExecutionStackEmpty;
import exceptions.MyException;
import model.MyIStack;
import model.PrgState;
import repository.IRepository;
import repository.Repository;
import statements.IStmt;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        while (!state.getStk().isEmpty()) {
            executeOne(state);
            repository.logPrgStateExec();
            state.getHeap().setContent(
                    enhancedGarbageCollector(
                            getAddressesFromSymTable(state.getSymTable().getContent().values()),
                            state.getHeap().getContent())
            );
            repository.logPrgStateExec();
        }
    }

    private List<Integer> getAddressesFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof refValue)
                .map(v -> {refValue v1 = (refValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }


    private Map<Integer, IValue> enhancedGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        Set<Integer> reachableAddresses = new HashSet<>(symTableAddr);

        // Recursive logic directly in enhancedGarbageCollector
        List<Integer> newAddresses = heap.entrySet().stream()
                .filter(e -> e.getValue() instanceof refValue)  // Only process refValues
                .filter(e -> reachableAddresses.contains(e.getKey())) // Key must already be reachable
                .map(e -> ((refValue) e.getValue()).getAddress()) // Extract referenced addresses
                .filter(addr -> !reachableAddresses.contains(addr)) // Only add new addresses
                .collect(Collectors.toList());

        if (!newAddresses.isEmpty()) {
            reachableAddresses.addAll(newAddresses);  // Add newly discovered addresses
            return enhancedGarbageCollector(new ArrayList<>(reachableAddresses), heap); // Recur with updated reachable addresses
        }

        // Keep the heap entries whose keys are in the reachable set
        return heap.entrySet().stream()
                .filter(e -> reachableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void reinitializeState(){
        PrgState curentState = repository.getCurentState();
        PrgState newState = new PrgState(curentState.getOriginalPrg());
        repository.addPrgState(newState);
    }

}
