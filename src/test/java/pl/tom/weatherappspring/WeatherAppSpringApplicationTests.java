package pl.tom.weatherappspring;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tom.weatherappspring.controller.HomeController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class WeatherAppSpringApplicationTests {

    @Autowired
    HomeController homeController;

    @Test
    void contextLoads() {
        assertThat(homeController).isNotNull();
    }

}
