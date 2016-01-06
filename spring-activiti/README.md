## Spring Activiti Project

### How I created this project's structure

```
gradle init --type java-library
```
 
 - Above command creates gradle based directory structure along with gradle wrapper and gradle build file
 
 - Now edit build.gradle and add idea, eclipse and jetty plugin
 
 ```
 apply plugin: 'idea'
 apply plugin: 'eclipse'
 apply plugin: 'jetty'
 ```

### How to configure this project in Intellij Idea
In order to import this project you have to follow below steps:

 - Run gradle idea command to create idea based ipr files
 
 ```
$ gradle idea
```

* import spring_activiti project in Intellij Idea as follows
     * Now open intellij idea
     * go to File>Open and navigate to 'spring_activiti' project to open it.


Jar dependencies
[activiti-spring](http://mvnrepository.com/artifact/org.activiti/activiti-spring/5.18.0)
activiti-engine