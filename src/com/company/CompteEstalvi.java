package com.company;

import java.util.ArrayList;
import java.util.List;

public class CompteEstalvi {
    private String numCompte;
    private double saldo;
    //Usuaris co-propietaris del compte
    private List<Client> llista_usuaris;



    public CompteEstalvi(String numCompte) {
        this.llista_usuaris = new ArrayList<>();
        this.numCompte = numCompte;
        saldo = 0;
    }


    public CompteEstalvi(String numCompte, List<CompteEstalvi> comptesExistents) throws BankAccountException.AccountNotFoundException {

        for (CompteEstalvi ce :
                comptesExistents) {
            if (ce.numCompte.equals(numCompte)) {
                this.numCompte = numCompte;
                this.llista_usuaris = ce.llista_usuaris;
                this.saldo = ce.saldo;
            }
        }
        if (this.numCompte == null)
            throw new BankAccountException.AccountNotFoundException(ExceptionMessage.ACCOUNT_NOT_FOUND);
    }

    /**
     * Afegeix un usuari d'aquest compte
     *
     * @param client
     * @return quantitat d'usuaris que té el compte
     **/
    public int addUser(Client client) throws ClientAccountException.WrongDNIException {

        for (Client c :
                llista_usuaris) {
            if (c.getDNI().equals(client.getDNI())) {
                throw new ClientAccountException.WrongDNIException(ExceptionMessage.WRONG_DNI);

            }
        }
        llista_usuaris.add(client);
        return llista_usuaris.size();
    }

    /**
     * Elimina un usuari d'aquest compte
     *
     * @param dni
     * @return quantitat d'usuaris que té el compte
     * @throws //BankAccountException
     **/
    public int removeUser(String dni) throws ClientAccountException.WrongDNIException {

        for (Client c :
                llista_usuaris) {
            if (c.getDNI().equals(dni)) {
                llista_usuaris.remove(c);
                System.out.println("usuari  esborrat");
                if (llista_usuaris.size() == 0)
                    try {
                        throw new BankAccountException.AccountZeroUser(ExceptionMessage.ACCOUNT_ZERO_USER);
                    } catch (BankAccountException.AccountZeroUser e) {
                        e.printStackTrace();
                    }

                return llista_usuaris.size();
            }
        }
        throw new ClientAccountException.WrongDNIException(ExceptionMessage.WRONG_DNI);

    }

    /**
     * Afegeix m diners al saldo
     *
     * @param amount
     */
    public void ingressar(double amount) {
        saldo += amount;
    }

    /**
     * Treu m diners del compte si n'hi han suficient
     *
     * @param m
     * @throws //BankAccountException
     */
    public void treure(double m) throws BankAccountException.TransferError {

        // si  ens passem del maxim descobert que son 300
        if (saldo - m < -300) {
            try {
                throw new BankAccountException.AccountMaxOverdraftException(ExceptionMessage.ACCOUNT_OVERDRAFT_300);
            } catch (BankAccountException.AccountMaxOverdraftException e) {
                e.printStackTrace();
            }
            throw new BankAccountException.TransferError(ExceptionMessage.TRANSFER_ERROR);

            // si tenim prous diners
        } else if (saldo - m >= 0) {
            saldo -= m;
        // si no  ens passem del maxim descobert que son -300€ pero estem per sota de zero
        } else {
            saldo-=m;
            try {
                throw new BankAccountException.AccountOverdraftException(ExceptionMessage.ACCOUNT_OVERDRAFT);
            } catch (BankAccountException.AccountOverdraftException e) {
                e.printStackTrace();
            }
        }

    }




    public String getNumCompte() {
        return numCompte;
    }

    public double getSaldo() {
        if (saldo < 0) {
            try {
                throw new BankAccountException.AccountOverdraftException(ExceptionMessage.ACCOUNT_OVERDRAFT);
            } catch (BankAccountException.AccountOverdraftException e) {
                e.printStackTrace();
            }
        }
        return saldo;
    }

    public List<Client> getLlista_usuaris() {

        return llista_usuaris;
    }
}
