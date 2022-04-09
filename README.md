# COVID-19 Tracker

[![build](https://github.com/StevenJDH/covid-tracker/actions/workflows/maven-sonar-container-workflow.yml/badge.svg?branch=main)](https://github.com/StevenJDH/covid-tracker/actions/workflows/maven-sonar-container-workflow.yml)
![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/StevenJDH/covid-tracker?include_prereleases)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/9c6b7925766c4c9ea36480a2f3e2d315)](https://www.codacy.com/gh/StevenJDH/covid-tracker/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=StevenJDH/covid-tracker&amp;utm_campaign=Badge_Grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_covid-tracker&metric=alert_status)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_covid-tracker&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_covid-tracker&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_covid-tracker&metric=sqale_index)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
[![Sonar Violations (long format)](https://img.shields.io/sonar/violations/StevenJDH_covid-tracker?format=long&server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_covid-tracker&metric=security_rating)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_covid-tracker&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_covid-tracker&metric=coverage)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=StevenJDH_covid-tracker&metric=ncloc)](https://sonarcloud.io/dashboard?id=StevenJDH_covid-tracker)
![Maintenance](https://img.shields.io/maintenance/yes/2022)
![GitHub](https://img.shields.io/github/license/StevenJDH/covid-tracker)

COVID-19 Tracker is a microservice that tracks the number of infected cases with the COVID-19 virus. Coronavirus disease (COVID-19) is the name given to a newly discovered strain of Coronavirus, called SARS-CoV-2, which was officially declared a Pandemic on 11 March 2020. Statistical data associated with COVID-19 changes rapidly, and there are various sources aggregating this data that update at different times, so do expect some variation in the data from other sites and sources. For more information regarding COVID-19, see the WHO's [COVID-19 Q&A](https://www.who.int/news-room/q-a-detail/coronavirus-disease-covid-19) page.

[![Buy me a coffee](https://img.shields.io/static/v1?label=Buy%20me%20a&message=coffee&color=important&style=flat&logo=buy-me-a-coffee&logoColor=white)](https://www.buymeacoffee.com/stevenjdh)

Releases: [https://github.com/StevenJDH/covid-tracker/releases](https://github.com/StevenJDH/covid-tracker/releases)

## Features
* Global COVID-19 statistics provided by Johns Hopkins University CSSE.
* Total infected cases along with total new infections since previous day.
* Dynamic filtering of geographic data.
* Scheduled cache control of source data.

## Prerequisites
* Docker or Kubernetes for running the container.

## Endpoints
Below are the URL references used in the app.

    GET :8080/
    GET :8081/actuator/info
    GET :8081/actuator/health
    GET :8081/actuator/health/liveness
    GET :8081/actuator/health/readiness

## Contributing
Thanks for your interest in contributing! There are many ways to contribute to this project. Get started [here](https://github.com/StevenJDH/.github/blob/main/docs/CONTRIBUTING.md).

## Do you have any questions?
Many commonly asked questions are answered in the FAQ:
[https://github.com/StevenJDH/covid-tracker/wiki/FAQ](https://github.com/StevenJDH/covid-tracker/wiki/FAQ)

## Want to show your support?

|Method       | Address                                                                                                    |
|------------:|:-----------------------------------------------------------------------------------------------------------|
|PayPal:      | [https://www.paypal.me/stevenjdh](https://www.paypal.me/stevenjdh "Steven's Paypal Page")                  |
|Bitcoin:     | 3GyeQvN6imXEHVcdwrZwKHLZNGdnXeDfw2                                                                         |
|Litecoin:    | MAJtR4ccdyUQtiiBpg9PwF2AZ6Xbk5ioLm                                                                         |
|Ethereum:    | 0xa62b53c1d49f9C481e20E5675fbffDab2Fcda82E                                                                 |
|Dash:        | Xw5bDL93fFNHe9FAGHV4hjoGfDpfwsqAAj                                                                         |
|Zcash:       | t1a2Kr3jFv8WksgPBcMZFwiYM8Hn5QCMAs5                                                                        |
|PIVX:        | DQq2qeny1TveZDcZFWwQVGdKchFGtzeieU                                                                         |
|Ripple:      | rLHzPsX6oXkzU2qL12kHCH8G8cnZv1rBJh<br />Destination Tag: 2357564055                                        |
|Monero:      | 4GdoN7NCTi8a5gZug7PrwZNKjvHFmKeV11L6pNJPgj5QNEHsN6eeX3D<br />&#8618;aAQFwZ1ufD4LYCZKArktt113W7QjWvQ7CWDXrwM8yCGgEdhV3Wt|


// Steven Jenkins De Haro ("StevenJDH" on GitHub)
