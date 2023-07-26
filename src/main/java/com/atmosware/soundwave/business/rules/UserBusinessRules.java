package com.atmosware.soundwave.business.rules;

import com.atmosware.soundwave.common.constants.ExceptionTypes;
import com.atmosware.soundwave.core.exceptions.BusinessException;
import com.atmosware.soundwave.core.exceptions.DatabaseException;
import com.atmosware.soundwave.entities.User;
import com.atmosware.soundwave.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository repository;


    public void checkIfAnyUserExists(List<User> users) {
        if (users.isEmpty()){
            log.error(ExceptionTypes.Exception.Business+": Kullanıcılar bulunamadı!");
            throw new BusinessException("Kullanıcılar bulunamadı!");
        }
    }

    public void checkIfUserExists(UUID id) {
        if (!repository.existsById(id)){
            log.error(ExceptionTypes.Exception.Business+": Kullanıcı bulunamadı!");
            throw new BusinessException("Kullanıcı bulunamadı!");
        }
    }

    public boolean checkPassword(String username, String password) {
        User user;
        try{
             user = repository.findByName(username);
        }catch (Exception e){
            log.error(ExceptionTypes.Exception.Database+": "+e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        if(!user.getPassword().equals(password)){
            log.error(ExceptionTypes.Exception.Business+": "+"Giriş yapılamadı. Giriş bilgilerinizi kontrol ediniz!");
            throw new BusinessException("Giriş yapılamadı. Giriş bilgilerinizi kontrol ediniz!");
        }
        return true;
    }
}
