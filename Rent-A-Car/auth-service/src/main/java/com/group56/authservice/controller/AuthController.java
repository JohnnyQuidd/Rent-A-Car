package com.group56.authservice.controller;

import com.group56.authservice.DTO.AdminDTO;
import com.group56.authservice.DTO.AgentDTO;
import com.group56.authservice.DTO.LoginDTO;
import com.group56.authservice.DTO.UserDTO;
import com.group56.authservice.enumeration.Role;
import com.group56.authservice.service.AdminService;
import com.group56.authservice.service.AgentService;
import com.group56.authservice.service.UserService;
import com.group56.authservice.utility.IdentityCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth-service")
public class AuthController {
    private UserService userService;
    private AdminService adminService;
    private AgentService agentService;
    private IdentityCheck identityCheck;
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(UserService userService, AdminService adminService, AgentService agentService, IdentityCheck identityCheck) {
        this.userService = userService;
        this.adminService = adminService;
        this.agentService = agentService;
        this.identityCheck = identityCheck;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        Role role = identityCheck.getRoleForUsername(loginDTO.getUsername());
        if(role != null) {
            switch (role){
                case USER: return userService.logInUser(loginDTO, session);
                case ADMIN: return adminService.logInAdmin(loginDTO, session);
                case AGENT: return agentService.loginAgent(loginDTO, session);
                default: return new ResponseEntity<>("Invalid credentials!", HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/register/user")
    public ResponseEntity<?> registerNewUser(@RequestBody UserDTO userDTO) {
        return  userService.registerNewUser(userDTO);
    }

    @PostMapping(value = "/register/admin")
    public ResponseEntity<?> registerNewAdmin(@RequestBody AdminDTO adminDTO) {
        return adminService.registerNewAdmin(adminDTO);
    }

    @PostMapping(value = "/register/agent")
    public ResponseEntity<?> registerNewAgent(@RequestBody AgentDTO agentDTO) {
        return agentService.registerAgent(agentDTO);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        try {
            session.invalidate();
            logger.info("Session is invalidated");
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Couldn't invalidate session");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user/rent-service")
    public ResponseEntity<?> getNewUsers() {
        return userService.getNewUsersForRentingService();
    }

}
