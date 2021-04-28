# Correct an Address

```


8""""8    8""""8                                                       8""""8                                     
     8    8    " eeeee eeeee  eeeee  eeee eeee eeeee    eeeee eeeee    8    8 eeeee eeeee eeeee  eeee eeeee eeeee 
eeeee8    8e     8  88 8   8  8   8  8    8  8   8      8   8 8   8    8eeee8 8   8 8   8 8   8  8    8   " 8   " 
88        88     8   8 8eee8e 8eee8e 8eee 8e     8e     8eee8 8e  8    88   8 8e  8 8e  8 8eee8e 8eee 8eeee 8eeee 
""        88   e 8   8 88   8 88   8 88   88     88     88  8 88  8    88   8 88  8 88  8 88   8 88      88    88 
88        88eee8 8eee8 88   8 88   8 88ee 88e8   88     88  8 88  8    88   8 88ee8 88ee8 88   8 88ee 8ee88 8ee88 
                                                                                                                  

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
