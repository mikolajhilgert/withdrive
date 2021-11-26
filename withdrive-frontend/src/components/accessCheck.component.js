import { useHistory } from "react-router";
function IsAuthenticated(authentication) {
    const History =  useHistory();
    if(authentication == null){
        History.push("/not-found");
        window.location.reload();
    }
}
export default IsAuthenticated;