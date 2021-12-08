package com.company;

public class Client {
    private String Nom;
    private String Cognoms;
    private String DNI;

    public Client(String nom, String cognoms, String DNI) throws ClientAccountException.WrongDNIException, ClientAccountException.WrongDNILengthException {
        Nom = nom;
        Cognoms = cognoms;
        if (DNI.length()!=9) {
            throw new ClientAccountException.WrongDNILengthException(ExceptionMessage.WRONG_DNI_LENGTH);

        }else {
            this.DNI = DNI;
        }
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCognoms() {
        return Cognoms;
    }

    public void setCognoms(String cognoms) {
        Cognoms = cognoms;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }



}
