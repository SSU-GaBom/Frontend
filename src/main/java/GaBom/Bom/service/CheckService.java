package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CNickNameAlreadyExistsException;
import GaBom.Bom.advice.exception.CUserAlreadyExistsException;
import GaBom.Bom.advice.exception.CUserIdAlreadyExistsException;
import GaBom.Bom.dto.SignUpUserDto;
import GaBom.Bom.dto.UserDto;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CheckService {

    private final UserRepository userRepository;

    @Transactional
    public void check(SignUpUserDto signUpUserDto){
    if(!checkUserNameAndEmail(signUpUserDto.getUserName(), signUpUserDto.getEmail()))
        throw new CUserAlreadyExistsException();
    if(!checkId(signUpUserDto.getUserId()))
        throw new CUserIdAlreadyExistsException();
    if(!checkNickName(signUpUserDto.getNickName()))
        throw new CNickNameAlreadyExistsException();
}

    @Transactional
    public boolean checkId(String userId) {
        //아이디가 존재하면 생성 불가
        if (userRepository.existsByUserId(userId))
            return false;
        return true;
    }

    //이메일이 존재하는지 확인정도의 서비스
    @Transactional
    public Boolean checkUserNameAndEmail(String userName, String email) {
        if (userRepository.existsByUserNameAndEmail(userName, email))
            return false;
        return true;
    }

    @Transactional
    public Boolean checkUserIdAndEmail(String userId, String email) {
        if (userRepository.existsByUserIdAndEmail(userId, email))
            return false;
        return true;
    }

    @Transactional
    public boolean checkNickName(String NickName) {
        //아이디가 존재하면 생성 불가
        if (userRepository.existsByNickName(NickName))
            return false;
        return true;
    }
}
