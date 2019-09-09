package com.jimmy.functionmanager;

public abstract class Function {
    protected Function(String functionName) {
        this.functionName = functionName;
    }

    public String functionName;
}
