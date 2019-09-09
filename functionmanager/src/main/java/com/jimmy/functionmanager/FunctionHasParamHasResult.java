package com.jimmy.functionmanager;

public abstract class FunctionHasParamHasResult<T, P> extends Function {
    public FunctionHasParamHasResult(String functionName) {
        super(functionName);
    }

    public abstract T function(P param);
}
