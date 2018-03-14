package com.oldgoat5.mockitodemo;

/*********************************************************************
 * @author Michael Evans 
 * @since 1/8/18
 *********************************************************************/
public class Member {

    private AuthenticationService authenticationService;

    public Member(AuthenticationService authenticationService, boolean b) {
        this.authenticationService = authenticationService;
    }

    public void refresh() {
        if (this.authenticationService.isUnderMaintenance()) {
            return;
        }

        this.authenticationService.update();
    }

    /**
     * Make sure {@link AuthenticationService#snooze()}
     * is called before {@link AuthenticationService#logout()}.
     */
    public void logout() {
        this.authenticationService.snooze();
        this.authenticationService.logout();
    }
}
