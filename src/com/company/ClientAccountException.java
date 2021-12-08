package com.company;

public class ClientAccountException extends Exception{
    public ClientAccountException() {

    }
    public static class WrongDNIException extends Exception{
        public WrongDNIException(String message) {
            super(message);
        }
    }public static class WrongDNILengthException extends Exception{
        public WrongDNILengthException(String message) {
            super(message);
        }

    }
}
