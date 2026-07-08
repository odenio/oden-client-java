# QualityTestApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**bulkDeleteQualityTests**](QualityTestApi.md#bulkDeleteQualityTests) | **POST** /v2/quality_tests/delete | Delete multiple quality tests |
| [**deleteQualityTest**](QualityTestApi.md#deleteQualityTest) | **POST** /v2/quality_test/delete | Delete a quality test |
| [**searchQualitySchemas**](QualityTestApi.md#searchQualitySchemas) | **POST** /v2/quality_schema/search | Search quality schemas for a factory |
| [**searchQualityTests**](QualityTestApi.md#searchQualityTests) | **POST** /v2/quality_test/search | Search quality tests |
| [**setQualityTest**](QualityTestApi.md#setQualityTest) | **POST** /v2/quality_test/set | Create or update a quality test result |


<a id="bulkDeleteQualityTests"></a>
# **bulkDeleteQualityTests**
> bulkDeleteQualityTests(bulkDeleteQualityTestsRequest)

Delete multiple quality tests

Bulk deletes quality tests, either: - All quality tests on a given &#x60;line&#x60; whose &#x60;timsetamp&#x60; is between &#x60;start_time&#x60; and &#x60;end_time&#x60; OR - All quality tests whose &#x60;id&#x60; is supplied  It will do one or the other, with a bias for &#x60;id&#x60;&#39;s if both are supplied. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.QualityTestApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    QualityTestApi apiInstance = new QualityTestApi(defaultClient);
    BulkDeleteQualityTestsRequest bulkDeleteQualityTestsRequest = new BulkDeleteQualityTestsRequest(); // BulkDeleteQualityTestsRequest | 
    try {
      apiInstance.bulkDeleteQualityTests(bulkDeleteQualityTestsRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#bulkDeleteQualityTests");
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
| **bulkDeleteQualityTestsRequest** | [**BulkDeleteQualityTestsRequest**](BulkDeleteQualityTestsRequest.md)|  | |

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

<a id="deleteQualityTest"></a>
# **deleteQualityTest**
> deleteQualityTest(qualityTest)

Delete a quality test

Searches for uniqueQuality Test by:  - &#x60;id&#x60;  OR  - &#x60;interval&#x60; (of type &#x60;RUN&#x60; or &#x60;BATCH&#x60;)*  *This only work if there is a single quality test record for the interval. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.QualityTestApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    QualityTestApi apiInstance = new QualityTestApi(defaultClient);
    QualityTest qualityTest = new QualityTest(); // QualityTest | 
    try {
      apiInstance.deleteQualityTest(qualityTest);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#deleteQualityTest");
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
| **qualityTest** | [**QualityTest**](QualityTest.md)|  | |

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

<a id="searchQualitySchemas"></a>
# **searchQualitySchemas**
> searchQualitySchemas(qualitySchema)

Search quality schemas for a factory

Searches for Quality Schema[s] by:  - &#x60;factory&#x60; 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.QualityTestApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    QualityTestApi apiInstance = new QualityTestApi(defaultClient);
    QualitySchema qualitySchema = new QualitySchema(); // QualitySchema | 
    try {
      apiInstance.searchQualitySchemas(qualitySchema);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#searchQualitySchemas");
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
| **qualitySchema** | [**QualitySchema**](QualitySchema.md)|  | |

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

<a id="searchQualityTests"></a>
# **searchQualityTests**
> searchQualityTests(qualityTest)

Search quality tests

Searches for Quality Test[s] by:  - &#x60;id&#x60;  OR  - &#x60;interval&#x60; (of type &#x60;RUN&#x60; or &#x60;BATCH&#x60;) 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.QualityTestApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    QualityTestApi apiInstance = new QualityTestApi(defaultClient);
    QualityTest qualityTest = new QualityTest(); // QualityTest | 
    try {
      apiInstance.searchQualityTests(qualityTest);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#searchQualityTests");
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
| **qualityTest** | [**QualityTest**](QualityTest.md)|  | |

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

<a id="setQualityTest"></a>
# **setQualityTest**
> setQualityTest(qualityTest)

Create or update a quality test result

Create or update a Quality Test record: - To update &#x60;id&#x60; must be supplied. Only the supplied fields will be updated, the rest will remain unchanged. - If &#x60;id&#x60; is not supplied, a new &#x60;quality_test_record&#x60; will be created. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.QualityTestApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    QualityTestApi apiInstance = new QualityTestApi(defaultClient);
    QualityTest qualityTest = new QualityTest(); // QualityTest | 
    try {
      apiInstance.setQualityTest(qualityTest);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#setQualityTest");
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
| **qualityTest** | [**QualityTest**](QualityTest.md)|  | |

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

