package controller;

import Type.IType;
import Values.IValue;
import Values.refValue;
import exceptions.ExecutionStackEmpty;
import exceptions.FileException;
import exceptions.MyException;
import model.MyDictionary;
import model.MyIDictionary;
import model.MyIStack;
import model.PrgState;
import repository.IRepository;
import repository.Repository;
import statements.IStmt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository) throws MyException {
        try {
            this.repository = repository;
            typecheck();
        }catch (MyException e) {
            System.out.println("Type check error\n" + e.getMessage());
        }
    }

    public void typecheck() throws MyException {
            PrgState prg = repository.getPrgStates().get(0);
            MyIDictionary<String, IType> typeEnv = new MyDictionary<>();
            prg.getOriginalPrg().typeCheck(typeEnv);
    }


    public void oneStepForAllPrg(List<PrgState> programStatesList) throws InterruptedException {
        programStatesList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<PrgState>> callList = programStatesList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());
        List<PrgState> newProgramStatesList = this.executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        programStatesList.addAll(newProgramStatesList);
        programStatesList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });
        this.repository.setPrgStates(programStatesList);
    }

    public void allSteps() throws InterruptedException {
        this.executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = this.removeCompletedPrg(this.repository.getPrgStates());
        while (prgList.size() > 0) {
            this.conservativeGarbageCollector(prgList);
            this.oneStepForAllPrg(prgList);
            prgList = this.removeCompletedPrg(this.repository.getPrgStates());
        }
        this.executor.shutdownNow();
        this.repository.setPrgStates(prgList);
    }


    List<PrgState> removeCompletedPrg(List<PrgState> inProgramList) {
        return inProgramList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    private List<Integer> getAddressesFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof refValue)
                .map(v -> {refValue v1 = (refValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }


//    private Map<Integer, IValue> enhancedGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
//        Set<Integer> reachableAddresses = new HashSet<>(symTableAddr);
//
//        
//        List<Integer> newAddresses = heap.entrySet().stream()
//                .filter(e -> e.getValue() instanceof refValue)  // Only process refValues
//                .filter(e -> reachableAddresses.contains(e.getKey())) // Key must already be reachable
//                .map(e -> ((refValue) e.getValue()).getAddress()) // Extract referenced addresses
//                .filter(addr -> !reachableAddresses.contains(addr)) // Only add new addresses
//                .collect(Collectors.toList());
//
//        if (!newAddresses.isEmpty()) {
//            reachableAddresses.addAll(newAddresses);  // Add newly discovered addresses
//            return enhancedGarbageCollector(new ArrayList<>(reachableAddresses), heap); // Recur with updated reachable addresses
//        }
//
//        // Keep the heap entries whose keys are in the reachable set
//        return heap.entrySet().stream()
//                .filter(e -> reachableAddresses.contains(e.getKey()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }
private void conservativeGarbageCollector(List<PrgState> prgList) {
    if (prgList.isEmpty()) return;

    Map<Integer, IValue> heapContent = prgList.get(0).getHeap().getContent();

    //  all values from symbol tables in all program states
    List<IValue> symbolTableValues = prgList.stream()
            .flatMap(prg -> prg.getSymTable().getContent().values().stream())
            .collect(Collectors.toList());

    List<Integer> symbolTableAddresses = getAddressesFromSymTable(symbolTableValues);
    boolean change = true;
    List<Integer> allReferencedAddresses = new ArrayList<>(symbolTableAddresses);

    while (change) {
        change = false;

        //  addresses indirectly referenced by heap entries
        List<Integer> newAddresses = heapContent.entrySet().stream()
                .filter(e -> e.getValue() instanceof refValue)            // Only process refValues
                .filter(e -> allReferencedAddresses.contains(e.getKey())) // Key must already be reachable
                .map(e -> ((refValue) e.getValue()).getAddress())         // Extract referenced addresses
                .filter(addr -> !allReferencedAddresses.contains(addr))   // Add only new addresses
                .collect(Collectors.toList());

        if (!newAddresses.isEmpty()) {
            change = true;
            allReferencedAddresses.addAll(newAddresses);
        }
    }

    Map<Integer, IValue> updatedHeap = heapContent.entrySet().stream()
            .filter(e -> allReferencedAddresses.contains(e.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    prgList.get(0).getHeap().setContent(updatedHeap);
}



//    public void reinitializeState(){
//        PrgState curentState = repository.getCurentState();
//        PrgState newState = new PrgState(curentState.getOriginalPrg());
//        repository.addPrgState(newState);
//    }

}
