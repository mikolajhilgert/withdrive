describe('accept_application', () => {
    it("accept_application", () => {
        cy.login("driver@withdrive.com","password")
        cy.visit('/')
        /* ==== Generated with Cypress Studio ==== */
        cy.get('#responsive-navbar-nav > :nth-child(2) > div > .nav-link').click();
        cy.get('[href="/driver-trips"] > .MuiMenuItem-root').click();
        /* ==== End Cypress Studio ==== */
        /* ==== Generated with Cypress Studio ==== */
        cy.get('.MuiDataGrid-cell--withRenderer > div > :nth-child(2) > .MuiSvgIcon-root > path').click();
        cy.get('[data-field="message"] > .btn').click();
        cy.get('.mm-popup__btn').click();
        cy.get('.btn-success').click();
        cy.get('.MuiDataGrid-row > [data-field="Actions"] > :nth-child(1)').click();
        cy.get('.mm-popup__btn').click();
        cy.get('.MuiDataGrid-row > [data-field="Actions"] > :nth-child(2)').click();
        cy.get('.mm-popup__btn').click();
        cy.get('.css-78trlr-MuiButtonBase-root-MuiIconButton-root').click();
        cy.get('[aria-label="view"] > .MuiSvgIcon-root').click();
        cy.wait(1000)
        cy.get('[href="/sign-out"]').click();
        /* ==== End Cypress Studio ==== */
    })
})

