# MachineGroupsApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v2FactorySearchPost**](MachineGroupsApi.md#v2FactorySearchPost) | **POST** /v2/factory/search |  |
| [**v2LineSearchPost**](MachineGroupsApi.md#v2LineSearchPost) | **POST** /v2/line/search |  |


<a id="v2FactorySearchPost"></a>
# **v2FactorySearchPost**
> List&lt;Factory&gt; v2FactorySearchPost(factory)



Search for a specific Factory by a unique indentifier: - &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  OR  - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  Search for all factories: - &#x60;match: all&#x60; 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.MachineGroupsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    MachineGroupsApi apiInstance = new MachineGroupsApi(defaultClient);
    Factory factory = new Factory(); // Factory | 
    try {
      List<Factory> result = apiInstance.v2FactorySearchPost(factory);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MachineGroupsApi#v2FactorySearchPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **factory** | [**Factory**](Factory.md)|  | |

### Return type

[**List&lt;Factory&gt;**](Factory.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of factories. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

<a id="v2LineSearchPost"></a>
# **v2LineSearchPost**
> List&lt;Line&gt; v2LineSearchPost(line)



Search for specific Line by any Line identifier. Either: - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  OR - &#x60;factory&#x60;   - &#x60;name&#x60; or &#x60;id&#x60; - line &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  Search for all Lines for a given Factory: - &#x60;factory&#x60;   - &#x60;name&#x60; or &#x60;id&#x60; - &#x60;match: all&#x60; 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.MachineGroupsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    MachineGroupsApi apiInstance = new MachineGroupsApi(defaultClient);
    Line line = new Line(); // Line | 
    try {
      List<Line> result = apiInstance.v2LineSearchPost(line);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MachineGroupsApi#v2LineSearchPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **line** | [**Line**](Line.md)|  | |

### Return type

[**List&lt;Line&gt;**](Line.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of lines. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |

