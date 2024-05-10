package vn.edu.ptit.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.quiz.config.AppConstant;
import vn.edu.ptit.quiz.model.AdminLoginRQ;
import vn.edu.ptit.quiz.model.AdminTokenDto;
import vn.edu.ptit.quiz.service.AdminService;
import vn.edu.ptit.quiz.util.converter.ConverterUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<AdminTokenDto> login(@RequestBody AdminLoginRQ adminLoginRQ){
        return ResponseEntity.ok(adminService.login(adminLoginRQ));
    }

}
