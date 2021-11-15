package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // Thread A : 사용자 A 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // Thread B : 사용자 B 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // 이렇게 바로 지역 변수로 사용하도록...

//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
//
//        // 큰일 난다... 상글콘 패턴은 상태를 유지 하지 않도록 설계해야 함..
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
