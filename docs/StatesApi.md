# StatesApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**searchStateCategories**](StatesApi.md#searchStateCategories) | **POST** /v2/state_category/search | Search state categories |
| [**searchStateReasons**](StatesApi.md#searchStateReasons) | **POST** /v2/state_reason/search | Search state reasons on one or more lines |
| [**setStateCategory**](StatesApi.md#setStateCategory) | **POST** /v2/state_category/set | Create or update a state category |
| [**setStateReasons**](StatesApi.md#setStateReasons) | **POST** /v2/state_reason/set | Create a state reason on one or more lines |


<a id="searchStateCategories"></a>
# **searchStateCategories**
> List&lt;StateCategoryDetail&gt; searchStateCategories(stateCategorySearch)

Search state categories

Returns the state categories available to your organization, including Oden&#39;s built-in ones. Filter by &#x60;id&#x60; or &#x60;name&#x60;; omit both to list all. Built-in categories have no &#x60;organization&#x60; and cannot be modified.  &#x60;type&#x60; summarises how the platform treats time in the category: - &#x60;uptime&#x60;: producing good product - &#x60;scrapping&#x60;: running but not producing good product - &#x60;planned_downtime&#x60;, &#x60;unplanned_downtime&#x60;: not running 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.StatesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    StatesApi apiInstance = new StatesApi(defaultClient);
    StateCategorySearch stateCategorySearch = new StateCategorySearch(); // StateCategorySearch | 
    try {
      List<StateCategoryDetail> result = apiInstance.searchStateCategories(stateCategorySearch);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatesApi#searchStateCategories");
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
| **stateCategorySearch** | [**StateCategorySearch**](StateCategorySearch.md)|  | |

### Return type

[**List&lt;StateCategoryDetail&gt;**](StateCategoryDetail.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The matching state categories. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |

<a id="searchStateReasons"></a>
# **searchStateReasons**
> List&lt;StateReasonDetail&gt; searchStateReasons(stateReasonSearch)

Search state reasons on one or more lines

Returns the state reasons defined on every line in &#x60;lines&#x60; (required; each by &#x60;id&#x60;, or by &#x60;name&#x60; plus &#x60;factory&#x60;), optionally restricted to one &#x60;category&#x60; (by &#x60;id&#x60; or &#x60;name&#x60;). Each result names its line, so one request can audit a whole fleet. Inactive reasons are included, with &#x60;active: false&#x60;. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.StatesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    StatesApi apiInstance = new StatesApi(defaultClient);
    StateReasonSearch stateReasonSearch = new StateReasonSearch(); // StateReasonSearch | 
    try {
      List<StateReasonDetail> result = apiInstance.searchStateReasons(stateReasonSearch);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatesApi#searchStateReasons");
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
| **stateReasonSearch** | [**StateReasonSearch**](StateReasonSearch.md)|  | |

### Return type

[**List&lt;StateReasonDetail&gt;**](StateReasonDetail.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The matching state reasons. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |

<a id="setStateCategory"></a>
# **setStateCategory**
> StateCategoryDetail setStateCategory(stateCategorySet)

Create or update a state category

**Create** (no &#x60;id&#x60;): &#x60;name&#x60; and &#x60;type&#x60; are required. If a category with that &#x60;name&#x60; already exists for your organization and has the same &#x60;type&#x60;, it is returned unchanged; a different &#x60;type&#x60; is a 409.  **Update** (with &#x60;id&#x60;): fields you omit keep their current value. Built-in categories (no organization) cannot be updated.  Requires an organization admin token. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.StatesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    StatesApi apiInstance = new StatesApi(defaultClient);
    StateCategorySet stateCategorySet = new StateCategorySet(); // StateCategorySet | 
    try {
      StateCategoryDetail result = apiInstance.setStateCategory(stateCategorySet);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatesApi#setStateCategory");
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
| **stateCategorySet** | [**StateCategorySet**](StateCategorySet.md)|  | |

### Return type

[**StateCategoryDetail**](StateCategoryDetail.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The state category after set. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |

<a id="setStateReasons"></a>
# **setStateReasons**
> List&lt;StateReasonDetail&gt; setStateReasons(stateReasonSet)

Create a state reason on one or more lines

Ensures the reason &#x60;name&#x60; exists under &#x60;category&#x60; on every line in &#x60;lines&#x60;. Lines that already have it are returned as they are; the rest are created in one transaction. The response has one entry per line, in request order, so calling this again is a no-op.  &#x60;category&#x60; may be given by &#x60;id&#x60; or &#x60;name&#x60; and must be one of your organization&#39;s own categories; Oden&#39;s built-in categories are read-only. &#x60;active&#x60; defaults to true and also re-activates an existing inactive reason (or deactivates with &#x60;false&#x60;).  Requires an organization admin token. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.StatesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    StatesApi apiInstance = new StatesApi(defaultClient);
    StateReasonSet stateReasonSet = new StateReasonSet(); // StateReasonSet | 
    try {
      List<StateReasonDetail> result = apiInstance.setStateReasons(stateReasonSet);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatesApi#setStateReasons");
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
| **stateReasonSet** | [**StateReasonSet**](StateReasonSet.md)|  | |

### Return type

[**List&lt;StateReasonDetail&gt;**](StateReasonDetail.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The state reason on each requested line. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |

