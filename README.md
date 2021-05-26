# ？Correct an Address

```
 o-o        o-o                       o                     O     o    o                
o   o      /                          |                    / \    |    |                
   /      O     o-o o-o o-o o-o  o-o -o-      oo o-o      o---o o-O  o-O o-o o-o o-o o-o
  o        \    | | |   |   |-' |     |      | | |  |     |   ||  | |  | |   |-'  \   \ 
            o-o o-o o   o   o-o  o-o  o      o-o-o  o     o   o o-o  o-o o   o-o o-o o-o
  O                   🌠 Copyright © 2021 Ionuț Roșca <ionut.rosca@info.uaic.ro>        
```
## Live Demo
Want to see it live? Say no more! You can view the latest version of the Web app at https://reloadedd.me:5443/.

## Requirements
- Write an algorithm that corrects the fields country, state, city of a postal address. Example: 
```
Country: RO, State: New York, City: Iasi
```
will become
```
Country: RO, State: Iasi, City: Iasi
```
- The algorithm needs to have unit tests and integration tests for performance and precision
- Ideally the algorithm will work for all countries in the world and a few languages
- Expose a REST API using Spring Boot that will receive a postal address and return the corrected result
- Deploy the application as a Docker container in AWS/Heroku or other using a continuous deployment pipeline

## Changelog
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html). In order to view the
changes for each version, please consult the [CHANGELOG](CHANGELOG.md) file.

## License
The `? Correct an Address` project is available under the GNU General Public License v3.0 License.
For the full license text please read the [LICENSE](LICENSE) file.
