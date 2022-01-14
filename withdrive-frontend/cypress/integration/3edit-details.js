describe('editdetails', () => {
    it("edit_details", () => {
        cy.login("drivertester@withdrive.com","password")
        cy.visit('/my-profile')
        cy.wait(1000)
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

