# Spring MVC + Spring HATEOAS app with HAL representations of each resource

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
```
curl -v localhost:8080/employees/1 | python -m json.tool
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

### Fetch the aggregate root
```
curl -v localhost:8080/orders | python -m json.tool
```
#### RESTful representation of a collection of orders resources
```
 GET /orders/ HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: application/hal+json
< Transfer-Encoding: chunked
< Date: Sun, 19 Sep 2021 19:43:07 GMT
<
{ [546 bytes data]
100   539    0   539    0     0    527      0 --:--:--  0:00:01 --:--:--   529
* Connection #0 to host localhost left intact
{
    "_embedded": {
        "orderList": [
            {
                "id": 3,
                "status": "COMPLETED",
                "description": "Acer 1234",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/orders/3"
                    },
                    "orders": {
                        "href": "http://localhost:8080/orders"
                    }
                }
            },
            {
                "id": 4,
                "status": "IN_PROGRESS",
                "description": "Iphone 13",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/orders/4"
                    },
                    "orders": {
                        "href": "http://localhost:8080/orders"
                    },
                    "cancel": {
                        "href": "http://localhost:8080/orders/4/cancel"
                    },
                    "complete": {
                        "href": "http://localhost:8080/orders/4/complete"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/orders"
        }
    }
}
```

### Cancel order 

```
curl -v -X DELETE http://localhost:8080/orders/4/cancel | python -m json.tool
```
#### Output

```
> DELETE /orders/4/cancel HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: */*
>
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0* Mark bundle as not supporting multiuse
< HTTP/1.1 200
< Content-Type: application/hal+json
< Transfer-Encoding: chunked
< Date: Sun, 19 Sep 2021 19:58:39 GMT
<
{ [170 bytes data]
100   164    0   164    0     0    166      0 --:--:-- --:--:-- --:--:--   167
* Connection #0 to host localhost left intact
{
    "id": 4,
    "status": "CANCELLED",
    "description": "Iphone 13",
    "_links": {
        "self": {
            "href": "http://localhost:8080/orders/4"
        },
        "orders": {
            "href": "http://localhost:8080/orders"
        }
    }
}
```

### Cancel order again

```
curl -v -X DELETE http://localhost:8080/orders/4/cancel | python -m json.tool
```
#### Output
```
> DELETE /orders/4/cancel HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 405
< Content-Type: application/problem+json
< Transfer-Encoding: chunked
< Date: Sun, 19 Sep 2021 19:47:59 GMT
<
{ [105 bytes data]
100    99    0    99    0     0   1067      0 --:--:-- --:--:-- --:--:--  1222
* Connection #0 to host localhost left intact
{
    "title": "Method not allowed",
    "detail": "You can't cancel an order that is in the CANCELLED status"
}
```
### Trying to complete the same order

```
curl -v -X DELETE http://localhost:8080/orders/4/cancel | python -m json.tool
```
#### Output
```
> PUT /orders/4/complete HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.78.0
> Accept: */*
>
* Mark bundle as not supporting multiuse
< HTTP/1.1 405
< Content-Type: application/problem+json
< Transfer-Encoding: chunked
< Date: Sun, 19 Sep 2021 20:00:39 GMT
<
{ [79 bytes data]
100    73    0    73    0     0    822      0 --:--:-- --:--:-- --:--:--   879
* Connection #0 to host localhost left intact
{
    "detail": "Yous can't complete an order that is in the CANCELLED status"
}

```