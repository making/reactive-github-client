# Reactive GitHub API Client

GitHub API Client that uses Reactor's `Flux`/`Mono` and Spring 5's `WebClient`.

## Supported API

* Repositories
  * [Contents](https://developer.github.com/v3/repos/contents/)
  
## Usage

```xml
<dependency>
    <groupId>am.ik.github</groupId>
    <artifactId>reactive-github-client</artifactId>
    <version>${reactive-github-client.version}</version>
</dependency>
```

```java
GitHubClient client = new GitHubClient(WebClient.builder(), new AccessToken("xxxxxx"));
```


### Contents API


#### Get a file
```java
ContentsApi.File file = client.file("making", "reactive-github-client", "pom.xml");
Mono<ContentsResponse.File> pom = file.get();
```

#### Create a file

```java
ContentsApi.File file = client.file("making", "reactive-github-client", "foo.txt");
Mono<ContentsResponse.Put> created = file.create(ContentsRequest.Builder
                .fromPlainText("Hello World!")
                .toCreate("Create foo.txt"));
```

#### Update a file

```java
ContentsApi.File file = client.file("making", "reactive-github-client", "foo.txt");
Mono<ContentsResponse.Put> updated = file.update(ContentsRequest.Builder
                .fromPlainText("Hello World!!!!!")
                .toUpdate("Update foo.txt", "sha of foo.txt"));
```

#### Delete a file

```java
ContentsApi.File file = client.file("making", "reactive-github-client", "foo.txt");
Mono<ContentsResponse.Delete> deleted = file.delete(ContentsRequest.Builder
                .noContent()
                .toDelete("Delete foo.txt", "sha of foo.txt"));
```