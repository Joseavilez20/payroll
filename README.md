## Testing Request 🚀
### On Windows
### Creating a new employee

```
curl -H "Content-Type: application/json" -X POST -d {\"name\":\"mkyong\",
\"role\":\"gardener\"} http://localhost:8080/employees
```

#### Output 
```
{"id":3,"name":"mkyong","role":"gardener"}
```

### Update the user
```
curl -H "Content-Type: application/json" -X PUT -d {\"name\":\"mkyong\",\"role\":\"ringbearer\"} http://localhost:8080/employees/3
```

#### Output

```
{"id":3,"name":"mkyong","role":"ringbearer"}
```

### Delete User 
```
curl -X DELETE localhost:8080/employees/3
```

### Before of converter the application in REST API
```curl -v localhost:8080/employees/1 | python -m json.tool
```

#### Output

```
*   Trying 127.0.0.1:8080...
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /employees/1 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: application/hal+json
< Transfer-Encoding: chunked
< Date: Sun, 12 Sep 2021 18:44:20 GMT
<
{ [120 bytes data]
100   109    0   109    0     0    903      0 --:--:-- --:--:-- --:--:--   947
* Connection #0 to host localhost left intact
{
    "id": 1,
    "name": "Juan Sierra",
    "role": "burglar",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/1"
        }
    }
}
```
### Fetch the aggregate root
```
curl -v localhost:8080/employees/1 | python -m json.tool
```
#### RESTful representation of a collection of employee resources
```
{
    "_embedded": {
        "employeeList": [
            {
                "id": 1,
                "name": "Juan Sierra",
                "role": "burglar",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/1"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            },
            {
                "id": 2,
                "name": "Camila Narvaez",
                "role": "thief",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/2"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees"
        }
    }
}
```