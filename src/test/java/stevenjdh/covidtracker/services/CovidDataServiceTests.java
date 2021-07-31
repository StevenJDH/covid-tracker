/*
 * This file is part of Covid Tracker <https://github.com/StevenJDH/covid-tracker>.
 * Copyright (C) 2021 Steven Jenkins De Haro.
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

package stevenjdh.covidtracker.services;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cache.CacheManager;
import stevenjdh.covidtracker.AppConfig;
import stevenjdh.covidtracker.models.LocationStat;

@WebMvcTest({CovidDataService.class, AppConfig.class})
public class CovidDataServiceTests {

    @InjectMocks
    private CovidDataService covidDataService;

    @Mock
    private HttpClient mockClient;
    
    @Mock
    private HttpResponse<String> mockResponse;
    
    @Autowired
    private CacheManager cacheManager;
    
    @Test
    @DisplayName("Should correctly parse statistics for test data.")
    void Should_CorrectlyParseStatistics_ForTestData() throws IOException, InterruptedException {        
        Path testData = Path.of("target", "test-classes", "jhu-test-data.csv");

        when(mockResponse.statusCode())
                .thenReturn(200);
        when(mockResponse.body())
                .thenReturn(Files.readString(testData));
        when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);
        
        var locationStats = covidDataService.getLocationStats();
        
        assertEquals(16, locationStats.size());
        assertEquals(5_216_075, locationStats.stream()
                .mapToInt(LocationStat::getLatestTotalCases)
                .sum());
        assertEquals(21_658, locationStats.stream()
                .mapToInt(LocationStat::getDiffFromPrevDay)
                .sum());
        
        // Ensures caching is off for tests.
        assertThat(cacheManager.getCacheNames()).hasSize(0);
    }
}