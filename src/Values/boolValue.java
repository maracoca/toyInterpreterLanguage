package Values;

import Type.IType;
import Type.boolType;

public class boolValue implements IValue{
    private boolean value;

    public boolValue() {
        value = false;
    }

    public boolValue(boolean value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new boolType();
    }

    public boolean getValue() {
        return value;
    }

    public String toString() {
        return "" + value;
    }
}
