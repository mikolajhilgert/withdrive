describe('apply_to_trip', () => {
    it("apply_to_trip", () => {
        cy.login("tester@withdrive.com","password")
        cy.visit('/')
        cy.get('[href="/view-trips"]').click();
        cy.get('.btn').click();
        cy.get('.btn').click();
        cy.get('[data-cy=text]').type('This is my application for the trip!!!')
        cy.get('.btn').click();
        /* ==== Generated with Cypress Studio ==== */
        cy.get('.btn').click();
        cy.get('.mm-popup__box__body').click();
        /* ==== End Cypress Studio ==== */
        cy.visit('/sign-out')
    })
})

