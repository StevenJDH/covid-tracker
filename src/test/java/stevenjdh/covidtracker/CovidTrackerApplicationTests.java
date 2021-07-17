package stevenjdh.covidtracker;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import stevenjdh.covidtracker.controllers.HomeController;
import stevenjdh.covidtracker.models.LocationStat;
import stevenjdh.covidtracker.services.CovidDataService;

@WebMvcTest(HomeController.class)
class CovidTrackerApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CovidDataService covidDataService;

    @Autowired
    private HomeController homeController;

    @Test
    @DisplayName("Should initialize controller when context loads.")
    void Should_InitializeControler_When_ContextLoads() throws Exception {
        assertThat(homeController).isNotNull();
    }

    @Test
    @DisplayName("Should display 200 total cases for test data.")
    void Should_Display200TotalCases_ForTestData() throws Exception {
        String expectedSubString = "<h2 class=\"display-4\">200</h2>";
        var stat = new LocationStat();
        
        stat.setState("My Province");
        stat.setCountry("My Country");
        stat.setLatestTotalCases(100);
        stat.setDiffFromPrevDay(10);

        when(covidDataService.getLocationStats())
                .thenReturn(List.of(stat, stat)); // Doubling the data.

        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedSubString)));
    }
}