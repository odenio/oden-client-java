# DashboardsApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeDashboard**](DashboardsApi.md#executeDashboard) | **POST** /v2/dashboard/execute | Execute a dashboard |


<a id="executeDashboard"></a>
# **executeDashboard**
> List&lt;DashboardExecuteResult&gt; executeDashboard(dashboardExecuteRequest)

Execute a dashboard

Execute every module in a dashboard with shared time-range and filter overrides, returning the columns and rows produced by each module.  Modules execute in parallel and are reported in the dashboard&#39;s stored module order. Per-module failures (parse, dispatch) land in the &#x60;error&#x60; field of that module&#39;s result; siblings continue to run.  Known v1 limitations: - Template variables (&#x60;{var_name}&#x60; in stored OQL) are not substituted   server-side. Modules containing unsubstituted placeholders will   fail at parse time. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.DashboardsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    DashboardsApi apiInstance = new DashboardsApi(defaultClient);
    DashboardExecuteRequest dashboardExecuteRequest = new DashboardExecuteRequest(); // DashboardExecuteRequest | 
    try {
      List<DashboardExecuteResult> result = apiInstance.executeDashboard(dashboardExecuteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DashboardsApi#executeDashboard");
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
| **dashboardExecuteRequest** | [**DashboardExecuteRequest**](DashboardExecuteRequest.md)|  | |

### Return type

[**List&lt;DashboardExecuteResult&gt;**](DashboardExecuteResult.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Dashboard loaded and each module executed. Individual modules may still report &#x60;error&#x60; in their result; this is not a 200/non-200 distinction.  |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **429** | Too many requests |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |

