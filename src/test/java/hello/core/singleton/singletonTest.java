package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class singletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1.조회: 호출할 때 마다 객체를 생성 하나?
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        // 호출 할때마다 객체가 새로 생성 됨
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    public static void main(String[] args) {
        /** SingletonService가 private로 선언되어 있기 떄문에 외부에서 해당 객체를 생성 불가능 **/
        //SingletonService ss1 = new SingletonService();
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 같은 객체 인스턴스가 반환 됨
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // singletonService1==singletonService2
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
}