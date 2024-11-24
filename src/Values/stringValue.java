package Values;


import Type.IType;
import Type.stringType;

public class stringValue implements IValue {
    private String value;

    public stringValue(String value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new stringType();
    }

    @Override
    public IValue deepCopy() {
        return new stringValue(value);
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

//    @Override
//    public boolean equals(IValue other) {
//        stringValue that = (stringValue) other;
//        return other instanceof stringValue && value.equals(that.value); //?
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        stringValue that = (stringValue) o;
        return value.equals(that.value);
    }
}
