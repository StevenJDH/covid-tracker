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

$(document).ready(function() {
    var year = $("span#copyright-year").data().year;
    var currentYear = new Date().getFullYear();
    $("#copyright-year").text(year < currentYear ? year + "-" + currentYear : year);
    
    var alterRibbon = function() {
        if (document.body.clientWidth < 800) {
            $('.github-fork-ribbon').addClass('right-bottom');
        } else {
            $('.github-fork-ribbon').removeClass('right-bottom');
        };
    };
    $(window).resize(function(){
        alterRibbon();
    });
    // Fire when page first loads.
    alterRibbon();
});