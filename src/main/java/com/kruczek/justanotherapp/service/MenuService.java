package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.exception.ExitTypeException;
import com.kruczek.justanotherapp.exception.NoIDException;

import java.util.Scanner;

public class MenuService {

    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.printf("Pass client ID or leave it empty to get all. To \"KILL THA' APP\" type 'exit'\n");
        System.out.printf("Client ID:\n ");
    }

    public Integer getClientID() throws ExitTypeException, NoIDException {
        Integer clientID;
        String input = scanner.nextLine();

        try {
            clientID = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            if (input.equals("exit")) {
                throw new ExitTypeException();
            } else {
                throw new NoIDException();
            }
        }

        return clientID;
    }
}
