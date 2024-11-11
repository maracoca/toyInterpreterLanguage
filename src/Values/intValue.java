package Values;

import Type.IType;
import Type.intType;

public class intValue implements IValue{
    private int value;

    public intValue() {
        value = 0;
    }

    public intValue(int value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new intType();
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "" + value;
    }

}
