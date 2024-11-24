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

    @Override
    public IValue deepCopy() {
        return new intValue(value);
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "" + value;
    }

//    @Override
//    public boolean equals(IValue other) {
//        intValue that = (intValue) other;
//        return other instanceof intValue && value.equals(that.value);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        intValue intValue = (intValue) o;
        return value == intValue.value;
    }

}
