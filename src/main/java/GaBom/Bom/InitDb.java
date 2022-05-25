package GaBom.Bom;

import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.entity.Travel;
import GaBom.Bom.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

//@Component
@RequiredArgsConstructor
public class InitDb {
//
//    private final InitService initService;
//
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//
//        private final EntityManager em;
//        private final PasswordEncoder passwordEncoder;
//
//        public void dbInit1(){
//            User sg = User.builder()
//                    .nickName("SSUSG")
//                    .emailAuth(true)
//                    .userId("qwe123")
//                    .userPw(passwordEncoder.encode("123"))
//                    .follwerNum(10)
//                    .followingNum(15)
//                    .build();
//
//            User ex1 = User.builder()
//                    .nickName("ex1")
//                    .emailAuth(true)
//                    .userId("ex1")
//                    .userPw(passwordEncoder.encode("123"))
//                    .follwerNum(20)
//                    .followingNum(25)
//                    .build();
//
//            User ex2 = User.builder()
//                    .nickName("ex2")
//                    .emailAuth(true)
//                    .userId("ex2")
//                    .userPw(passwordEncoder.encode("123"))
//                    .follwerNum(40)
//                    .followingNum(45)
//                    .build();
//
//            User ex3 = User.builder()
//                    .nickName("ex3")
//                    .emailAuth(true)
//                    .userId("ex3")
//                    .userPw(passwordEncoder.encode("123"))
//                    .follwerNum(100)
//                    .followingNum(105)
//                    .build();
//
//            User so = User.builder()
//                    .nickName("최강씨2")
//                    .emailAuth(true)
//                    .userId("rkskek")
//                    .userPw(passwordEncoder.encode("1234567890"))
//                    .follwerNum(100)
//                    .followingNum(105)
//                    .build();
//
//            User s3o = User.builder()
//                    .nickName("최강씨3")
//                    .emailAuth(true)
//                    .userId("rnsnen")
//                    .userPw(passwordEncoder.encode("1234567890"))
//                    .follwerNum(100)
//                    .followingNum(105)
//                    .build();
//
//            em.persist(sg);
//            em.persist(ex1);
//            em.persist(ex2);
//            em.persist(ex3);
//            em.persist(so);
//            em.persist(s3o);
//
//        }
//    }
}
