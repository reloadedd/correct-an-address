# ？Correct an Address

```
 o-o        o-o                       o                     O     o    o                
o   o      /                          |                    / \    |    |                
   /      O     o-o o-o o-o o-o  o-o -o-      oo o-o      o---o o-O  o-O o-o o-o o-o o-o
  o        \    | | |   |   |-' |     |      | | |  |     |   ||  | |  | |   |-'  \   \ 
            o-o o-o o   o   o-o  o-o  o      o-o-o  o     o   o o-o  o-o o   o-o o-o o-o
  O                   🌠 Copyright © 2021 Ionuț Roșca <ionut.rosca@info.uaic.ro>        
```

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
