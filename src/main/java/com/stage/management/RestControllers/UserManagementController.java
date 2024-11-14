package com.stage.management.RestControllers;

import com.stage.management.DAO.Entities.OurUsers;
import com.stage.management.DTO.ReqRes;
import com.stage.management.Services.security.EmailService;
import com.stage.management.Services.security.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserManagementController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg) {
        ReqRes response = usersManagementService.register(reg);

        if (response.getStatusCode() == 200) {  // Assuming 200 means success
            String subject = "Welcome to Our Platform";
            String text = "Dear " + reg.getUsername() + ",\n\nYour account has been created successfully. " +
                    "Here are your credentials:\n\n" +
                    "Email: " + reg.getEmail() + "\n" +
                    "Password: " + reg.getPassword() + "\n\n" +
                    "Please keep this information safe.\n\nBest Regards,\nYour Company";
            emailService.sendSimpleMessage(reg.getEmail(), subject, text);
        }

        return ResponseEntity.ok(response);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }
    @PostMapping("/auth/forgot-password")
    public ResponseEntity<ReqRes> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        ReqRes response = usersManagementService.forgotPassword(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/auth/check-email-exists")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        boolean exists = usersManagementService.checkEmailExists(email);
        return ResponseEntity.ok(exists);
    }
}