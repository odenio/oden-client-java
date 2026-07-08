# TargetsApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**searchTargets**](TargetsApi.md#searchTargets) | **POST** /v2/target/search | Search metric targets |
| [**setTarget**](TargetsApi.md#setTarget) | **POST** /v2/target/set | Create or update a metric target |


<a id="searchTargets"></a>
# **searchTargets**
> List&lt;Target&gt; searchTargets(target)

Search metric targets

Search for a Target by &#x60;line&#x60;, &#x60;metric_group&#x60;, and &#x60;product&#x60;. For each of these inputs, any of their unique indentifiers (as described in their &#x60;search&#x60; endpoint) may be used. See examples. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.TargetsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    TargetsApi apiInstance = new TargetsApi(defaultClient);
    Target target = new Target(); // Target | 
    try {
      List<Target> result = apiInstance.searchTargets(target);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TargetsApi#searchTargets");
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
| **target** | [**Target**](Target.md)|  | |

### Return type

[**List&lt;Target&gt;**](Target.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of targets. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

<a id="setTarget"></a>
# **setTarget**
> Target setTarget(target)

Create or update a metric target

Create or update a Target.  First the endpoint will search for a Target by &#x60;metric_group&#x60;, &#x60;product&#x60;, and &#x60;line&#x60;: - If the target does not exist a new target is created. - If the product or its mapping to the given line does not exist, they will be created. - If a target exists but with different parameters, it will be updated. - If the target exists with all the same parameters nothing is done. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.TargetsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    TargetsApi apiInstance = new TargetsApi(defaultClient);
    Target target = new Target(); // Target | 
    try {
      Target result = apiInstance.setTarget(target);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TargetsApi#setTarget");
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
| **target** | [**Target**](Target.md)|  | |

### Return type

[**Target**](Target.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Updated target. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

