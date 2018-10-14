package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.exception.ExitTypeException;
import com.kruczek.justanotherapp.exception.NoIDException;

import java.util.Scanner;

public class MenuService {

    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.printf("\n================================================================\n");
        System.out.printf("Pass client ID or leave it empty to get all. To \"KILL THA' APP\" type 'exit'\n");
        System.out.printf("Client ID:\n ");
    }

    public String getClientID() throws ExitTypeException, NoIDException {
        String clientID = scanner.nextLine();

        if (clientID.length() > 6) {
            throw new NoIDException();
        }

        if (clientID.equals("")) {
            return null;
        }

        if (clientID.equals("exit")) {
            throw new ExitTypeException();
        }

        return clientID;
    }

    public Boolean isSavingToFile() {
        System.out.println("Type: s to save results in file. In other case results will be only show in terminal");
        return new Scanner(System.in).nextLine().equals("s");
    }
}
