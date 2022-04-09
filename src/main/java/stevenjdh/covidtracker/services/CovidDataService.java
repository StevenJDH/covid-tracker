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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import stevenjdh.covidtracker.models.LocationStat;

@Service
public class CovidDataService {

    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private final HttpClient httpClient;
    private static final Logger LOG = LoggerFactory.getLogger(CovidDataService.class);
    
    public CovidDataService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
    
    @Cacheable(value = "stats", key = "#root.method.name")
    public List<LocationStat> getLocationStats() {
        LOG.info("Fetching the latest COVID-19 data...");
        
        try {
            return fetchVirusData();
        } catch (IOException | InterruptedException ex) {
            LOG.error("Error: {}", ex.getMessage());
            Thread.currentThread().interrupt(); // Restores interrupted state.
            return new ArrayList<>();
        }
    }

    private List<LocationStat> fetchVirusData() throws IOException, InterruptedException{
        List<LocationStat> newLocationStats = new ArrayList<>();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        var httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        try (var csvBodyReader = new StringReader(httpResponse.body())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(csvBodyReader);
            
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
        }
        
        LOG.info("Latest COVID-19 data now cached.");
        return newLocationStats;
    }
    
    @Scheduled(cron = "0 0 0/1 1/1 * ?") // Runs every hour.
    @CacheEvict(cacheNames = {"stats"}, allEntries = true)
    public void evictCache() {
        LOG.info("Evicting cached COVID-19 data.");
    }
}