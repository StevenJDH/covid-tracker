/*
 * This file is part of Covid Tracker <https://github.com/StevenJDH/covid-tracker>.
 * Copyright (C) 2021-2022 Steven Jenkins De Haro.
 *
 * Covid Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Covid Tracker is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Covid Tracker.  If not, see <http://www.gnu.org/licenses/>.
 */

package stevenjdh.covidtracker.controllers;

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
import stevenjdh.covidtracker.models.LocationStat;
import stevenjdh.covidtracker.services.CovidDataService;

@WebMvcTest(HomeController.class)
class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CovidDataService covidDataService;

    @Autowired
    private HomeController homeController;

    @Test
    @DisplayName("Should initialize controller when context loads.")
    void Should_InitializeControler_When_ContextLoads() {
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
        
        // A simple check for SonarCloud since the above is not seen as an assertion.
        assertThat(stat.getDiffFromPrevDay()).isEqualTo(10);
    }
}