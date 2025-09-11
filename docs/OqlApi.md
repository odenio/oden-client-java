# OqlApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v2OqlQueryPost**](OqlApi.md#v2OqlQueryPost) | **POST** /v2/oql/query |  |


<a id="v2OqlQueryPost"></a>
# **v2OqlQueryPost**
> v2OqlQueryPost(oqLQuery, format)



Run an OQL (Oden Query Language) query.  For reference on writing OQL queries, see:  [https://platform.oden.app/knowledge/how-do-i-write-queries-in-oden-query-language-oql](https://platform.oden.app/knowledge/how-do-i-write-queries-in-oden-query-language-oql) 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.OqlApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    OqlApi apiInstance = new OqlApi(defaultClient);
    OQLQuery oqLQuery = new OQLQuery(); // OQLQuery | 
    String format = "json"; // String | Format of the response. Can be `json`, `jsonextended` or `csv`. If unspecified, defaults to `jsonextended`. 
    try {
      apiInstance.v2OqlQueryPost(oqLQuery, format);
    } catch (ApiException e) {
      System.err.println("Exception when calling OqlApi#v2OqlQueryPost");
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
| **oqLQuery** | [**OQLQuery**](OQLQuery.md)|  | |
| **format** | **String**| Format of the response. Can be &#x60;json&#x60;, &#x60;jsonextended&#x60; or &#x60;csv&#x60;. If unspecified, defaults to &#x60;jsonextended&#x60;.  | [optional] [default to json] [enum: json, jsonextended, csv] |

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
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **413** | Too many requests |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |

