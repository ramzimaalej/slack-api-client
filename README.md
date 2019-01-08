[![Build Status](https://travis-ci.org/ramzimaalej/slack-events-api-client.svg?branch=master)](https://travis-ci.org/ramzimaalej/slack-events-api-client) 

# Slack-Events-Api-Client

Slack-Events-Api-Client is a versatile Java client for the Slack API. It relies on [OpenAPI generator](https://github.com/OpenAPITools/openapi-generator) to generate the api client code. It also provides a high level handler of the Slack events API. 

To contribute, please refer to the list of open issues.

# How to use it

To add Slack-Events-Api-Client to your project, your need to add the following repository:

```xml 
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Then, you need to add this dependency to your pom file

```xml 
<dependency>
    <groupId>com.github.ramzimaalej</groupId>
    <artifactId>slack-events-api-client</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

# License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.