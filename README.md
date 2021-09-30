# brXm

Build powerful commerce experiences on a rock solid foundation

## Pre-requisites

- Java JDK version 8 or above should be installed
- Maven should be installed

## Building application

```shell
make build-project
```

## Starting application locally

```shell
make run-project
```

## Tests

[Test cases - brXm](https://docs.google.com/spreadsheets/d/1IMjs9qqUJZ8lHM6Lf8WlLwmpvLyuIkf-AFHzJX5eh_I/edit?usp=sharing)

Tests are under `/tests` directory

### Running UI tests

```shell
make run-ui-tests
```

### Running API tests

```shell
make run-api-tests
```

## Continuous Integration - Github Actions

*[GHA configuration](/.github/workflows/maven.yml)*

## Reports

### UI test reports

- Generate the report using maven command - `make generate-ui-test-report`
- Reports will be generated in `tests/cms-gui/target/generated-reports/index.html` after running the tests using maven
  command

### API test reports

- Reports will be generated in `tests/booking-api/target/HtmlReport/ExtentHtml.html` after test execution

Test report will be also uploaded to Github Action artifacts
