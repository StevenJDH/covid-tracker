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
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import stevenjdh.covidtracker.models.LocationStat;

@Service
public class CovidDataService {

    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStat> locationStats = new ArrayList<>();
    private final HttpClient httpClient;
    
    public CovidDataService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
    
    public List<LocationStat> getLocationStats() {
        return locationStats;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 0/1 1/1 * ?") // Runs every hour.
    private void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStat> newLocationStats = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        var csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
        for (CSVRecord entry : records) {
            var locationStat = new LocationStat();  
            // Use country as default value if no Province/State specified.
            locationStat.setState(entry.get(entry.get("Province/State").equals("") ? "Country/Region" : "Province/State"));
            locationStat.setCountry(entry.get("Country/Region"));
            int latestCases = Integer.parseInt(entry.get(entry.size() - 1));
            int prevDayCases = Integer.parseInt(entry.get(entry.size() - 2));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
            newLocationStats.add(locationStat);
        }
        
        locationStats = newLocationStats;
    }
}