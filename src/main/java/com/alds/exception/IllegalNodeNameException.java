package com.alds.exception;

public class IllegalNodeNameException extends RuntimeException {

    private String nodeName;
    private String nodeType;

    public IllegalNodeNameException(String nodeName, String nodeType){
        this.nodeName = nodeName;
        this.nodeType = nodeType;
    }
    @Override
    public String getMessage() {
        return nodeName+" is not valid name of "+nodeType;
    }
}
