import AuthService from "../services/AuthService";
function IsAuthenticated() {
    if(AuthService.getCurrentUser() !== null){
        if(AuthService.getCurrentUser().roles.includes("ROLE_ADMIN")){
            AuthService.checkTokenAdmin().then(response=>{
                if(response===false){
                    window.history.pushState({}, '', "/not-found");
                    window.location.reload();
                }
            })
        }else{
            AuthService.checkToken().then(response=>{
                if(response===false){
                    window.history.pushState({}, '', "/sign-out");
                    window.location.reload();
                }
            })
        }
    }else{
        window.history.pushState({}, '', "/not-found");
        window.location.reload();
    }
}
export default IsAuthenticated;