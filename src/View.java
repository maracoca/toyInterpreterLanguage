import Type.boolType;
import Type.intType;
import Values.IValue;
import Values.boolValue;
import Values.intValue;
import controller.Controller;
import exceptions.MyException;
import expressions.ArithExp;
import expressions.ValueExp;
import expressions.VarExp;
import model.*;
import repository.Repository;
import statements.*;

import java.util.Scanner;

public class View {
    public static void main(String[] args) throws MyException {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new intType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new intValue(2))), new PrintStmt(new VarExp("v"))));

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new intType()),
                new CompStmt(new VarDeclStmt("b", new intType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new intValue(2)), new
                                ArithExp(4, new ValueExp(new intValue(3)), new ValueExp(new intValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new
                                        intValue(1)))), new PrintStmt(new VarExp("b"))))));


        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new boolType()),
                new CompStmt(new VarDeclStmt("v", new intType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new boolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        intValue(2))), new AssignStmt("v", new ValueExp(new intValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        IStmt ex4 = new CompStmt(new VarDeclStmt("a", new intType()), new CompStmt(new VarDeclStmt("b", new boolType()), new AssignStmt("a", new VarExp("b"))));

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIList<IValue> out = new MyList<>();
        MyIDictionary<String, IValue> symbolTable = new MyDictionary<>();

        PrgState prgState = new PrgState(exeStack, symbolTable, out);

        Repository repo = new Repository(prgState);

        Controller controller = new Controller(repo);

        Scanner userInput = new Scanner(System.in);

        System.out.println("int v; v = 2; print(x)");
        System.out.println("int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)");
        System.out.println("bool a; int v; a = true; IF (a) THEN (v = 2) ELSE (v = 3); print(v)");
        System.out.println("int a; bool b; a=b");
        System.out.print("Choose a program to run: ");
        int option = userInput.nextInt();

        if (option == 1) {
            exeStack.push(ex1);
        } else if (option == 2) {
            exeStack.push(ex2);
        } else if (option == 3) {
            exeStack.push(ex3);
        }else if(option == 4){
            exeStack.push(ex4);
        } else throw new MyException("incorrect option");

        try {
            controller.executeAll();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }
}