# Simple Java-based Lambda Microservice

## Setup and Build

* Create Docker volume for Maven repository
  ```bash
  docker volume create maven-repository
  ```
* Run Maven in Container
  ```bash
  docker run --rm -it -v $PWD:/usr/src/app -v maven-repository:/root/.m2/repository -w /usr/src/app maven:3.6-jdk-8 /bin/bash
  ```
* Create project with Maven
  ```bash
  docker run --rm -v $PWD:/usr/src/app -v maven-repository:/root/.m2/repository -w /usr/src/app maven:3.6-jdk-8 mvn archetype:generate -DgroupId=com.carpinuslabs.todo -DartifactId=todo-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
  ```
* Add dependencies
  ```xml
  <dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-lambda-java-core</artifactId>
    <version>1.1.0</version>
  </dependency>
  <dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-lambda-java-events</artifactId>
    <version>2.2.5</version>
  </dependency>
  ```
* Configure packaging
  ```xml
  <packaging>jar</packaging>
  ```
* Configure compiler settings
  ```xml
  <maven.compiler.source>1.8</maven.compiler.source>
  <maven.compiler.target>1.8</maven.compiler.target>
  ```
* Add `shade` plugin
  ```xml
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.3</version>
        <configuration>
          <createDependencyReducedPom>false</createDependencyReducedPom>
        </configuration>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
  ```
* Build project in Container
  ```bash
  docker run --rm -v $PWD:/usr/src/app -v maven-repository:/root/.m2/repository -w /usr/src/app maven:3.6-jdk-8 mvn clean package -DskipTests
  ```
