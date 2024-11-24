package exceptions;

public class DictionaryException extends MyException{
    public DictionaryException() {
        super("Key not found");
    }
}
