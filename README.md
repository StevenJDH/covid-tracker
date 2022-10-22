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
* Docker/Rancher Desktop or Kubernetes for running the container.

## Container registries
COVID-19 Tracker container images are currently hosted on the following platforms:

* [Amazon Elastic Container Registry (ECR)](https://gallery.ecr.aws/stevenjdh/covid-tracker)
* [GitHub Container Registry](https://github.com/users/StevenJDH/packages/container/package/covid-tracker)
* [Docker Hub](https://hub.docker.com/r/stevenjdh/covid-tracker)

For production use cases, it is not recommended to pull an image with the `:latest` tag, or no tag since these are equivalent.

## Helm chart
COVID-19 Tracker can be optionally deployed to a Kubernetes cluster using the [COVID-19 Tracker Helm Chart](https://github.com/StevenJDH/helm-charts/tree/main/charts/covid-tracker) that is managed in a separate repository. All of the features described below and more are supported by this chart.

## Usage
To run the application locally, or access it without an Ingress resource, use one of the following approaches below.

**Docker/Rancher Desktop:**

```bash
docker run --name covid-tracker -p 127.0.0.1:80:8080/tcp -d stevenjdh/covid-tracker:preview
# OR
nerdctl run --name covid-tracker -p 127.0.0.1:80:8080/tcp -d stevenjdh/covid-tracker:preview
```

**Kubernetes:**

```bash
kubectl run covid-tracker --image=stevenjdh/covid-tracker:preview --port 8080
kubectl expose po covid-tracker --port 80 --target-port=8080 --name=covid-tracker
kubectl port-forward svc/covid-tracker 80:80
```

Once the application is running, the UI can be accessed via http://localhost.

## Endpoints
Below are the URL references used in the app.

    GET :8080/
    GET :8081/actuator/info
    GET :8081/actuator/health
    GET :8081/actuator/health/liveness
    GET :8081/actuator/health/readiness

## Schedule for cached statistics
Cached statistics are updated every hour by default in UTC time, and it can be overridden by the `SPRING_CRON_SCHEDULE` environment variable. The cron syntax used by Spring is different, so use [CronMaker](http://www.cronmaker.com) to generate the desired schedule. Take the result, and remove the last argument to make it valid. For example, `0 0 0/1 1/1 * ? *` is edited to become `0 0 0/1 1/1 * ?` for use in Spring.

## Disclaimer
COVID-19 Tracker is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

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
