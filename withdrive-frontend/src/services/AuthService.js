    import axios from "axios";

    const API_URL = "http://localhost:8080/user/login";

    class AuthService {
    login(username, password) {
        var reqBody = "username="+username+"&password="+password+"&grant_type=password";
        return fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: reqBody
        })
    
    }

    logout() {
        localStorage.removeItem("user");
    }
}

    export default new AuthService();