describe('delete_user', () => {
    it("delete_user", () => {
        cy.login("admin@withdrive.com","password")
        cy.visit('/')
        /* ==== Generated with Cypress Studio ==== */
        cy.get('[href="/view-users"]').click();
        cy.contains('tester@withdrive.com').type('{rightarrow}{rightarrow}{rightarrow}{rightarrow}');
        cy.focused().click();
        cy.wait(500)
        cy.contains('tester@withdrive.com').type('{rightarrow}{rightarrow}{rightarrow}{rightarrow}');
        cy.focused().click();
        /* ==== End Cypress Studio ==== */
        cy.wait(1500)
        cy.visit('/sign-out')
    })
})