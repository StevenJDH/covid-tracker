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

package stevenjdh.covidtracker;

import java.net.http.HttpClient;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AppConfig.class)
class AppConfigTests {

    @Autowired
    private AppConfig appConfig;
    
    @Autowired
    private HttpClient httpClient;
    
    @Test
    @DisplayName("Should reference same object when instance is autowired.")
    void Should_ReferenceSameObject_When_InstanceIsAutowired() {
        assertThat(appConfig.httpClient()).isNotNull();
        assertThat(httpClient).isNotNull();
        assertThat(appConfig.httpClient()).isSameAs(httpClient);
    }
}