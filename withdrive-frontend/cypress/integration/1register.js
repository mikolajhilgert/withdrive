describe('register', () => {
    it("register_passenger", () => {
        /* ==== Generated with Cypress Studio ==== */
        cy.visit('http://localhost:3000');
        cy.get('[href="/sign-up"]').click();
        cy.get(':nth-child(3) > .form-control').clear();
        cy.get(':nth-child(3) > .form-control').type('John');
        cy.get(':nth-child(4) > .form-control').clear();
        cy.get(':nth-child(4) > .form-control').type('Doe');
        cy.get(':nth-child(5) > .form-control').clear();
        cy.get(':nth-child(5) > .form-control').type('tester@withdrive.com');
        cy.get('.auth-wrapper').select('male');
        cy.get(':nth-child(8) > .form-control').clear();
        cy.get(':nth-child(8) > .form-control').type('+420606058797');
        cy.get(':nth-child(9) > .form-control').clear();
        cy.get(':nth-child(9) > .form-control').type('password');
        cy.get(':nth-child(10) > .form-control').clear();
        cy.get(':nth-child(10) > .form-control').type('password');
        cy.get('.btn').click();
        cy.wait(2000);
        /* ==== End Cypress Studio ==== */
    })
    it("register_driver", () => {
        /* ==== Generated with Cypress Studio ==== */
        cy.visit('http://localhost:3000');
        cy.get('[href="/sign-up"]').click();
        cy.get(':nth-child(3) > .form-control').clear();
        cy.get(':nth-child(3) > .form-control').type('John');
        cy.get(':nth-child(4) > .form-control').clear();
        cy.get(':nth-child(4) > .form-control').type('Doe');
        cy.get(':nth-child(5) > .form-control').clear();
        cy.get(':nth-child(5) > .form-control').type('drivertester@withdrive.com');
        cy.get('.auth-wrapper').select('male');
        cy.get(':nth-child(8) > .form-control').clear();
        cy.get(':nth-child(8) > .form-control').type('+420606058797');
        cy.get(':nth-child(9) > .form-control').clear();
        cy.get(':nth-child(9) > .form-control').type('password');
        cy.get(':nth-child(10) > .form-control').clear();
        cy.get(':nth-child(10) > .form-control').type('password');
        cy.get('.btn').click();
        cy.wait(2000);
        /* ==== End Cypress Studio ==== */
    })
})

