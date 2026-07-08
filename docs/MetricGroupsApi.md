# MetricGroupsApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v2MetricGroupSearchPost**](MetricGroupsApi.md#v2MetricGroupSearchPost) | **POST** /v2/metric_group/search |  |


<a id="v2MetricGroupSearchPost"></a>
# **v2MetricGroupSearchPost**
> List&lt;MetricGroup&gt; v2MetricGroupSearchPost(metricGroup)



Search for a specific Metric Group:  - &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  OR  - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  Search for all Metric Groups: - &#x60;match: all&#x60; 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.MetricGroupsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    MetricGroupsApi apiInstance = new MetricGroupsApi(defaultClient);
    MetricGroup metricGroup = new MetricGroup(); // MetricGroup | 
    try {
      List<MetricGroup> result = apiInstance.v2MetricGroupSearchPost(metricGroup);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MetricGroupsApi#v2MetricGroupSearchPost");
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
| **metricGroup** | [**MetricGroup**](MetricGroup.md)|  | |

### Return type

[**List&lt;MetricGroup&gt;**](MetricGroup.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of metric groups. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

