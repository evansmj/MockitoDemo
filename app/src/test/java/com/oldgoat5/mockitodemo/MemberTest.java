package com.oldgoat5.mockitodemo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MemberTest {
    private Member member;

    /**
     * Mockito JUnit Rule helps keeping tests clean.
     * It initializes mocks, validates usage and detects incorrect stubbing.
     */
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private AuthenticationService authenticationService;

    @Before
    public void setUp() {
        member = new Member(authenticationService, false);
    }

    @Test
    public void logoutTest() {
        member.logout();
        Mockito.verify(authenticationService).logout(); //By default uses times(1);
    }

    /**
     * Test to make sure after refactor of old code that
     * {@link AuthenticationService#isUnderMaintenance()} is only called once in
     * {@link Member#refresh()}.
     */
    @Test
    public void refreshTest() {
        member.refresh();
        Mockito.verify(authenticationService, Mockito.times(1)).isUnderMaintenance();
    }

    /**
     * Test to make sure methods were called in order.
     */
    @Test
    public void logoutApiOrderTest() {
        member.logout();

        InOrder inOrder = Mockito.inOrder(authenticationService);

        inOrder.verify(authenticationService).snooze();
        inOrder.verify(authenticationService).logout();
    }

    /**
     * Test that stubs a method which controls behavior we are checking.
     */
    @Test
    public void testApiUnderMaintenance() {
        Mockito.when(authenticationService.isUnderMaintenance()).thenReturn(true);

        member.refresh();

        Mockito.verify(authenticationService, Mockito.never()).update();
    }
}