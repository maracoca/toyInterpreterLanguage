package Type;

import Values.IValue;
import Values.intValue;

public class intType implements IType{
    public intType() {}

    @Override
    public IValue getDefaultValue() {
        return new intValue(0);
    }

    @Override
    public IType deepCopy() {
        return new intType();
    }

    public String toString() {
        return "int";
    }

    public boolean equals(Object another) {
        return another instanceof intType;
    }
}
