# QualityTestApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v2QualitySchemaSearchPost**](QualityTestApi.md#v2QualitySchemaSearchPost) | **POST** /v2/quality_schema/search |  |
| [**v2QualityTestDeletePost**](QualityTestApi.md#v2QualityTestDeletePost) | **POST** /v2/quality_test/delete |  |
| [**v2QualityTestSearchPost**](QualityTestApi.md#v2QualityTestSearchPost) | **POST** /v2/quality_test/search |  |
| [**v2QualityTestSetPost**](QualityTestApi.md#v2QualityTestSetPost) | **POST** /v2/quality_test/set |  |
| [**v2QualityTestsDeletePost**](QualityTestApi.md#v2QualityTestsDeletePost) | **POST** /v2/quality_tests/delete |  |


<a id="v2QualitySchemaSearchPost"></a>
# **v2QualitySchemaSearchPost**
> v2QualitySchemaSearchPost(qualitySchema)



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
      apiInstance.v2QualitySchemaSearchPost(qualitySchema);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#v2QualitySchemaSearchPost");
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

<a id="v2QualityTestDeletePost"></a>
# **v2QualityTestDeletePost**
> v2QualityTestDeletePost(qualityTest)



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
      apiInstance.v2QualityTestDeletePost(qualityTest);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#v2QualityTestDeletePost");
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

<a id="v2QualityTestSearchPost"></a>
# **v2QualityTestSearchPost**
> v2QualityTestSearchPost(qualityTest)



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
      apiInstance.v2QualityTestSearchPost(qualityTest);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#v2QualityTestSearchPost");
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

<a id="v2QualityTestSetPost"></a>
# **v2QualityTestSetPost**
> v2QualityTestSetPost(qualityTest)



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
      apiInstance.v2QualityTestSetPost(qualityTest);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#v2QualityTestSetPost");
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

<a id="v2QualityTestsDeletePost"></a>
# **v2QualityTestsDeletePost**
> v2QualityTestsDeletePost(v2QualityTestsDeletePostRequest)



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
    V2QualityTestsDeletePostRequest v2QualityTestsDeletePostRequest = new V2QualityTestsDeletePostRequest(); // V2QualityTestsDeletePostRequest | 
    try {
      apiInstance.v2QualityTestsDeletePost(v2QualityTestsDeletePostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling QualityTestApi#v2QualityTestsDeletePost");
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
| **v2QualityTestsDeletePostRequest** | [**V2QualityTestsDeletePostRequest**](V2QualityTestsDeletePostRequest.md)|  | |

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

