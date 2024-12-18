package view;

import controller.Controller;
import exceptions.MyException;

import java.io.IOException;

public class RunExample extends Command{
    private Controller controller;

    public RunExample(String k, String desc, Controller c) {
        super(k, desc);
        this.controller = c;
    }

    @Override
    public void execute() {
        try {
           // this.controller.reinitializeState();
            this.controller.allSteps();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
