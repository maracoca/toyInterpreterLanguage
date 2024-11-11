package repository;

import model.PrgState;

public class Repository implements IRepository {
    private PrgState state;

    public Repository(PrgState state) {
        this.state = state;
    }

    @Override
    public PrgState getCurentState() {
        return state;
    }
}
