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
    it("send_alert", () => {
        cy.login("admin@withdrive.com","password")
        cy.visit('/')
        cy.get('[href="/send-alerts"]').click();
        cy.get('[data-cy=text]').type('Test Test Test Test Test Test Test')
        cy.get('.btn').click();
        cy.wait(4000)
        cy.get('[href="/sign-out"]').click();
        /* ==== End Cypress Studio ==== */
    })
})