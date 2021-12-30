describe('seach_for_trips', () => {
    it("seach_for_trips", () => {
        cy.login("tester@withdrive.com","password")
        cy.visit('/')
        cy.get('[href="/view-trips"]').click();
        /* ==== Generated with Cypress Studio ==== */
        cy.get('.css-6j8wv5-Input').click();
        cy.get('#react-select-2-option-0').click();
        cy.get('.css-6j8wv5-Input').click();
        cy.get('#react-select-2-input').clear();
        cy.get('#react-select-2-input').type('ein');
        cy.get('#react-select-2-option-4').click();
        /* ==== End Cypress Studio ==== */
    })
})

