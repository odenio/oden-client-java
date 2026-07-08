# ScrapYieldDataApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v2ScrapYieldDeletePost**](ScrapYieldDataApi.md#v2ScrapYieldDeletePost) | **POST** /v2/scrap_yield/delete |  |
| [**v2ScrapYieldSearchPost**](ScrapYieldDataApi.md#v2ScrapYieldSearchPost) | **POST** /v2/scrap_yield/search |  |
| [**v2ScrapYieldSetPost**](ScrapYieldDataApi.md#v2ScrapYieldSetPost) | **POST** /v2/scrap_yield/set |  |


<a id="v2ScrapYieldDeletePost"></a>
# **v2ScrapYieldDeletePost**
> v2ScrapYieldDeletePost(v2ScrapYieldSearchPostRequest)



Deletes Scrap Yield record by ID and line 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.ScrapYieldDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    ScrapYieldDataApi apiInstance = new ScrapYieldDataApi(defaultClient);
    V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest = new V2ScrapYieldSearchPostRequest(); // V2ScrapYieldSearchPostRequest | 
    try {
      apiInstance.v2ScrapYieldDeletePost(v2ScrapYieldSearchPostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScrapYieldDataApi#v2ScrapYieldDeletePost");
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
| **v2ScrapYieldSearchPostRequest** | [**V2ScrapYieldSearchPostRequest**](V2ScrapYieldSearchPostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

<a id="v2ScrapYieldSearchPost"></a>
# **v2ScrapYieldSearchPost**
> v2ScrapYieldSearchPost(v2ScrapYieldSearchPostRequest)



Searches for scrap/yield records for a given Interval 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.ScrapYieldDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    ScrapYieldDataApi apiInstance = new ScrapYieldDataApi(defaultClient);
    V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest = new V2ScrapYieldSearchPostRequest(); // V2ScrapYieldSearchPostRequest | 
    try {
      apiInstance.v2ScrapYieldSearchPost(v2ScrapYieldSearchPostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScrapYieldDataApi#v2ScrapYieldSearchPost");
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
| **v2ScrapYieldSearchPostRequest** | [**V2ScrapYieldSearchPostRequest**](V2ScrapYieldSearchPostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

<a id="v2ScrapYieldSetPost"></a>
# **v2ScrapYieldSetPost**
> v2ScrapYieldSetPost(v2ScrapYieldSetPostRequest, pattern)



Uploads scrap or yield raw data.  Notes:  - If &#x60;id&#x60; is provided the existing Scrap/Yield record will be updated.  - If &#x60;id&#x60; is omitted a new Scrap/Yield record will be created.  - The scrap yield for an interval is an aggregate of all scrap yield raw data records associated with that interval     - Therefore, multiple scrap yield records can exist for a single interval, each contributing to the \&quot;aggregate\&quot; (i.e. sum total) scrap/yield of that interval  - Changing an aggregate can be done by either adding another record with an offset, or updating an existing record.     - Example: If you have 3 scrap records in an interval: 50 50 50 &#x3D; 150 and want to make the aggregate 100 for a given interval, either update one of the existing scrap records from 50 -&gt; 0, or add a new one with value -50  - Duplicate keys should be avoided, see Schema docs above for details. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.ScrapYieldDataApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    ScrapYieldDataApi apiInstance = new ScrapYieldDataApi(defaultClient);
    V2ScrapYieldSetPostRequest v2ScrapYieldSetPostRequest = new V2ScrapYieldSetPostRequest(); // V2ScrapYieldSetPostRequest | 
    String pattern = "exact"; // String | Optional pattern type to use for matching: - `exact` for exact match - `contains` for the string to be contained in the query - `regex` to match based on a regular expression 
    try {
      apiInstance.v2ScrapYieldSetPost(v2ScrapYieldSetPostRequest, pattern);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScrapYieldDataApi#v2ScrapYieldSetPost");
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
| **v2ScrapYieldSetPostRequest** | [**V2ScrapYieldSetPostRequest**](V2ScrapYieldSetPostRequest.md)|  | |
| **pattern** | **String**| Optional pattern type to use for matching: - &#x60;exact&#x60; for exact match - &#x60;contains&#x60; for the string to be contained in the query - &#x60;regex&#x60; to match based on a regular expression  | [optional] [default to exact] [enum: exact, contains, regex] |

### Return type

null (empty response body)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

