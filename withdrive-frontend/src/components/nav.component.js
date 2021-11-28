import { Navbar, Nav, Container } from 'react-bootstrap';
import withdriveLogoSmall from "../images/withdrive-w.png";
import AuthService from "../services/AuthService";
import { Fragment } from 'react';

var isLoggedIn = 0;

if (AuthService.getCurrentUser() !== null && AuthService.getCurrentUser().roles.includes("ROLE_ADMIN")) {
    isLoggedIn = 1;
} else if (AuthService.getCurrentUser() !== null) {
    isLoggedIn = 2;
}

const Navigation = () => {
    let menu = '';

    if (isLoggedIn === 0) {
        menu = (
            <Fragment>
                <Nav.Link href='/sign-in'>Login</Nav.Link>
                <Nav.Link href='/sign-up'>Sign Up</Nav.Link>
            </Fragment>
        )
    } else if (isLoggedIn === 1) {
        menu = (
            <Fragment>
                <Nav.Link href='/sign-in'>View Users</Nav.Link>
                <Nav.Link href='/sign-up'>View Complaints</Nav.Link>
            </Fragment>
        )
    } else {
        menu = (
            <Fragment>
                <Nav.Link href='/create-trip'>Create Ride</Nav.Link>
                <Nav.Link href='/my-trips'>My Rides</Nav.Link>
                <Nav.Link href='/sign-out'>Sign Out</Nav.Link>
            </Fragment>
        )
    }

    return (
        <>
            <Navbar collapseOnSelect fixed="top" expand="sm" bg="light" variant="light">
                <Container>
                    <Navbar.Toggle aria-controls='responsive-navbar-nav' />
                    <Navbar.Collapse id='responsive-navbar-nav'>
                        <Nav className="me-auto my-2 my-lg-0">
                            <Navbar.Brand href='/'>
                                <img src={withdriveLogoSmall} width="33" height="33" className="d-inline-block align-top"></img>
                            </Navbar.Brand>
                            <Nav.Link href='/'>withdrive</Nav.Link>
                            <Nav.Link href='/view-trips'>View Rides</Nav.Link>
                        </Nav>
                        <Nav>
                            {menu}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    );
}

export default Navigation;