# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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

[Unreleased]: https://github.com/reloadedd/correct-an-address/compare/v0.3.0...HEAD
[0.3.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.3.0
[0.2.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.2.0
[0.1.0]: https://github.com/reloadedd/correct-an-address/releases/tag/v0.1.0