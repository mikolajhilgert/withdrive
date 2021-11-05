import { Navbar, Nav, Container,Button } from 'react-bootstrap';
import withdriveLogoSmall from "../images/withdrive-w.png";


const Navigation = () =>{
    return(
        <>
            <Navbar collapseOnSelect fixed="top" expand="sm" bg="light" variant="light">
                <Container>
                    <Navbar.Toggle aria-controls='responsive-navbar-nav' /> 
                        <Navbar.Collapse id='responsive-navbar-nav'>
                            <Nav>
                                <Navbar.Brand href="/">
                                    <img src={withdriveLogoSmall} width="30" height="30" className="d-inline-block align-top"></img>
                                </Navbar.Brand>
                                <Nav.Link href='/'>withdrive</Nav.Link>
                                <Nav.Link href='/sign-in'>Login</Nav.Link>
                                <Nav.Link href='/sign-up'>Sign Up</Nav.Link>
                                {/* <Nav.Link href='/api-test'>Api Test</Nav.Link> */}
                                <Nav.Link href='/view-trips'>View Rides</Nav.Link>
                                {/* <Nav.Link href='/view-trips'><Button size="sm">Publish trip</Button></Nav.Link> */}
                            </Nav>
                            
                    </Navbar.Collapse>  
                </Container>
            </Navbar>
        </>
    );
}

export default Navigation;