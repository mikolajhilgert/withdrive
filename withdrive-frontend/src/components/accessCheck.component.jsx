import AuthService from "../services/AuthService";

function notFound() {
    window.history.pushState({}, '', "/not-found");
    window.location.reload();
}
function out() {
    window.history.pushState({}, '', "/sign-out");
    window.location.reload();
}


function IsAuthenticated(userType) {
    if (AuthService.getCurrentUser() !== null) {
        switch(userType){
            case "admin":
                AuthService.checkTokenAdmin().then(response => {
                    if (response === false) {
                        notFound();
                    }
                })
            default:
                AuthService.checkToken().then(response => {
                    if (response === false) {
                        out();
                    }
                })
        }
    }else{
        notFound();
    }
}
export default IsAuthenticated;