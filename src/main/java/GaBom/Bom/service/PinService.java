package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.entity.*;
import GaBom.Bom.repository.PinRepository;
import GaBom.Bom.repository.TravelRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PinService {
    private final TravelRepository travelRepository;
    private final UserRepository userRepository;
    private final PinRepository pinRepository;

    @Transactional
    //파라미터 : 유저 id, travelDto
    //유저 id ( 로그인 세션으로 변경 예정)
    public boolean save(String username, Travel travel){
        User user = userRepository.findByUserName(username).orElseThrow(CUserNotFoundException::new);
        //만약 userName이 없으면? -> 에러처리. 근데 로그인세션으로 할거니까 그때 보고?하기.
        user.add(travel);
        travelRepository.save(travel);
        return true;
    }

}