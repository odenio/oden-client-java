# ProductMappingsApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**searchProductMappings**](ProductMappingsApi.md#searchProductMappings) | **POST** /v2/product_mapping/search | Search product-to-line mappings |
| [**setProductMapping**](ProductMappingsApi.md#setProductMapping) | **POST** /v2/product_mapping/set | Map a product to a line |


<a id="searchProductMappings"></a>
# **searchProductMappings**
> List&lt;ProductMapping&gt; searchProductMappings(productMapping)

Search product-to-line mappings

Searches for Product Mappings.  May be used to confirm a Product Mapping exists.  Much like &#x60;product/search&#x60;, may be used to get &#x60;name&#x60;s of line or product from &#x60;id&#x60;s, or &#x60;id&#x60;s from &#x60;name&#x60;s. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.ProductMappingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    ProductMappingsApi apiInstance = new ProductMappingsApi(defaultClient);
    ProductMapping productMapping = new ProductMapping(); // ProductMapping | 
    try {
      List<ProductMapping> result = apiInstance.searchProductMappings(productMapping);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductMappingsApi#searchProductMappings");
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
| **productMapping** | [**ProductMapping**](ProductMapping.md)|  | |

### Return type

[**List&lt;ProductMapping&gt;**](ProductMapping.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of product mappings. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |

<a id="setProductMapping"></a>
# **setProductMapping**
> setProductMapping(productMapping)

Map a product to a line

Map a Product to a Line - implying this Line can produce, or is producing this Product.  If the supplied Product doesn&#39;t exist, it will be created. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.ProductMappingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    ProductMappingsApi apiInstance = new ProductMappingsApi(defaultClient);
    ProductMapping productMapping = new ProductMapping(); // ProductMapping | 
    try {
      apiInstance.setProductMapping(productMapping);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductMappingsApi#setProductMapping");
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
| **productMapping** | [**ProductMapping**](ProductMapping.md)|  | |

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
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **404** | Entity not found |  -  |

