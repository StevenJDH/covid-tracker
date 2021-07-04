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

package stevenjdh.covidtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import stevenjdh.covidtracker.services.CovidDataService;

@Controller
public class HomeController {

    private final CovidDataService covidDataService;

    public HomeController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }
    
    @GetMapping("/")
    public String home(Model model) {
        var locationStats = covidDataService.getLocationStats();
        int totalReportedCases = locationStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = locationStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        
        model.addAttribute("locationStats", locationStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}