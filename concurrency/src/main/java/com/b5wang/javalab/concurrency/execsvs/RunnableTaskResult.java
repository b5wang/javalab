package com.b5wang.javalab.concurrency.execsvs;

public class RunnableTaskResult {

    private String msg = null;

    public RunnableTaskResult(){
    }

    public RunnableTaskResult(String msg){
        this.msg = msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

}
