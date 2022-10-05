# Upload a File and scan it for viruses using multipl AV engines

Currently this is not a fully functioning application but is a test to use React, Spring, Kafka, and MongoDB


## Start Application
1. `cd ui; npm start`
2. `cd backend; <start spring boot however>`
3. `cd kafka; docker-compose -f zk-single-kafka-single.yml up`
4. `start a mongodb instance`

### View Page at
http://localhost:3000/ 


### View Kafka Topics

`docker exec -it 1e7fbc54ba31 kafka-topics --bootstrap-server localhost:29092 --list`