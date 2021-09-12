On Windows
Creating a new employee
curl -H "Content-Type: application/json" -X POST -d {\"name\":\"mkyong\",\"role\":\"gardener\"} http://localhost:8080/employees

Output 
{"id":3,"name":"mkyong","role":"gardener"}

Update the user
curl -H "Content-Type: application/json" -X PUT -d {\"name\":\"mkyong\",\"role\":\"ringbearer\"} http://localhost:8080/employees/3

Output

{"id":3,"name":"mkyong","role":"ringbearer"}

Delete User 
curl -X DELETE localhost:8080/employees/3