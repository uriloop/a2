package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        List<CompteEstalvi> comptesDestalvis = new ArrayList<>();

        crearComptesAmbUsuaris(comptesDestalvis);

        comptesDestalvis.toString();
        ///////////////
        System.out.println("\n1 ACCOUNT_NOT_FOUND     Introduim un numero de compte que no exiseix --- ");
        CompteEstalvi ce;
        try {
            ce = new CompteEstalvi("32353763845734750475075230", comptesDestalvis);         // fem saltar l'excepció de no existeix el compte

        } catch (BankAccountException.AccountNotFoundException e) {
            e.printStackTrace();
        }


//////////////
        System.out.println("\nIntroduim un numero de compte que existeix --- accedim a aquest compte");
        try {
            ce = new CompteEstalvi("1111111111111111110", comptesDestalvis);
            System.out.println("\nIntroduim/creem un segon usuari al compte");
            try {
                ce.addUser(new Client("oriol", "López", "111111118"));
            } catch (ClientAccountException.WrongDNIException e) {
                e.printStackTrace();
            } catch (ClientAccountException.WrongDNILengthException e) {
                e.printStackTrace();
            }

            System.out.println("\n2 WRONG_DNI    Tornem a repetir el mateix pas per fer saltar l'excepció de dni incorrecte perque ja existeix el dni  ---- ");
            try {
                ce.addUser(new Client("oriol", "López", "111111118"));
            } catch (ClientAccountException.WrongDNIException e) {
                e.printStackTrace();
            } catch (ClientAccountException.WrongDNILengthException e) {
                e.printStackTrace();
            }
            System.out.println("\n3 WRONG_DNI_LENGTH    Tornem a repetir el mateix pas per fer saltar l'excepció de dni incorrecte perque introduirem un dni amb menys digits  ---- ");

            try {
                ce.addUser(new Client("oriol", "López", "11111111"));
            } catch (ClientAccountException.WrongDNIException e) {
                e.printStackTrace();
            } catch (ClientAccountException.WrongDNILengthException e) {
                e.printStackTrace();
            }

            System.out.println("\nDemanem el saldo --- no salta cap excepció perque no estem al descobert");


            System.out.println(ce.getSaldo());
            System.out.println("\nIngressem diners al compte 100 eur  --- no salta cap excepció");

            ce.ingressar(100);


            System.out.println("\n4 OVERDRAFT      Treiem 200 per fer saltar el overdraft exception  ----  (Al restar aquests diners ens quedarem en negatiu) \n Aquest banc permet tenir fins a 300 euros en descobert");


            try {
                ce.treure(200);
            } catch (BankAccountException.TransferError e) {
                e.printStackTrace();
            }


            System.out.println("\n5 OVERDRAFT Exception       Demanem el saldo --- i torna a donar un overdraft ja que estem en negatiu");
            System.out.println(ce.getSaldo());


            System.out.println("\n6 MAX_OVERDRAFT + 7 TRANSFER_ERROR       Treiem 201 euros més que ens deixaria en -301, com no podem passar de 300 en descobert ens donara error de transferencia");


            try {
                ce.treure(201);
            } catch (BankAccountException.TransferError e) {
                e.printStackTrace();
            }

            System.out.println("\n8 OVERDRAFT     Demanem el saldo per fer saltar el overdraft exception altre cop ja que hi ha -100  eur  --- ");

            System.out.println(ce.getSaldo());


            System.out.println("\n9 WRONG_DNI               Intentem esborrar un usuari que no existeix ----   ");
            try {
                ce.removeUser("090909090");
            } catch (ClientAccountException.WrongDNIException e) {
                e.printStackTrace();
            }
            System.out.println("\nEsborrem un usuari del compte  --- no salta excepció perque encara queda altre user i el dni es correcte");
            try {
                ce.removeUser("111111110");
            } catch (ClientAccountException.WrongDNIException e) {
                e.printStackTrace();
            }

            System.out.println("\n10 ZERO_USER            Esborrem l'últim usuari del compte per fer saltar el ZERO_USER exception  ");
            try {

                ce.removeUser("111111118");

            } catch (ClientAccountException.WrongDNIException e) {
                e.printStackTrace();
            }


        } catch (BankAccountException.AccountNotFoundException e) {
            e.printStackTrace();
        }
        ////////////


    }

    public static void crearComptesAmbUsuaris(List<CompteEstalvi> comptesDestalvis) {

        for (int i = 0; i < 10; i++) {
            comptesDestalvis.add(new CompteEstalvi("111111111111111111" + Integer.toString(i)));
        }
        int i = 0;
        for (CompteEstalvi ce :
                comptesDestalvis) {
            try {
                ce.addUser(new Client(("nom"), "cognom", "11111111" + Integer.toString(i)));
            } catch (ClientAccountException.WrongDNIException e) {
                e.printStackTrace();
            } catch (ClientAccountException.WrongDNILengthException e) {
                e.printStackTrace();
            }
            i++;
        }

    }
}
