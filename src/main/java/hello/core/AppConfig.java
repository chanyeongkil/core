package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * @Bean 어노테이션작성 시 스프링 컨테이너에 등록 됨
     * 컨테이너에 등록 시 메서드 이름으로 등록 됨
    * */
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { return new MemoryMemberRepository(); }

    @Bean
    public OrderService orderService(){ return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy()); }

    @Bean
    public DiscountPolicy discountPolicy(){ return new FixDiscountPolicy(); }
}
