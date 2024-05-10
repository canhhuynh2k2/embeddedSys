package vn.edu.ptit.quiz.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.ptit.quiz.config.AppConstant;
import vn.edu.ptit.quiz.model.AdminLoginRQ;
import vn.edu.ptit.quiz.model.AdminTokenDto;
import vn.edu.ptit.quiz.service.AdminService;
import vn.edu.ptit.quiz.util.converter.ConverterUtil;

@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public AdminTokenDto login(AdminLoginRQ adminLoginRQ) {
        if(adminLoginRQ.getUsername().equals(AppConstant.ADMIN.ADMIN_USERNAME)
                && adminLoginRQ.getPassword().equals(AppConstant.ADMIN.ADMIN_PASSWORD)) {
            AdminTokenDto adminTokenDto = ConverterUtil.mappingToObject(adminLoginRQ, AdminTokenDto.class);
            adminTokenDto.setToken(AppConstant.ADMIN.ADMIN_TOKEN);
            return adminTokenDto;
        }
        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public Boolean authenticate(String token) {
        if(token.equals(AppConstant.ADMIN.ADMIN_TOKEN)) {
            return true;
        }
        return false;
    }
}
