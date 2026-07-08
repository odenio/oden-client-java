# ScrapYieldDataApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteScrapYield**](ScrapYieldDataApi.md#deleteScrapYield) | **POST** /v2/scrap_yield/delete | Delete a scrap/yield record |
| [**searchScrapYield**](ScrapYieldDataApi.md#searchScrapYield) | **POST** /v2/scrap_yield/search | Search scrap/yield records |
| [**setScrapYield**](ScrapYieldDataApi.md#setScrapYield) | **POST** /v2/scrap_yield/set | Create or update a scrap/yield record |


<a id="deleteScrapYield"></a>
# **deleteScrapYield**
> deleteScrapYield(searchScrapYieldRequest)

Delete a scrap/yield record

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
    SearchScrapYieldRequest searchScrapYieldRequest = new SearchScrapYieldRequest(); // SearchScrapYieldRequest | 
    try {
      apiInstance.deleteScrapYield(searchScrapYieldRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScrapYieldDataApi#deleteScrapYield");
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
| **searchScrapYieldRequest** | [**SearchScrapYieldRequest**](SearchScrapYieldRequest.md)|  | |

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

<a id="searchScrapYield"></a>
# **searchScrapYield**
> searchScrapYield(searchScrapYieldRequest)

Search scrap/yield records

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
    SearchScrapYieldRequest searchScrapYieldRequest = new SearchScrapYieldRequest(); // SearchScrapYieldRequest | 
    try {
      apiInstance.searchScrapYield(searchScrapYieldRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScrapYieldDataApi#searchScrapYield");
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
| **searchScrapYieldRequest** | [**SearchScrapYieldRequest**](SearchScrapYieldRequest.md)|  | |

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

<a id="setScrapYield"></a>
# **setScrapYield**
> setScrapYield(setScrapYieldRequest, pattern)

Create or update a scrap/yield record

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
    SetScrapYieldRequest setScrapYieldRequest = new SetScrapYieldRequest(); // SetScrapYieldRequest | 
    String pattern = "exact"; // String | Optional pattern type to use for matching: - `exact` for exact match - `contains` for the string to be contained in the query - `regex` to match based on a regular expression 
    try {
      apiInstance.setScrapYield(setScrapYieldRequest, pattern);
    } catch (ApiException e) {
      System.err.println("Exception when calling ScrapYieldDataApi#setScrapYield");
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
| **setScrapYieldRequest** | [**SetScrapYieldRequest**](SetScrapYieldRequest.md)|  | |
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

