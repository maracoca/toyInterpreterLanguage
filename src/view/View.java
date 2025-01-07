package view;

import Type.boolType;
import Type.intType;
import Type.refType;
import Type.stringType;
import Values.IValue;
import Values.boolValue;
import Values.intValue;
import Values.stringValue;
import controller.Controller;
import exceptions.MyException;
import expressions.*;
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

        PrgState prg1= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex1);
        IRepository repo1 = new Repository(prg1, "ex1.txt");
        Controller controller1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new intType()),
                new CompStmt(new VarDeclStmt("b", new intType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new intValue(2)), new
                                ArithExp(4, new ValueExp(new intValue(3)), new ValueExp(new intValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new
                                        intValue(1)))), new PrintStmt(new VarExp("b"))))));

        PrgState prg2= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex2);
        IRepository repo2 = new Repository(prg2, "ex2.txt");
        Controller controller2 = new Controller(repo2);


        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new boolType()),
                new CompStmt(new VarDeclStmt("v", new intType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new boolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        intValue(2))), new AssignStmt("v", new ValueExp(new intValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        PrgState prg3= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex3);
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
        PrgState prg4= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex4);
        IRepository repo4 = new Repository(prg4, "ex4.txt");
        Controller controller4 = new Controller(repo4);


        IStmt ex5 = new CompStmt(new VarDeclStmt("a", new intType()), new CompStmt(new VarDeclStmt("b", new boolType()), new AssignStmt("a", new VarExp("b"))));
        PrgState prg5= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex5);
        IRepository repo5 = new Repository(prg5, "exException.txt");
        Controller controller5 = new Controller(repo5);

        IStmt ex6 = new CompStmt(new VarDeclStmt("x", new intType()),
                new CompStmt(new VarDeclStmt("y", new intType()),
                        new CompStmt(new AssignStmt("x", new ValueExp(new intValue(10))),
                                new CompStmt(new AssignStmt("y", new ValueExp(new intValue(5))),
                                        new IfStmt(new RelExpression(new VarExp("x"), new VarExp("y"), ">"),
                                                new PrintStmt(new ValueExp(new intValue(1))),
                                                new PrintStmt(new ValueExp(new intValue(0))))))));

        PrgState prg6= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex6);
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
        PrgState prg7= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex7);
        IRepository repo7 = new Repository(prg7, "ex7.txt");
        Controller controller7 = new Controller(repo7);

        // example 8: Ref int v;new(v, 20); Ref Ref int a; new(a, v); print(v); print(a)
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new refType(new intType())),
                new CompStmt(new New("v", new ValueExp(new intValue(20))),
                        new CompStmt(new VarDeclStmt("a", new refType(new refType(new intType()))),
                                new CompStmt(new New("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        PrgState prg8 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, IValue>(),
                new MyList<IValue>(), new MyDictionary<>(), new MyHeap<IValue>(), ex8);
        IRepository repo8 = new Repository(prg8, "ex8.txt");
        Controller controller8 = new Controller(repo8);

        // example 9: Ref int v; new(v, 20); Ref Ref int a; new(a, v); print(rH(v)); print(rH(rH(a)) + 5)
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new refType(new intType())),
                new CompStmt(new New("v", new ValueExp(new intValue(20))),
                        new CompStmt(new VarDeclStmt("a", new refType(new refType(new intType()))),
                                new CompStmt(new New("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(1, new rHExp(new rHExp(new VarExp("a"))), new ValueExp(new intValue(5)))))))));

        PrgState prg9= new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex9);
        IRepository repo9 = new Repository(prg9, "ex9.txt");
        Controller controller9 = new Controller(repo9);

        // example 10: Ref int v; new(v, 20); print(rH(v)); wH(v, 30); print(rH(v) + 5);
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new refType(new intType())),
                new CompStmt(new New("v", new ValueExp(new intValue(20))),
                        new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),
                                new CompStmt(new wHStmt("v", new ValueExp(new intValue(30))),
                                        new PrintStmt(new ArithExp(1, new rHExp(new VarExp("v")), new ValueExp(new intValue(5))))))));
        PrgState prg10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex10);
        IRepository repo10 = new Repository(prg10, "ex10.txt");
        Controller controller10 = new Controller(repo10);

        // example 11: Ref int v; new(v, 20); Ref Ref int a; new(a, v); new(v, 30); print(rH(rH(a)))
        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new refType(new intType())),
                new CompStmt(new New("v", new ValueExp(new intValue(20))),
                        new CompStmt(new VarDeclStmt("a", new refType(new refType(new intType()))),
                                new CompStmt(new New("a", new VarExp("v")),
                                        new CompStmt(new New("v", new ValueExp(new intValue(30))),
                                                new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));
        PrgState prg11 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex11);
        IRepository repo11 = new Repository(prg11, "ex11.txt");
        Controller controller11 = new Controller(repo11);

        // example 12: int v; v=4; (while (v>0) print(v); v=v-1); print(v)
        IStmt ex12 = new CompStmt(new VarDeclStmt("v", new intType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new intValue(4))),
                        new CompStmt(new WhileStmt(new RelExpression(new VarExp("v"), new ValueExp(new intValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValueExp(new intValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
        PrgState prg12 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex12);
        IRepository repo12 = new Repository(prg12, "ex12.txt");
        Controller controller12 = new Controller(repo12);

        IStmt ex13 = new CompStmt(new VarDeclStmt("v", new intType()),
                new CompStmt(new VarDeclStmt("a", new refType(new intType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new intValue(10))),
                                new CompStmt(new New("a", new ValueExp(new intValue(22))),
                                        new CompStmt(new forkStmt(new CompStmt(new wHStmt("a", new ValueExp(new intValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new intValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a")))))))));
        PrgState prg13 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex13);
        IRepository repo13 = new Repository(prg13, "ex13.txt");
        Controller controller13 = new Controller(repo13);
        

        IStmt ex14 = new CompStmt(
                new VarDeclStmt("a", new refType(new intType())),
                new CompStmt(
                        new VarDeclStmt("v", new intType()),
                        new CompStmt(
                                new New("a", new ValueExp(new intValue(10))),
                                new CompStmt(
                                        new forkStmt(
                                                new CompStmt(
                                                        new AssignStmt("v", new ValueExp(new intValue(20))),
                                                        new CompStmt(
                                                                new forkStmt(
                                                                        new CompStmt(
                                                                                new wHStmt("a", new ValueExp(new intValue(40))),
                                                                                new PrintStmt(new rHExp(new VarExp("a")))
                                                                        )
                                                                ),
                                                                new PrintStmt(new VarExp("v"))
                                                        )
                                                )
                                        ),
                                        new CompStmt(
                                                new AssignStmt("v", new ValueExp(new intValue(30))),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new rHExp(new VarExp("a")))
                                                )
                                        )
                                )
                        )
                )
        );


        PrgState prg14 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex14);
        IRepository repo14 = new Repository(prg14, "ex14.txt");
        Controller controller14 = new Controller(repo14);


        IStmt ex15 = new CompStmt(new VarDeclStmt("varf", new stringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new stringValue("test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new forkStmt(new CompStmt(new VarDeclStmt("varc", new intType()),
                                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                new PrintStmt(new VarExp("varc"))))),
                                        new CompStmt(new VarDeclStmt("varc", new intType()),
                                                new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                new closeRFile(new VarExp("varf")))))))));
        PrgState prg15 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), ex15);
        IRepository repo15 = new Repository(prg15, "ex15.txt");
        Controller controller15 = new Controller(repo15);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", "int v; v = 2; print(x)", controller1));
        menu.addCommand(new RunExample("2", "int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)", controller2));
        menu.addCommand(new RunExample("3", "bool a; int v; a = true; c (a) THEN (v = 2) ELSE (v = 3); print(v)", controller3));
        menu.addCommand(new RunExample("4", "string varf; varf = \"test.in\"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)", controller4));
        menu.addCommand(new RunExample("5", "EXCEPTION  int a; bool b; a=b;", controller5));
        menu.addCommand(new RunExample("6", "int x; int y; x = 10; y = 5; IF ( x > y) THEN (print(1)) ELSE (print(0))", controller6));
        menu.addCommand(new RunExample("7", "string varf; varf = \"testEmpty.in\"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)", controller7));
        menu.addCommand(new RunExample("8", "Ref int v;new(v, 20); Ref Ref int a; new(a, v); print(v); print(a)", controller8));
        menu.addCommand(new RunExample("9", "Ref int v; new(v, 20); Ref Ref int a; new(a, v); print(rH(v)); print(rH(rH(a)) + 5)", controller9));
        menu.addCommand(new RunExample("10", "Ref int v; new(v, 20); print(rH(v)); wH(v, 30); print(rH(v) + 5);", controller10));
        menu.addCommand(new RunExample("11", "Ref int v; new(v, 20); Ref Ref int a; new(a, v); new(v, 30); print(rH(rH(a)))", controller11));
        menu.addCommand(new RunExample("12", "int v; v=4; (while (v>0) print(v); v=v-1); print(v)", controller12));
        menu.addCommand(new RunExample("13", "int v; Ref int a; v = 10; new(a,22); fork(wH(a,30); v = 32; print(v); print(rH(a))); print(v); print(rH(a))", controller13));
        menu.addCommand(new RunExample("14", "Ref int a;int v;New(a, 10);fork(v = 20;fork(WriteToHeap(a, 40);print(readFromHeap(a)));print(v));v = 30;print(v);print(readFromHeap(a))", controller14));
        menu.addCommand(new RunExample("15", "string varf; varf=\"test.a\"; openRFile varf; fork(int varc; reafFile(varf,varc); print(varc)); int varc; reafFFile(varf,varc); print(varc); closeFile(varf);", controller15));
        menu.show();
    }
}