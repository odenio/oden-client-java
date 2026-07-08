# ProductAttributesApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v2ProductAttributeSearchPost**](ProductAttributesApi.md#v2ProductAttributeSearchPost) | **POST** /v2/product_attribute/search |  |
| [**v2ProductAttributeSetPost**](ProductAttributesApi.md#v2ProductAttributeSetPost) | **POST** /v2/product_attribute/set |  |


<a id="v2ProductAttributeSearchPost"></a>
# **v2ProductAttributeSearchPost**
> List&lt;ProductAttribute&gt; v2ProductAttributeSearchPost(productAttribute)



Searches for Product Attributes  Product attributes may be searched by ID, product, or, display_name - in that order.  If an ID is supplied, it will be used to search for a Product Attribute, and display_name, product will be ignored.  If a product is supplied (and no ID), it will be used to search for a Product Attribute, and display_name will be ignored. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.ProductAttributesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    ProductAttributesApi apiInstance = new ProductAttributesApi(defaultClient);
    ProductAttribute productAttribute = new ProductAttribute(); // ProductAttribute | 
    try {
      List<ProductAttribute> result = apiInstance.v2ProductAttributeSearchPost(productAttribute);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductAttributesApi#v2ProductAttributeSearchPost");
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
| **productAttribute** | [**ProductAttribute**](ProductAttribute.md)|  | |

### Return type

[**List&lt;ProductAttribute&gt;**](ProductAttribute.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of product attributes - potentially with products and values. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |

<a id="v2ProductAttributeSetPost"></a>
# **v2ProductAttributeSetPost**
> List&lt;ProductAttribute&gt; v2ProductAttributeSetPost(productAttribute)



Set a Product Attribute for a Product.  If the supplied Product Attribute doesn&#39;t exist, it will be created.  If the supplied Product Attribute Value doesn&#39;t exist, it will be created.  If the supplied Product Attribute Value is already set for the Product, it will be updated.  If the supplied Product Attribute Value is not set for the Product, it will be added.  Supplied Product must exist already. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.ProductAttributesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    ProductAttributesApi apiInstance = new ProductAttributesApi(defaultClient);
    ProductAttribute productAttribute = new ProductAttribute(); // ProductAttribute | 
    try {
      List<ProductAttribute> result = apiInstance.v2ProductAttributeSetPost(productAttribute);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProductAttributesApi#v2ProductAttributeSetPost");
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
| **productAttribute** | [**ProductAttribute**](ProductAttribute.md)|  | |

### Return type

[**List&lt;ProductAttribute&gt;**](ProductAttribute.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of product attributes - potentially with products and values. |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |

