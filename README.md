##Akka PubSub Cluster Example

This is an example of the DistributedPubSubExtension Akka extension. To run it you will need SBT install. Clone the project and from the project directory

### Run WebService
- type sbt
- type run
- You will be prompted for which App to run. Choose "ApiBoot"

### Run BackendService 
- type sbt
- type run 4444 (or whatever port you want)
- You will be prompted for which App to run. Choose "BackendServiceBoot"

Now you should have a cluster with a WebService and BackendService. Try calling the http service

curl http://localhost:8085/dowork

You should see a response "OK". Now try running another BackendService by repeating the "Run BackendService steps but giving a different port. Now when you call the dowork route, one of the two BackendServices will be called randomly.

