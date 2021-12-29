package nl.fontys.withdrive.controller;

import com.sun.mail.imap.Utility;
import net.bytebuddy.utility.RandomString;
import nl.fontys.withdrive.dto.user.UserDTO;
import nl.fontys.withdrive.entity.User;
import nl.fontys.withdrive.interfaces.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class ForgotPasswordController {
    private final IUserService users;

    @Autowired
    public ForgotPasswordController(IUserService users){
        this.users = users;
    }

    @PostMapping("/forgot_password")
    public void processForgotPassword(@Param(value = "email") String email) {
        String token = RandomString.make(35);

        try {
            users.updateResetPasswordToken(token, email, "http://localhost:3000");

        } catch (Exception ex) {
            System.out.println("Exception!");
        }
    }

    @GetMapping("/reset_password/{token}")
    public ResponseEntity showResetPasswordForm(@PathVariable(value = "token") String token) {

        UserDTO customer = users.getByResetPasswordToken(token);

        if(customer != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reset_password")
    public void processResetPassword(@Param(value = "password") String password,@Param(value = "token") String token) {

        UserDTO user = users.getByResetPasswordToken(token);

        if (user != null) {
            users.updatePassword(user.getEmail(), password);
        }
    }
}
