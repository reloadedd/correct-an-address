# Correct an Address

```
dP"Yb    .d88b                             w                       db       8    8                      
"  d8    8P    .d8b. 8d8b 8d8b .d88b .d8b w8ww    .d88 8d8b.      dPYb   .d88 .d88 8d8b .d88b d88b d88b 
  dP     8b    8' .8 8P   8P   8.dP' 8     8      8  8 8P Y8     dPwwYb  8  8 8  8 8P   8.dP' `Yb. `Yb. 
  w      `Y88P `Y8P' 8    8    `Y88P `Y8P  Y8P    `Y88 8   8    dP    Yb `Y88 `Y88 8    `Y88P Y88P Y88P 
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
