describe('update_trip', () => {
    it("update_trip", () => {
        cy.login("drivertester@withdrive.com","password")
        cy.visit('/')
        //cy.visit('/sign-out')
        /* ==== Generated with Cypress Studio ==== */
        cy.get('#responsive-navbar-nav > :nth-child(2) > div > .nav-link').click();
        cy.get('[href="/driver-trips"] > .MuiMenuItem-root').click();
        cy.get(':nth-child(3) > .MuiSvgIcon-root > path').click();
        cy.get('.innerPage_authinner_big__ajjBj').click();
        cy.get('[min="0.00"]').clear();
        cy.get('[min="0.00"]').type('10');
        cy.get('form > .btn').click();
        /* ==== End Cypress Studio ==== */
        cy.wait(500)
        cy.visit('/sign-out')
    })
})

