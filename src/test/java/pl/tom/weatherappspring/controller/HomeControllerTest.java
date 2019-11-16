package pl.tom.weatherappspring.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;


@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock
    HomeController homeController;

    @Before
    public void init(){
        given(homeController.weatherApp()).willReturn("home");
    }

    @Test
    public void shouldReturnViewName() {
        String viewName = homeController.weatherApp();
        Assert.assertEquals(viewName, "home");
    }

}