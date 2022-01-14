describe('editdetails', () => {
    it("edit_details", () => {
        cy.login("drivertester@withdrive.com","password")
        cy.visit('/')
        cy.wait(1000)
        /* ==== Generated with Cypress Studio ==== */
        cy.get('[href="/my-profile"]').click();
        cy.get('.innerPage_authwrapper__36oWg').click();
        cy.get(':nth-child(4) > .form-control').clear();
        cy.get(':nth-child(4) > .form-control').type('Black');
        cy.get('.innerPage_authinner_form__2JOHt').click();
        cy.get('.btn').click();
        
        cy.get('[href="/my-profile"]').click();
        /* ==== End Cypress Studio ==== */
        cy.visit('/sign-out')
    })
    
})

