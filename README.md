# openapi-java-client

Oden API
- API version: 2.0.0
  - Build date: 2025-09-11T21:54:58.815514129Z[Etc/UTC]
  - Generator version: 7.15.0

The Oden Private Partner API exposes RESTful API endpoints for clients to get, create and update data on the Oden Platform.

The API is based on the OpenAPI 3.0 specification.
### Current Version
The URL, and host, for the current version is [https://api.oden.app/v2](https://api.oden.app/v2).

### Oden's Data Model
- **Organization**: This represents the Organization registered as an Oden customer. An organization has an associated collection of users, factories, lines, etc. This is the entity a specific authentication token is associated with.
- **Asset** or **Machinegroup**: Assets, or machinegroups, are collections of machines, which may either be a **Factory** or a **Line**:
  - **Factory**: Factories are collections of lines, representing a particular manufacturing location.
    - **Line**: Lines are collections of machines, often representing a particular production line. Lines may also have **Products** mapped to them, indicating what is currently being manufactured by the specific line.
      - **Machine**: Machines are the physical machines that make up a line
- **Product**: Products capture what entities a manufacturer produces
- **Interval**: An interval is a period of time that takes place on a manufacturing line and expresses some business concern. It's Oden's way of making metrics aggregatable, traceable, and relatable to a manufacturer.
  - **Run**: A run is a production interval that labels a period of production as being work on some single product
  - **Batch**: A batch is a production interval that represents a portion of a particular run
  - **State**: A state is an interval that tracks the availability or utilization of a line
    - **State Category**: A state category describes what state a line is in - such as ex. uptime, downtime, scrapping, etc.
    - **State Reason**: A state reason describes why a line is in a particular state - such as \"maintenance\" being a reason for the category \"downtime\".
  - **Custom**: A custom interval can track any other type of interval-based data a manufacturer might want to analyze. These are created on a per-factory basis.
- **Target**: Targets specify values and upper/lower thresholds for metrics when specific products are running on specific lines
- **Scrap/Yield**: Scrap/yield output specifies amount of produced product on a line during either a run or batch interval. Oden will categorize all output as either scrap or yield - as specified by the Scrap Yield Schema for a given factory. If you have other categories, like rework/blocked/off-grade, you must choose between categorizing those amounts as either good or bad production by specifying as scrap or yield. Clients may also add scrap codes (i.e., reasons) to a given Scrap Yield Data entry.
  - **Scrap Code**: A scrap code is a code that explains the reason for a scrap/yield raw data input - such as \"Rework\"
- **Quality Test**: Quality Tests are results of quality assurance tests done on site, and uploaded to Oden. They may be attached to a single Batch or Run.
- **Metric**: Known in factories as \"tags\", metrics are the raw data that is collected by Oden from the machines and devices on the factory floor.
- **Metric Group**: Metric groups are metrics that represent the same thing across different lines. They provide common display names for tags and allow labeling groups of tags as measuring key types of data like performance or production.

### Best Practices
Under the current implementation, the Oden API does not rate limit requests from clients.

However, rate limiting will be introduced in the near future and it is recommended that users design their API
clients to not exceed a request rate of **one per second**.

### Schema
All v2 API access is over HTTPS and accessed from https://api.oden.app/v2
All data is sent and received as JSON.

API requests with duplicate keys will process only the data for the first key detected and ignore the rest, so it's not recommended. Batching multiple messages in this way is currently not possible.
  - Example of duplicate key in JSON: {\"raw_data\":{\"scrap\":\"10\",\"scrap\":\"100\"}}

All timestamps are returned in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601#Times) format:

  `YYYY-MM-DDTHH:MM:SSZ`

All durations are returned in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601#Times) format with the largest unit of time being the hour:

   `PT[n]H[n]M[n]S`

All timestamps sent to the API in POST requests should be in ISO 8601 format.
### HTTP Verbs
The ONLY HTTP call type (sometimes called *verb* or *method*) used within Oden's API is **POST**.
There are three actions supported via a **POST**; call, search, set, and delete, together supporting CRUD operations;
  - **search** requests are used to search for and *read* objects in the Oden Platform
      - All Oden Objects may be uniquely identified by some combination of, or a single, parameter.
        - Ex a `line` my be identified by either:
          - `id`
          - `name` AND `factory`
  - **set** requests are used to *create* or *update* objects
  - **delete** requests are used to *delete* objects. If a delete endpoint is not yet implemented for a given object, users may choose to update the values of a specific entity to null or 0 values.

### URI Components
All endpoints may be accessed with the URI pattern:
`https://api.oden.app/v2/{object}/{action}`

Where:
  - `object` is the name of the object being requested:
       - `factory`, `quality_test`, `interval`, `line`, etc...
  - `action` is the name of the action being requested
    - `search` , `set` , `delete`

e.g. `https://api.oden.app/v2/factory/search`

# Authentication
Clients can authenticate through the v2 API using a Token provided by Oden. Tokens are opaque strings similar to
[Bearer](https://swagger.io/docs/specification/authentication/bearer-authentication/) tokens that the client must
pass in the [HTTP Authorization request header](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Authorization) in every request.
The syntax is as follows:

`Authorization: <type> <credentials>`

Where \\<type\\> is \"Token\" and \\<credentials\\> is the Token string. For example:

`Authorization: Token tokenStringProvidedByOden`

Authenticating with an invalid Token will return `401 Unauthorized Error`.

Authenticating with a Token that is not authorized to read requested data will return `403 Forbidden Error`.

Some endpoints may require requests to be broken out by machinegroup (i.e., line or factory) and the number of
requests would scale accordingly. This multiplicity should be taken into consideration when deciding on the
frequency the API client makes requests to the Oden endpoints.

To authenticate in this [UI](https://api.oden.app/v2/ui/), click the Lock icon, and copy/paste the token into the Authorize box.


  For more information, please visit [https://oden.io/contact/](https://oden.io/contact/)

*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>org.openapitools</groupId>
  <artifactId>openapi-java-client</artifactId>
  <version>2.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'openapi-java-client' jar has been published to maven central.
    mavenLocal()       // Needed if the 'openapi-java-client' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "org.openapitools:openapi-java-client:2.0.0"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/openapi-java-client-2.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.model.*;
import oden.api.IntervalsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    IntervalsApi apiInstance = new IntervalsApi(defaultClient);
    Interval interval = new Interval(); // Interval | 
    try {
      List<Interval> result = apiInstance.v2IntervalDeletePost(interval);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IntervalsApi#v2IntervalDeletePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://api.oden.app*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*IntervalsApi* | [**v2IntervalDeletePost**](docs/IntervalsApi.md#v2IntervalDeletePost) | **POST** /v2/interval/delete | 
*IntervalsApi* | [**v2IntervalSearchPost**](docs/IntervalsApi.md#v2IntervalSearchPost) | **POST** /v2/interval/search | 
*IntervalsApi* | [**v2IntervalSetPost**](docs/IntervalsApi.md#v2IntervalSetPost) | **POST** /v2/interval/set | 
*IntervalsApi* | [**v2IntervalTypeSearchPost**](docs/IntervalsApi.md#v2IntervalTypeSearchPost) | **POST** /v2/interval_type/search | 
*IntervalsApi* | [**v2IntervalsDeletePost**](docs/IntervalsApi.md#v2IntervalsDeletePost) | **POST** /v2/intervals/delete | 
*IntervalsApi* | [**v2IntervalsSetPost**](docs/IntervalsApi.md#v2IntervalsSetPost) | **POST** /v2/intervals/set | 
*MachineGroupsApi* | [**v2FactorySearchPost**](docs/MachineGroupsApi.md#v2FactorySearchPost) | **POST** /v2/factory/search | 
*MachineGroupsApi* | [**v2LineSearchPost**](docs/MachineGroupsApi.md#v2LineSearchPost) | **POST** /v2/line/search | 
*MetricGroupsApi* | [**v2MetricGroupSearchPost**](docs/MetricGroupsApi.md#v2MetricGroupSearchPost) | **POST** /v2/metric_group/search | 
*OqlApi* | [**v2OqlQueryPost**](docs/OqlApi.md#v2OqlQueryPost) | **POST** /v2/oql/query | 
*ProductAttributesApi* | [**v2ProductAttributeSearchPost**](docs/ProductAttributesApi.md#v2ProductAttributeSearchPost) | **POST** /v2/product_attribute/search | 
*ProductAttributesApi* | [**v2ProductAttributeSetPost**](docs/ProductAttributesApi.md#v2ProductAttributeSetPost) | **POST** /v2/product_attribute/set | 
*ProductMappingsApi* | [**v2ProductMappingSearchPost**](docs/ProductMappingsApi.md#v2ProductMappingSearchPost) | **POST** /v2/product_mapping/search | 
*ProductMappingsApi* | [**v2ProductMappingSetPost**](docs/ProductMappingsApi.md#v2ProductMappingSetPost) | **POST** /v2/product_mapping/set | 
*ProductsApi* | [**v2ProductDeletePost**](docs/ProductsApi.md#v2ProductDeletePost) | **POST** /v2/product/delete | 
*ProductsApi* | [**v2ProductSearchPost**](docs/ProductsApi.md#v2ProductSearchPost) | **POST** /v2/product/search | 
*ProductsApi* | [**v2ProductSetPost**](docs/ProductsApi.md#v2ProductSetPost) | **POST** /v2/product/set | 
*QualityTestApi* | [**v2QualitySchemaSearchPost**](docs/QualityTestApi.md#v2QualitySchemaSearchPost) | **POST** /v2/quality_schema/search | 
*QualityTestApi* | [**v2QualityTestDeletePost**](docs/QualityTestApi.md#v2QualityTestDeletePost) | **POST** /v2/quality_test/delete | 
*QualityTestApi* | [**v2QualityTestSearchPost**](docs/QualityTestApi.md#v2QualityTestSearchPost) | **POST** /v2/quality_test/search | 
*QualityTestApi* | [**v2QualityTestSetPost**](docs/QualityTestApi.md#v2QualityTestSetPost) | **POST** /v2/quality_test/set | 
*QualityTestApi* | [**v2QualityTestsDeletePost**](docs/QualityTestApi.md#v2QualityTestsDeletePost) | **POST** /v2/quality_tests/delete | 
*ScrapYieldDataApi* | [**v2ScrapYieldDeletePost**](docs/ScrapYieldDataApi.md#v2ScrapYieldDeletePost) | **POST** /v2/scrap_yield/delete | 
*ScrapYieldDataApi* | [**v2ScrapYieldSearchPost**](docs/ScrapYieldDataApi.md#v2ScrapYieldSearchPost) | **POST** /v2/scrap_yield/search | 
*ScrapYieldDataApi* | [**v2ScrapYieldSetPost**](docs/ScrapYieldDataApi.md#v2ScrapYieldSetPost) | **POST** /v2/scrap_yield/set | 
*TargetsApi* | [**v2TargetSearchPost**](docs/TargetsApi.md#v2TargetSearchPost) | **POST** /v2/target/search | 
*TargetsApi* | [**v2TargetSetPost**](docs/TargetsApi.md#v2TargetSetPost) | **POST** /v2/target/set | 


## Documentation for Models

 - [BatchMetadata](docs/BatchMetadata.md)
 - [Factory](docs/Factory.md)
 - [GenericError](docs/GenericError.md)
 - [Interval](docs/Interval.md)
 - [IntervalBulkCreate](docs/IntervalBulkCreate.md)
 - [IntervalBulkDelete](docs/IntervalBulkDelete.md)
 - [IntervalMetadata](docs/IntervalMetadata.md)
 - [IntervalType](docs/IntervalType.md)
 - [Line](docs/Line.md)
 - [Match](docs/Match.md)
 - [MetricGroup](docs/MetricGroup.md)
 - [OQLQuery](docs/OQLQuery.md)
 - [Product](docs/Product.md)
 - [ProductAttribute](docs/ProductAttribute.md)
 - [ProductMapping](docs/ProductMapping.md)
 - [QualitySchema](docs/QualitySchema.md)
 - [QualityTest](docs/QualityTest.md)
 - [RunMetadata](docs/RunMetadata.md)
 - [ScrapYieldData](docs/ScrapYieldData.md)
 - [ScrapYieldSchema](docs/ScrapYieldSchema.md)
 - [StateCategory](docs/StateCategory.md)
 - [StateMetadata](docs/StateMetadata.md)
 - [StateReason](docs/StateReason.md)
 - [Target](docs/Target.md)
 - [Unit](docs/Unit.md)
 - [V2IntervalsDeletePost200Response](docs/V2IntervalsDeletePost200Response.md)
 - [V2IntervalsSetPost200Response](docs/V2IntervalsSetPost200Response.md)
 - [V2LineSearchPost400Response](docs/V2LineSearchPost400Response.md)
 - [V2LineSearchPost409Response](docs/V2LineSearchPost409Response.md)
 - [V2LineSearchPost500Response](docs/V2LineSearchPost500Response.md)
 - [V2QualityTestsDeletePostRequest](docs/V2QualityTestsDeletePostRequest.md)
 - [V2ScrapYieldSearchPostRequest](docs/V2ScrapYieldSearchPostRequest.md)
 - [V2ScrapYieldSetPostRequest](docs/V2ScrapYieldSetPostRequest.md)


<a id="documentation-for-authorization"></a>
## Documentation for Authorization


Authentication schemes defined for the API:
<a id="APIKeyAuth"></a>
### APIKeyAuth

- **Type**: API key
- **API key parameter name**: Authorization
- **Location**: HTTP header


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

support@oden.io

