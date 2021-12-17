describe('create_trip', () => {
    it("create_trip", () => {
        cy.login("drivertester@withdrive.com","password")
        cy.visit('/')
        /* ==== Generated with Cypress Studio ==== */
        cy.get('#responsive-navbar-nav > :nth-child(2) > div > .nav-link').click();
        cy.get('[href="/create-trip"] > .MuiMenuItem-root').click();
        cy.get(':nth-child(4) > .css-b62m3t-container > .css-1s2u09g-control > .css-319lph-ValueContainer > .css-6j8wv5-Input').click();
        cy.get('#react-select-2-option-1').click();
        cy.get('.css-1s2u09g-control > .css-319lph-ValueContainer > .css-6j8wv5-Input').click();
        cy.get('#react-select-3-option-8').click();
        cy.get(':nth-child(8) > .form-control').clear();
        cy.get(':nth-child(8) > .form-control').type('12-ABC-266');
        cy.get(':nth-child(10) > .form-control').click();
        cy.get(':nth-child(10) > .form-control').type('We will leave from the Central station!')
        // cy.get('.innerPage_authinner_big__ajjBj').click();
        cy.get('[min="0.00"]').type('5');
        //cy.get('.innerPage_authinner_big__ajjBj').click();
        cy.get('.btn').click();
        
        /* ==== End Cypress Studio ==== */
        cy.visit('/sign-out')
    })
})

