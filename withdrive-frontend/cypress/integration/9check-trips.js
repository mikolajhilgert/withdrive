describe('accept_application', () => {
    it("check_driver_trips", () => {
        cy.login("drivertester@withdrive.com","password")
        cy.visit('/')
        /* ==== Generated with Cypress Studio ==== */
        cy.get('#responsive-navbar-nav > :nth-child(2) > div > .nav-link').click();
        cy.get('[href="/driver-trips"] > .MuiMenuItem-root').click();
        cy.get('.MuiSwitch-input').uncheck();
        cy.get('.MuiSwitch-input').check();
        /* ==== End Cypress Studio ==== */
    })
    it("check_passenger_trips", () => {
        cy.login("tester@withdrive.com","password")
        cy.visit('/')
        /* ==== Generated with Cypress Studio ==== */
        cy.get('#responsive-navbar-nav > :nth-child(2) > div > .nav-link').click();
        cy.get('[href="/my-trips"] > .MuiMenuItem-root').click();
        cy.get('.MuiSwitch-input').uncheck();
        cy.get('.MuiSwitch-input').check();
        /* ==== End Cypress Studio ==== */
    })
})

