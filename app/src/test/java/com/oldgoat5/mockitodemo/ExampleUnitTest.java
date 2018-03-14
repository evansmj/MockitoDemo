package com.oldgoat5.mockitodemo;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class ExampleUnitTest {

    @Test
    public void createMockTest() {
        AuthenticationService authenticationService = Mockito.mock(AuthenticationService.class);
        Member member = new Member(authenticationService, false);

        member.logout();

        Mockito.verify(authenticationService).logout();
    }
}