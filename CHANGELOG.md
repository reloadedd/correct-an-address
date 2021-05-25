# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.6.0] - 2021-05-25
### Added
- `/results` route that will display the corrected address into a Google Maps view.
- The REST API is working and corrects addresses based on the query score received from the
HERE Maps API.

### Modified
- Move `Jenkinksfile` to the root
- Modify `Jenkinsfile` to take into account the new folder structure

## [0.5.0] - 2021-05-25
### Added
- Create the GUI program that will interact with the REST API

## Modified
- The repository now contains both the Web interface and the GUI program

## [0.4.0] - 2021-05-24
### Added
- Create credentials file for HERE Maps
- Add Thymeleaf Maven dependency for templating HTML pages 
- Create the `routes` package together with the `IndexRoute` class that will server users with
the main page.

### Fixed
- Fix wrong redirect to `/login` route
- Static resources are now delivered properly

## [0.3.1] - 2021-05-22
### Added
- Jenkins will set the status of git commits

### Changed
- Remove unnecessary classes from the `config` package and replace with `server.ssl.enabled=true`

## [0.3.0] - 2021-05-22
### Added
- Add HTTPS support
- Create `config` package which includes configuration classes for the application.
`ServerConfig` class will redirect all HTTP traffic to port 5080 -> 5443.

### Changed
- Change application port to 5443 to indicate that it's using HTTPS.

## [0.2.0] - 2021-05-21
### Added
- Create Bash script to change version automatically
- Create `Jenkinsfile` to be used for the Jenkins CI/CD Pipeline

### Changed
- Modify `Dockerfile` to use image `openjdk:11.0.9-jre-slim`
- Expose port 5080 in `Dockerfile`

## [0.1.0] - 2021-05-06
### Added
- First working implementation of the Spring Boot Application
- Create Dockerfile
- Create this changelog

[Unreleased]: https://github.com/reloadedd/correct-an-address/compare/v0.6.0...HEAD
[0.6.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.6.0
[0.5.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.5.0
[0.4.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.4.0
[0.3.1]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.3.1
[0.3.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.3.0
[0.2.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.2.0
[0.1.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.1.0