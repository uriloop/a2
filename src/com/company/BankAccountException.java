package com.company;

public class BankAccountException  {



    public BankAccountException(){

    }

    public static class AccountNotFoundException extends Exception{

        public AccountNotFoundException(String message) {
            super(message);
        }
    }
    public static class AccountMaxOverdraftException extends Exception{

        public AccountMaxOverdraftException(String message) {
            super(message);
        }
    }
    public static class AccountOverdraftException extends Exception{

        public  AccountOverdraftException(String message)  {
            super(message);
        }
    }
    public static class AccountZeroUser extends Exception{

        public  AccountZeroUser(String message) {
            super(message);
        }
    }
    public static class TransferError extends Exception{

        public  TransferError(String message) {
            super(message);
        }
    }

}
