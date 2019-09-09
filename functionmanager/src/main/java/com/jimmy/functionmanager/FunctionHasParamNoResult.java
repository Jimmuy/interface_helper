package com.jimmy.functionmanager;

public abstract class FunctionHasParamNoResult<T> extends Function {
    public FunctionHasParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void function(T param);
}
