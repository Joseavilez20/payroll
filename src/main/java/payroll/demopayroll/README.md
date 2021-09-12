On Windows
curl -H "Content-Type: application/json" -X POST -d {\"name\":\"mkyong\",\"role\":\"gardener\"} http://localhost:8080/employees

Output 
{"id":3,"name":"mkyong","role":"gardener"}