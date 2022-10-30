package com.lb.console;

public class ConsolePrint implements Print{

    @Override
    public void info(Object s) {
        System.out.println(s);
        System.out.println();
    }
}
