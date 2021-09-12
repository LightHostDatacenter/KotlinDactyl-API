[version]: https://shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.devops.lighthost.com.br%2Frepository%2Fsnapshots%2Fbr%2Fcom%2Flighthost%2FKotlinDactyl-API%2Fmaven-metadata.xml&color=informational&label=Latest
[jenkins]: https://jenkins.devops.lighthost.com.br/job/KotlinDactyl-API/
[jenkins-shield]: https://img.shields.io/badge/Download-Jenkins-orange.svg


# KotlinDactyl-API
[![Build Status](https://jenkins.devops.lighthost.com.br/buildStatus/icon?job=KotlinDactyl-API)](https://jenkins.devops.lighthost.com.br/job/KotlinDactyl-API/) ![version][] [ ![jenkins-shield][] ][jenkins]


KotlinDactyl-API is a api wrapper writen **100%** in **Kotlin** to interact with Pterodactyl Panel API.
This wrapper can be used with any Java or Kotlin applications without any problems.

## Why Kotlin?
We chose Kotlin because we can do a lot of things with few lines and few code. This is a language in
expansion and is 100% interoperable with any Java application, expanding the horizons and enabling the
native usage for Android apps.

![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

## Implementation Status
This wrapper will support **100%** of Pterodactyl Panel API.
And will have a Wiki to provide help for all features.

<br>

<table>
<tr><th>Client API </th><th>Application API</th></tr>
<tr><td>

Feature | Status |
:---:| ---
Server Details️| ✔️
File Manager️| ✔️
Database Manager️| ✔️
Network Manager️| ✔️
Backup Manager️| ✔️
Power actions ️| ✔️
Startup manager ️| ✔️
Settings manager ️| ✔️
Account manager ️| ✔️
Schedule manager ️| ✔️
Subuser manager️| ✔️
Console websocket️| ✔️

</td><td>

Feature | Status |
:---:| ---
User manager️|  ❌️️
Node manager️|  ❌️️
Allocation manager️|  ❌️️
Location manager️|  ❌️️
Server manager️|  ❌️️
Database manager️|  ❌️️
Nest manager️|  ❌️️
Egg manager️|  ❌️️
️

</td></tr> </table>

## Dowload ![version][]
The version shold used **without v prefix**




### Maven


```xml
<dependency>
    <groupId>br.com.lighthost</groupId>
    <artifactId>KotlinDactyl-API</artifactId>
    <version>VERSION</version>
</dependency>
```
```xml
<dependency>
    <groupId>br.com.lighthost</groupId>
    <artifactId>KotlinDactyl-API</artifactId>
    <version>VERSION</version>
</dependency>
```

**Gradle**
```gradle
dependencies {
	implementation("br.com.lighthost:KotlinDactyl-API:VERSION")
}
```

```gradle
repositories {
	maven {
		url = uri("https://repo.devops.lighthost.com.br/repository/snapshots")
	}
}
```



