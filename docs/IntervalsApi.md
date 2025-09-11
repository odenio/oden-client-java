# IntervalsApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v2IntervalDeletePost**](IntervalsApi.md#v2IntervalDeletePost) | **POST** /v2/interval/delete |  |
| [**v2IntervalSearchPost**](IntervalsApi.md#v2IntervalSearchPost) | **POST** /v2/interval/search |  |
| [**v2IntervalSetPost**](IntervalsApi.md#v2IntervalSetPost) | **POST** /v2/interval/set |  |
| [**v2IntervalTypeSearchPost**](IntervalsApi.md#v2IntervalTypeSearchPost) | **POST** /v2/interval_type/search |  |
| [**v2IntervalsDeletePost**](IntervalsApi.md#v2IntervalsDeletePost) | **POST** /v2/intervals/delete |  |
| [**v2IntervalsSetPost**](IntervalsApi.md#v2IntervalsSetPost) | **POST** /v2/intervals/set |  |


<a id="v2IntervalDeletePost"></a>
# **v2IntervalDeletePost**
> List&lt;Interval&gt; v2IntervalDeletePost(interval)



Delete an interval by &#x60;type&#x60;, &#x60;line&#x60;, and &#x60;id&#x60;  **Note:** The &#x60;id&#x60; must be obtained from either: - The response when creating an interval via &#x60;/v2/interval/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60;  The examples below use placeholder IDs - replace with actual IDs from your system. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
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

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **interval** | [**Interval**](Interval.md)|  | |

### Return type

[**List&lt;Interval&gt;**](Interval.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list containing the deleted interval. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

<a id="v2IntervalSearchPost"></a>
# **v2IntervalSearchPost**
> List&lt;Interval&gt; v2IntervalSearchPost(interval)



Searches for intervals by &#x60;type&#x60;, &#x60;line&#x60; and other optional parameters:  - &#x60;id&#x60; (for a specific Interval) - &#x60;match: unique&#x60; or omit  OR  - &#x60;match : last&#x60; for the most recent Interval of the given type on the given line  OR  - &#x60;start_time&#x60; and &#x60;end_time&#x60; (for a range of Intervals over a period of time) - &#x60;match: all&#x60; for more than one result  OR  - match all intervals for all lines in a given factory  AND/OR  - &#x60;name&#x60; (only for Intervals with a matching name) 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
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
      List<Interval> result = apiInstance.v2IntervalSearchPost(interval);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IntervalsApi#v2IntervalSearchPost");
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
| **interval** | [**Interval**](Interval.md)|  | |

### Return type

[**List&lt;Interval&gt;**](Interval.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of intervals. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

<a id="v2IntervalSetPost"></a>
# **v2IntervalSetPost**
> Interval v2IntervalSetPost(interval)



Create or update an Interval.  Must include &#x60;line&#x60; and &#x60;type&#x60;. &#x60;match&#x60; must be omitted, &#x60;unique&#x60; or &#x60;last&#x60;   - If &#x60;id&#x60; is not supplied, a new Interval will be created.   - If &#x60;id&#x60; is supplied, existing Interval will be updated. This interval&#39;s start time can be modified using &#x60;start_time&#x60; field.  To update a specific interval supply the &#x60;id&#x60; of that interval.  If the interval exists with all the same parameters nothing is done.  To update the most recent Interval of a given &#x60;type&#x60; on a &#x60;line&#x60; one may use &#x60;match: last&#x60; and omit &#x60;id&#x60;  For &#x60;RUN&#x60; type: if &#x60;product&#x60; and/or &#x60;product_mapping&#x60; does not exist a new one will be created. Further a &#x60;target&#x60; may be set by adding a &#x60;target&#x60; to the metadata field. The &#x60;line&#x60; and &#x60;product&#x60; for this target will be the same as the interval.  Please see examples for more specific information. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
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
      Interval result = apiInstance.v2IntervalSetPost(interval);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IntervalsApi#v2IntervalSetPost");
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
| **interval** | [**Interval**](Interval.md)|  | |

### Return type

[**Interval**](Interval.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Updated interval. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |

<a id="v2IntervalTypeSearchPost"></a>
# **v2IntervalTypeSearchPost**
> List&lt;IntervalType&gt; v2IntervalTypeSearchPost(intervalType)



Search for Interval Types by &#x60;name&#x60;, &#x60;id&#x60;, &#x60;factory&#x60; or just &#x60;match: all&#x60; to return all Interval Types associated with the your organization. Basic Interval Types -- &#x60;RUN&#x60;, &#x60;BATCH&#x60;, and &#x60;STATE&#x60; -- are associated with every factory in Oden&#39;s system. Custom Interval Types may be created by users, are set on a per factory basis, and may only describe Intervals on Lines associated with that Factory. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
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
    IntervalType intervalType = new IntervalType(); // IntervalType | 
    try {
      List<IntervalType> result = apiInstance.v2IntervalTypeSearchPost(intervalType);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IntervalsApi#v2IntervalTypeSearchPost");
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
| **intervalType** | [**IntervalType**](IntervalType.md)|  | |

### Return type

[**List&lt;IntervalType&gt;**](IntervalType.md)

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
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

<a id="v2IntervalsDeletePost"></a>
# **v2IntervalsDeletePost**
> V2IntervalsDeletePost200Response v2IntervalsDeletePost(intervalBulkDelete)



Delete a group of intervals by a single &#x60;type&#x60; and a single &#x60;line&#x60;, between &#x60;start_time&#x60; and &#x60;end_time&#x60;. Returns a list of intervals that were not deleted, and the number of intervals deleted.  Limitations: - Cannot exceed 15,000 intervals per request, or 30 days worth of data. - Currently does not support \&quot;batch\&quot; or \&quot;run\&quot; interval types. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
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
    IntervalBulkDelete intervalBulkDelete = new IntervalBulkDelete(); // IntervalBulkDelete | 
    try {
      V2IntervalsDeletePost200Response result = apiInstance.v2IntervalsDeletePost(intervalBulkDelete);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IntervalsApi#v2IntervalsDeletePost");
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
| **intervalBulkDelete** | [**IntervalBulkDelete**](IntervalBulkDelete.md)|  | |

### Return type

[**V2IntervalsDeletePost200Response**](V2IntervalsDeletePost200Response.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list containing any intervals found but not deleted. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |

<a id="v2IntervalsSetPost"></a>
# **v2IntervalsSetPost**
> V2IntervalsSetPost200Response v2IntervalsSetPost(intervalBulkCreate)



Create (Does not update) a group of custom intervals, for the same &#x60;type&#x60; and &#x60;line&#x60;. Line and type do not need to be included in each individual interval, just once at the top level.  Limitations: - Cannot excees 2500 intervals per request. - Will not write over other intervals - Does not support \&quot;batch\&quot;, \&quot;run\&quot;, or \&quot;state\&quot; interval types. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
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
    IntervalBulkCreate intervalBulkCreate = new IntervalBulkCreate(); // IntervalBulkCreate | 
    try {
      V2IntervalsSetPost200Response result = apiInstance.v2IntervalsSetPost(intervalBulkCreate);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IntervalsApi#v2IntervalsSetPost");
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
| **intervalBulkCreate** | [**IntervalBulkCreate**](IntervalBulkCreate.md)|  | |

### Return type

[**V2IntervalsSetPost200Response**](V2IntervalsSetPost200Response.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list containing any intervals found but not created. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |

