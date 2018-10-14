package com.kruczek.justanotherapp.service;

import com.kruczek.justanotherapp.exception.ExitTypeException;
import com.kruczek.justanotherapp.exception.NoIDException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    private Scanner scanner;

    @InjectMocks
    private MenuService menuService = new MenuService();


    @Test
    public void shouldAcceptID() {
        //given
        when(scanner.nextLine()).thenReturn("123");

        //when
        String ID = menuService.getClientID();

        //then
        assertThat(ID).isEqualTo("123");
    }

    @Test(expected = NoIDException.class)
    public void shouldThrowNoIDException() {
        //given
        when(scanner.nextLine()).thenReturn("gfsghgdckhgshgkgfxg");

        //when
        String ID = menuService.getClientID();
    }

    @Test
    public void shouldReturnEmptyClientID() {
        //given
        when(scanner.nextLine()).thenReturn("");

        //when
        String ID = menuService.getClientID();

        //then
        assertThat(ID).isEqualTo(null);
    }

    @Test(expected = ExitTypeException.class)
    public void shouldThrowExitTypeException() {
        //given
        when(scanner.nextLine()).thenReturn("exit");

        //when
        String ID = menuService.getClientID();

        //then
        assertThat(ID).isEqualTo("123");
    }
}