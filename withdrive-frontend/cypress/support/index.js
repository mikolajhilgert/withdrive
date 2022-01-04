// ***********************************************************
// This example support/index.js is processed and
// loaded automatically before your test files.
//
// This is a great place to put global configuration and
// behavior that modifies Cypress.
//
// You can change the location of this file or turn off
// automatically serving support files with the
// 'supportFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/configuration
// ***********************************************************

// Import commands.js using ES2015 syntax:
import './commands'
// ignore uncaught exceptions
Cypress.on('uncaught:exception', (err, runnable) => {
  // returning false here prevents Cypress from
  // failing the test
  cy.log('Uncaught exception... continuing...')
  return false
})


Cypress.on('fail', (error, runnable) => {
  console.log(error)

  // we now have access to the err instance
  // and the mocha runnable this failed on

  throw error // throw error to have test still fail
})

Cypress.on('window:before:load', (win) => {
  Cypress.log({
      name: 'console.log',
      message: 'wrap on console.log',
  });

  // pass through cypress log so we can see log inside command execution order
  win.console.log = (...args) => {
      Cypress.log({
          name: 'console.log',
          message: args,
      });
  };
});

Cypress.on('log:added', (options) => {
  if (options.instrument === 'command') {
      // eslint-disable-next-line no-console
      console.log(
          `${(options.displayName || options.name || '').toUpperCase()} ${
              options.message
          }`,
      );
  }
});
  
  // patch Cypress top.onerro
// Alternatively you can use CommonJS syntax:
// require('./commands')
