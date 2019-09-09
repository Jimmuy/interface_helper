package com.jimmy.functionmanager;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FunctionManager {
    private Map<String, Function> functionMap = new HashMap<>();

    private FunctionManager() {

    }

    public static FunctionManager getInstance() {
        return SingleTon.instance;
    }

    private static class SingleTon {

        private static final FunctionManager instance = new FunctionManager();
    }


    public void addFunction(Function function) {
        if (functionMap.containsKey(function.functionName)) {
            throw new IllegalArgumentException("already added function");
        } else {
            functionMap.put(function.functionName, function);
        }
    }

    public void invokeFunctuion(String functionName) {
        invokeFunction(functionName, null);
    }

    public <RESULT> RESULT invokeFunction(String functionName, Class<RESULT> result) {
        return invokeFunction(functionName, null, result);
    }

    public <PARAM> void invokeFunction(String functionName, PARAM param) {
        invokeFunction(functionName, param, null);
    }

    public <RESULT, PARAM> RESULT invokeFunction(String functionName, @Nullable PARAM param, @Nullable Class<RESULT> result) {
        if (TextUtils.isEmpty(functionName)) throw new IllegalArgumentException("function name cannot be empty");
        Function function = functionMap.get(functionName);
        if (function != null) {
            if (function instanceof FunctionNoParamNoResult) {
                ((FunctionNoParamNoResult) function).function();
            } else if (function instanceof FunctionNoParamHasResult) {
                if (result != null) {
                    return result.cast(((FunctionNoParamHasResult) function).function());
                }
            } else if (function instanceof FunctionHasParamNoResult) {
                ((FunctionHasParamNoResult<PARAM>) function).function(param);
            } else if (function instanceof FunctionHasParamHasResult) {
                if (result != null) {
                    return result.cast(((FunctionHasParamHasResult) function).function(param));
                }
            }
        } else {
            Log.d("FunctionManager", "no function matching" + functionName);
        }
        return null;
    }

    //you need remove function when activity destroy or fragment destroy, or it will be leak
    public void remove(String functionName) {
        if (functionMap.containsKey(functionName)) {
            functionMap.remove(functionName);
        } else {
            throw new IllegalArgumentException("no matching function found");
        }
    }
}
