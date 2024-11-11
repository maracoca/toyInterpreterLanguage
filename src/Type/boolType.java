package Type;

import Values.IValue;
import Values.boolValue;

public class boolType implements IType{

    public boolType() {}

    @Override
    public IValue getDefaultValue() {
        return new boolValue(false);
    }

    public String toString() {
        return "bool";
    }

    public boolean equals(Object another) {
        return another instanceof boolType;
    }

}
