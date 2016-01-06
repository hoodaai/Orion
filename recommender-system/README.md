# recommender system
A distributed recommender system built using Java. It is devided in following lossely coupled modules
- **recommendation_api** : contains rest api to push events in system and get recommendation.
- **event_framework**: contains Events and their Event handlers.
- **spring_rabbit**: spring implementation of rabbitmq. It contains rabbitmq producer.
- **queue_worker**: rabbitmq consumer which polls events from queue and save them in to database
- **recommendation_algo**: contains recommendation algorithm implemenation and a multithreaded batch job which runs algorithm on database and finds recommendation and save them in different table.

## Technology Stack
- Java
- Spring
- RabbitMQ
- Aerospike

## Architecure Diagram

![recommender_system](https://cloud.githubusercontent.com/assets/1543471/8470100/aa368a94-20a3-11e5-9371-be4e2353aaa4.png)

## Setup Guide
### OSX
#### Install Gradle
```
$ brew update
$ brew install gradle
```

#### Set GRADLE_HOME
- open ~/.bash_profile
- Add below entry
```
export GRADLE_HOME=/usr/local/Cellar/gradle/2.4
```

#### How To import project as idea project
There are two root projects and one independent project
- recommendation_api
- queue_worker
- recommendation_api is independent project which does not have dependency on any other module.


Go to project root directory i.e. **recommendation_api** , **queue_worker** and **recommendation_algo**respectively and run
```
$ gradle wrapper
$ gradle idea
```
- Now open intelllij Idea IDE and choose *File>New>Project From Existing Sources* option
- Select project root directory for example *recommender_system/recommendation_api*, and click ok button
- Choose *import project from external model* option and choose *gradle* as external model
- Click next
- Set Gradle Home and Gradle JVM. Rest  of the option you can leave default
- Click on Finish and your idea project is ready.

#### Install RabbitMQ
```
$ brew update
$ brew install rabbitmq
```
- Open .bash_profile and add below entry
```
PATH=$PATH:/usr/local/sbin
```
- Then run this command to reload .bash_profile
```
$ source .bash_profile
```

#### To start rabbitmq server
$ rabbitmq-server start

## Running Project

- go to recommender_system>recommendation_api directory and run
```
$ gradle jettyRunWar
```
- got to recommender_system>queue_worker directory and run
```
$ gradle run
```






