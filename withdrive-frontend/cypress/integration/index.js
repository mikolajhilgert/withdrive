describe('render_index', () => {
    it("go_index", () => {
        cy.login("driver@withdrive.com","password")
        cy.visit('/')
        /* ==== Generated with Cypress Studio ==== */
        cy.get('[href="/view-trips"]').click();
        /* ==== End Cypress Studio ==== */
    })
})

