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

    @Override
    public IValue deepCopy() {
        return new boolValue(value);
    }

    public boolean getValue() {
        return value;
    }

    public String toString() {
        return "" + value;
    }

//    @Override
//    public boolean equals(IValue other) {
//        boolValue that = (boolValue) other;
//        return other instanceof boolValue && value.equals(that.value);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        boolValue boolValue = (boolValue) o;
        return value == boolValue.value;
    }

}
