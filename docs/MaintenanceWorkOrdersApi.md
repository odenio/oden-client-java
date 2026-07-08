# MaintenanceWorkOrdersApi

All URIs are relative to *https://api.oden.app*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**v2MaintenanceWorkOrderDeletePost**](MaintenanceWorkOrdersApi.md#v2MaintenanceWorkOrderDeletePost) | **POST** /v2/maintenance_work_order/delete |  |
| [**v2MaintenanceWorkOrderSearchPost**](MaintenanceWorkOrdersApi.md#v2MaintenanceWorkOrderSearchPost) | **POST** /v2/maintenance_work_order/search |  |
| [**v2MaintenanceWorkOrderSetPost**](MaintenanceWorkOrdersApi.md#v2MaintenanceWorkOrderSetPost) | **POST** /v2/maintenance_work_order/set |  |


<a id="v2MaintenanceWorkOrderDeletePost"></a>
# **v2MaintenanceWorkOrderDeletePost**
> List&lt;MaintenanceWorkOrder&gt; v2MaintenanceWorkOrderDeletePost(maintenanceWorkOrder)



Delete a Maintenance Work Order by unique identifier: - &#x60;id&#x60; OR &#x60;external_id&#x60; - &#x60;match: unique&#x60; or omit (only unique is supported) 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.MaintenanceWorkOrdersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    MaintenanceWorkOrdersApi apiInstance = new MaintenanceWorkOrdersApi(defaultClient);
    MaintenanceWorkOrder maintenanceWorkOrder = new MaintenanceWorkOrder(); // MaintenanceWorkOrder | 
    try {
      List<MaintenanceWorkOrder> result = apiInstance.v2MaintenanceWorkOrderDeletePost(maintenanceWorkOrder);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MaintenanceWorkOrdersApi#v2MaintenanceWorkOrderDeletePost");
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
| **maintenanceWorkOrder** | [**MaintenanceWorkOrder**](MaintenanceWorkOrder.md)|  | |

### Return type

[**List&lt;MaintenanceWorkOrder&gt;**](MaintenanceWorkOrder.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list containing the deleted maintenance work order. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |

<a id="v2MaintenanceWorkOrderSearchPost"></a>
# **v2MaintenanceWorkOrderSearchPost**
> List&lt;MaintenanceWorkOrder&gt; v2MaintenanceWorkOrderSearchPost(v2MaintenanceWorkOrderSearchPostRequest)



Search for Maintenance Work Orders by: - &#x60;id&#x60; - &#x60;external_id&#x60; - &#x60;line_id&#x60; with required &#x60;start_time&#x60; and &#x60;end_time&#x60; filters 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.MaintenanceWorkOrdersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    MaintenanceWorkOrdersApi apiInstance = new MaintenanceWorkOrdersApi(defaultClient);
    V2MaintenanceWorkOrderSearchPostRequest v2MaintenanceWorkOrderSearchPostRequest = new V2MaintenanceWorkOrderSearchPostRequest(); // V2MaintenanceWorkOrderSearchPostRequest | 
    try {
      List<MaintenanceWorkOrder> result = apiInstance.v2MaintenanceWorkOrderSearchPost(v2MaintenanceWorkOrderSearchPostRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MaintenanceWorkOrdersApi#v2MaintenanceWorkOrderSearchPost");
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
| **v2MaintenanceWorkOrderSearchPostRequest** | [**V2MaintenanceWorkOrderSearchPostRequest**](V2MaintenanceWorkOrderSearchPostRequest.md)|  | |

### Return type

[**List&lt;MaintenanceWorkOrder&gt;**](MaintenanceWorkOrder.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of maintenance work orders. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |

<a id="v2MaintenanceWorkOrderSetPost"></a>
# **v2MaintenanceWorkOrderSetPost**
> MaintenanceWorkOrder v2MaintenanceWorkOrderSetPost(maintenanceWorkOrder)



Create or update a Maintenance Work Order.  To **create** a new Maintenance Work Order: - Include &#x60;name&#x60; and &#x60;line&#x60;, &#x60;external_id&#x60;, &#x60;started_at&#x60; (required) - Omit &#x60;id&#x60; field - include &#x60;completed_at&#x60;, &#x60;description&#x60;, &#x60;metadata&#x60;  To **update** an existing Maintenance Work Order: - Include the &#x60;id&#x60; of the existing work order - Include any fields to update  NOTE: Any fields not included in an update request will be left unchanged. 

### Example
```java
// Import classes:
import oden.ApiClient;
import oden.ApiException;
import oden.Configuration;
import oden.auth.*;
import oden.models.*;
import oden.api.MaintenanceWorkOrdersApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.oden.app");
    
    // Configure API key authorization: APIKeyAuth
    ApiKeyAuth APIKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyAuth");
    APIKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyAuth.setApiKeyPrefix("Token");

    MaintenanceWorkOrdersApi apiInstance = new MaintenanceWorkOrdersApi(defaultClient);
    MaintenanceWorkOrder maintenanceWorkOrder = new MaintenanceWorkOrder(); // MaintenanceWorkOrder | 
    try {
      MaintenanceWorkOrder result = apiInstance.v2MaintenanceWorkOrderSetPost(maintenanceWorkOrder);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MaintenanceWorkOrdersApi#v2MaintenanceWorkOrderSetPost");
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
| **maintenanceWorkOrder** | [**MaintenanceWorkOrder**](MaintenanceWorkOrder.md)|  | |

### Return type

[**MaintenanceWorkOrder**](MaintenanceWorkOrder.md)

### Authorization

[APIKeyAuth](../README.md#APIKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list containing the created or updated maintenance work order. |  -  |
| **400** | An error occurred regarding one of the input parameters |  -  |
| **401** | User has provided either no credentials or invalid credentials |  -  |
| **403** | User has provided valid credentials but is not authorized to access the entity  |  -  |
| **404** | Entity not found |  -  |
| **409** | A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  |  -  |
| **500** | An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  |  -  |
| **501** | Endpoint is not yet implemented |  -  |

