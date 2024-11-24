package Values;

import Type.IType;

public interface IValue {
    IType getType();
//    boolean equals(IValue other);
    IValue deepCopy();
}
