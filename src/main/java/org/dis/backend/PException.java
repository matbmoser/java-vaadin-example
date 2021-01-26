package org.dis.backend;

public class PException extends Exception{
    public PException() { super(); }
    public PException(String message) { super(message); }
    public PException(String message, Throwable cause) { super(message, cause); }
    public PException(Throwable cause) { super(cause); }
}
