# Hiking Tour Service



## Built with
```
JDK 1.8
Spring boot
maven
docker
```

## Run Tests
```
mvn clean test
```

## Run Service
```
cd /path/to/project/root

mvn clean install
docker build -f Dockerfile -t hiking-tour .
docker run -p 8080:8080  hiking-tour 

```

### Hiker 
````
POST http://localhost/api/hikers/

Content-Type: application/json

Reaquest:
{
    "place" : Shire,
    "hikers" : [
        {
        "name" : test user,
        "age" : 20
        }
    ]
}
````
```
DELETE http://localhost:8080/api/hikers/{groupId}
```
```
GET http://localhost:8080/api/hikers/{groupId}
```

```
GET http://localhost:8080/api/admin/hikers/{date}
Date format: YYYY-MM-DD
```

## Monitoring
```
Service support Monitoring assuming that Grafana is already running on production and Prometheus is added as a datasource.
```

## Service Discovery
```
Assuming Eureka server is up and running so that the service will be able to register itself.
```

## Logging
```
Services supports distribution logging using sleuth and zipkin.
```
