package Type;

import Values.IValue;
import Values.stringValue;

public class stringType implements IType{
    public stringType() {}

    @Override
    public IValue getDefaultValue() {
        return new stringValue("");
    }

    @Override
    public IType deepCopy() {
        return new stringType();
    }

    public String toString() {
        return "string";
    }

    public boolean equals(Object another){
        return another instanceof stringType;
    }
}
