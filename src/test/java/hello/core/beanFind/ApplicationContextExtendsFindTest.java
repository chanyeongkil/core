package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회, 자식이 둘 이상, 부모타입으로 조회 >> NoUniqueBeanDefinitionException 발생")
    public void findByBeanByParentTypeDuplicate(){
        //ac.getBean(DiscountPolicy.class)
        assertThrows(NoUniqueBeanDefinitionException.class, ()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상, 특정 Bean 이름 지정 >> 정상 조회")
    public void findByBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상, 하위 특정 Bean 타입 지정 >> 정상 조회")
    public void findByBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상, 부모 타입으로 모두 조회 >> 정상 조회")
    public void findByBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key  = " + key + ", value = "+ beansOfType.get(key));
        }
    }

    /**
     * Object 객체는 Java의 모든 클래스의 부모 클래스 이기 때문에 Object 타입으로 출력 시
     * Spring Container의 모든 Bean 이 출력 됨
     * */
    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상, 부모 타입으로 모두 조회, Object Type>> 정상 조회")
    public void findByBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key  = " + key + ", value = "+ beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig{
        //DiscountPolicy 호출 시
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }


}
