package view;

import Type.boolType;
import Type.intType;
import Type.stringType;
import Values.IValue;
import Values.boolValue;
import Values.intValue;
import Values.stringValue;
import controller.Controller;
import exceptions.MyException;
import expressions.ArithExp;
import expressions.RelExpression;
import expressions.ValueExp;
import expressions.VarExp;
import model.*;
import repository.IRepository;
import repository.Repository;
import statements.*;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

import java.io.BufferedReader;
import java.util.Scanner;

public class View {
    public static void main(String[] args) throws MyException {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new intType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new intValue(2))), new PrintStmt(new VarExp("v"))));

        PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex1);
        IRepository repo1 = new Repository(prg1, "ex1.txt");
        Controller controller1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new intType()),
                new CompStmt(new VarDeclStmt("b", new intType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new intValue(2)), new
                                ArithExp(4, new ValueExp(new intValue(3)), new ValueExp(new intValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new
                                        intValue(1)))), new PrintStmt(new VarExp("b"))))));

        PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex2);
        IRepository repo2 = new Repository(prg2, "ex2.txt");
        Controller controller2 = new Controller(repo2);


        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new boolType()),
                new CompStmt(new VarDeclStmt("v", new intType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new boolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        intValue(2))), new AssignStmt("v", new ValueExp(new intValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex3);
        IRepository repo3 = new Repository(prg3, "ex3.txt");
        Controller controller3 = new Controller(repo3);


        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new stringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new stringValue("test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new intType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));
        PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex4);
        IRepository repo4 = new Repository(prg4, "ex4.txt");
        Controller controller4 = new Controller(repo4);

        IStmt ex5 = new CompStmt(new VarDeclStmt("a", new intType()), new CompStmt(new VarDeclStmt("b", new boolType()), new AssignStmt("a", new VarExp("b"))));
        PrgState prg5= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex5);
        IRepository repo5 = new Repository(prg5, "exException.txt");
        Controller controller5 = new Controller(repo5);

        IStmt ex6 = new CompStmt(new VarDeclStmt("x", new intType()),
                new CompStmt(new VarDeclStmt("y", new intType()),
                        new CompStmt(new AssignStmt("x", new ValueExp(new intValue(10))),
                                new CompStmt(new AssignStmt("y", new ValueExp(new intValue(5))),
                                        new IfStmt(new RelExpression(new VarExp("x"), new VarExp("y"), ">"),
                                                new PrintStmt(new ValueExp(new intValue(1))),
                                                new PrintStmt(new ValueExp(new intValue(0))))))));

        PrgState prg6= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex6);
        IRepository repo6 = new Repository(prg6, "ex6.txt");
        Controller controller6 = new Controller(repo6);

        IStmt ex7 = new CompStmt(new VarDeclStmt("varf", new stringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new stringValue("testEmpty.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new intType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));
        PrgState prg7= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex7);
        IRepository repo7 = new Repository(prg7, "ex7.txt");
        Controller controller7 = new Controller(repo7);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", "int v; v = 2; print(x)", controller1));
        menu.addCommand(new RunExample("2", "int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)", controller2));
        menu.addCommand(new RunExample("3", "bool a; int v; a = true; c (a) THEN (v = 2) ELSE (v = 3); print(v)", controller3));
        menu.addCommand(new RunExample("4", "string varf; varf = \"test.in\"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)", controller4));
        menu.addCommand(new RunExample("5", "EXCEPTION  int a; bool b; a=b;", controller5));
        menu.addCommand(new RunExample("6", "int x; int y; x = 10; y = 5; IF ( x > y) THEN (print(1)) ELSE (print(0))", controller6));
        menu.addCommand(new RunExample("7", "string varf; varf = \"testEmpty.in\"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)", controller7));

        menu.show();
    }
}