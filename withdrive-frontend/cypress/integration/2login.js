describe('login', () => {
    it("login", () => {
        /* ==== Generated with Cypress Studio ==== */
        cy.visit('http://localhost:3000');
        cy.get('[href="/sign-in"]').click();
        /* ==== End Cypress Studio ==== */
        /* ==== Generated with Cypress Studio ==== */
        cy.get(':nth-child(3) > .form-control').clear();
        cy.get(':nth-child(3) > .form-control').type('tester@withdrive.com');
        cy.get(':nth-child(4) > .form-control').clear();
        cy.get(':nth-child(4) > .form-control').type('passwords');
        cy.get('.btn').click();
        cy.wait(1000)
        cy.get(':nth-child(4) > .form-control').clear();
        cy.get(':nth-child(4) > .form-control').type('password');
        cy.get('.btn').click();
        cy.wait(1000)
        cy.visit('/sign-out')
        /* ==== End Cypress Studio ==== */
    })
})

