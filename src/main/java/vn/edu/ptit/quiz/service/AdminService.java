package vn.edu.ptit.quiz.service;

import vn.edu.ptit.quiz.model.AdminLoginRQ;
import vn.edu.ptit.quiz.model.AdminTokenDto;

public interface AdminService {
    AdminTokenDto login(AdminLoginRQ adminLoginRQ);

    Boolean authenticate(String token);
}
