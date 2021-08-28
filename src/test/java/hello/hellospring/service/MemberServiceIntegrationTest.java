package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //테스트어노테이션 적용 후 선언하게 되면 실행할 때 트랜잭션 생성하고 테스트가 끝나면 롤백을 통해 DB 반영하지 않게 동작해줌
//@Commit //커밋시켜줌
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello12331");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findMember(saveId).get();
        assertThat(member.getName()).isEqualTo((findMember.getName()));
    }
    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("hello2");
        Member member2 = new Member();
        member2.setName("hello3");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findMember() {
    }
}
