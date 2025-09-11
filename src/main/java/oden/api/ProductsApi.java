/*
 * Oden API
 * The Oden Private Partner API exposes RESTful API endpoints for clients to get, create and update data on the Oden Platform.  The API is based on the OpenAPI 3.0 specification. ### Current Version The URL, and host, for the current version is [https://api.oden.app/v2](https://api.oden.app/v2).  ### Oden's Data Model - **Organization**: This represents the Organization registered as an Oden customer. An organization has an associated collection of users, factories, lines, etc. This is the entity a specific authentication token is associated with. - **Asset** or **Machinegroup**: Assets, or machinegroups, are collections of machines, which may either be a **Factory** or a **Line**:   - **Factory**: Factories are collections of lines, representing a particular manufacturing location.     - **Line**: Lines are collections of machines, often representing a particular production line. Lines may also have **Products** mapped to them, indicating what is currently being manufactured by the specific line.       - **Machine**: Machines are the physical machines that make up a line - **Product**: Products capture what entities a manufacturer produces - **Interval**: An interval is a period of time that takes place on a manufacturing line and expresses some business concern. It's Oden's way of making metrics aggregatable, traceable, and relatable to a manufacturer.   - **Run**: A run is a production interval that labels a period of production as being work on some single product   - **Batch**: A batch is a production interval that represents a portion of a particular run   - **State**: A state is an interval that tracks the availability or utilization of a line     - **State Category**: A state category describes what state a line is in - such as ex. uptime, downtime, scrapping, etc.     - **State Reason**: A state reason describes why a line is in a particular state - such as \"maintenance\" being a reason for the category \"downtime\".   - **Custom**: A custom interval can track any other type of interval-based data a manufacturer might want to analyze. These are created on a per-factory basis. - **Target**: Targets specify values and upper/lower thresholds for metrics when specific products are running on specific lines - **Scrap/Yield**: Scrap/yield output specifies amount of produced product on a line during either a run or batch interval. Oden will categorize all output as either scrap or yield - as specified by the Scrap Yield Schema for a given factory. If you have other categories, like rework/blocked/off-grade, you must choose between categorizing those amounts as either good or bad production by specifying as scrap or yield. Clients may also add scrap codes (i.e., reasons) to a given Scrap Yield Data entry.   - **Scrap Code**: A scrap code is a code that explains the reason for a scrap/yield raw data input - such as \"Rework\" - **Quality Test**: Quality Tests are results of quality assurance tests done on site, and uploaded to Oden. They may be attached to a single Batch or Run. - **Metric**: Known in factories as \"tags\", metrics are the raw data that is collected by Oden from the machines and devices on the factory floor. - **Metric Group**: Metric groups are metrics that represent the same thing across different lines. They provide common display names for tags and allow labeling groups of tags as measuring key types of data like performance or production.  ### Best Practices Under the current implementation, the Oden API does not rate limit requests from clients.  However, rate limiting will be introduced in the near future and it is recommended that users design their API clients to not exceed a request rate of **one per second**.  ### Schema All v2 API access is over HTTPS and accessed from https://api.oden.app/v2 All data is sent and received as JSON.  API requests with duplicate keys will process only the data for the first key detected and ignore the rest, so it's not recommended. Batching multiple messages in this way is currently not possible.   - Example of duplicate key in JSON: {\"raw_data\":{\"scrap\":\"10\",\"scrap\":\"100\"}}  All timestamps are returned in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601#Times) format:    `YYYY-MM-DDTHH:MM:SSZ`  All durations are returned in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601#Times) format with the largest unit of time being the hour:     `PT[n]H[n]M[n]S`  All timestamps sent to the API in POST requests should be in ISO 8601 format. ### HTTP Verbs The ONLY HTTP call type (sometimes called *verb* or *method*) used within Oden's API is **POST**. There are three actions supported via a **POST**; call, search, set, and delete, together supporting CRUD operations;   - **search** requests are used to search for and *read* objects in the Oden Platform       - All Oden Objects may be uniquely identified by some combination of, or a single, parameter.         - Ex a `line` my be identified by either:           - `id`           - `name` AND `factory`   - **set** requests are used to *create* or *update* objects   - **delete** requests are used to *delete* objects. If a delete endpoint is not yet implemented for a given object, users may choose to update the values of a specific entity to null or 0 values.  ### URI Components All endpoints may be accessed with the URI pattern: `https://api.oden.app/v2/{object}/{action}`  Where:   - `object` is the name of the object being requested:        - `factory`, `quality_test`, `interval`, `line`, etc...   - `action` is the name of the action being requested     - `search` , `set` , `delete`  e.g. `https://api.oden.app/v2/factory/search`  # Authentication Clients can authenticate through the v2 API using a Token provided by Oden. Tokens are opaque strings similar to [Bearer](https://swagger.io/docs/specification/authentication/bearer-authentication/) tokens that the client must pass in the [HTTP Authorization request header](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Authorization) in every request. The syntax is as follows:  `Authorization: <type> <credentials>`  Where \\<type\\> is \"Token\" and \\<credentials\\> is the Token string. For example:  `Authorization: Token tokenStringProvidedByOden`  Authenticating with an invalid Token will return `401 Unauthorized Error`.  Authenticating with a Token that is not authorized to read requested data will return `403 Forbidden Error`.  Some endpoints may require requests to be broken out by machinegroup (i.e., line or factory) and the number of requests would scale accordingly. This multiplicity should be taken into consideration when deciding on the frequency the API client makes requests to the Oden endpoints.  To authenticate in this [UI](https://api.oden.app/v2/ui/), click the Lock icon, and copy/paste the token into the Authorize box. 
 *
 * The version of the OpenAPI document: 2.0.0
 * Contact: support@oden.io
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package oden.api;

import oden.ApiCallback;
import oden.ApiClient;
import oden.ApiException;
import oden.ApiResponse;
import oden.Configuration;
import oden.Pair;
import oden.ProgressRequestBody;
import oden.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import oden.model.GenericError;
import oden.model.Product;
import oden.model.V2LineSearchPost400Response;
import oden.model.V2LineSearchPost409Response;
import oden.model.V2LineSearchPost500Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public ProductsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ProductsApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for v2ProductDeletePost
     * @param product  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ProductDeletePostCall(@javax.annotation.Nonnull Product product, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = product;

        // create path and map variables
        String localVarPath = "/v2/product/delete";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIKeyAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call v2ProductDeletePostValidateBeforeCall(@javax.annotation.Nonnull Product product, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'product' is set
        if (product == null) {
            throw new ApiException("Missing the required parameter 'product' when calling v2ProductDeletePost(Async)");
        }

        return v2ProductDeletePostCall(product, _callback);

    }

    /**
     * 
     * Delete a Product by unique identifier: - &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  OR  - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  A deleted Product will not show up in Product searches or dropdowns, but associated Intervals will still exist. 
     * @param product  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public void v2ProductDeletePost(@javax.annotation.Nonnull Product product) throws ApiException {
        v2ProductDeletePostWithHttpInfo(product);
    }

    /**
     * 
     * Delete a Product by unique identifier: - &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  OR  - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  A deleted Product will not show up in Product searches or dropdowns, but associated Intervals will still exist. 
     * @param product  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> v2ProductDeletePostWithHttpInfo(@javax.annotation.Nonnull Product product) throws ApiException {
        okhttp3.Call localVarCall = v2ProductDeletePostValidateBeforeCall(product, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Delete a Product by unique identifier: - &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  OR  - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  A deleted Product will not show up in Product searches or dropdowns, but associated Intervals will still exist. 
     * @param product  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ProductDeletePostAsync(@javax.annotation.Nonnull Product product, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2ProductDeletePostValidateBeforeCall(product, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2ProductSearchPost
     * @param product  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of products. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ProductSearchPostCall(@javax.annotation.Nonnull Product product, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = product;

        // create path and map variables
        String localVarPath = "/v2/product/search";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIKeyAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call v2ProductSearchPostValidateBeforeCall(@javax.annotation.Nonnull Product product, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'product' is set
        if (product == null) {
            throw new ApiException("Missing the required parameter 'product' when calling v2ProductSearchPost(Async)");
        }

        return v2ProductSearchPostCall(product, _callback);

    }

    /**
     * 
     * Search for specific Product: - &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  OR  - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  May be used to confirm a Product exists or to get a Product &#x60;id&#x60; if &#x60;name&#x60; is known, or &#x60;name&#x60; if &#x60;id&#x60; is known. 
     * @param product  (required)
     * @return List&lt;Product&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of products. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public List<Product> v2ProductSearchPost(@javax.annotation.Nonnull Product product) throws ApiException {
        ApiResponse<List<Product>> localVarResp = v2ProductSearchPostWithHttpInfo(product);
        return localVarResp.getData();
    }

    /**
     * 
     * Search for specific Product: - &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  OR  - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  May be used to confirm a Product exists or to get a Product &#x60;id&#x60; if &#x60;name&#x60; is known, or &#x60;name&#x60; if &#x60;id&#x60; is known. 
     * @param product  (required)
     * @return ApiResponse&lt;List&lt;Product&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of products. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Product>> v2ProductSearchPostWithHttpInfo(@javax.annotation.Nonnull Product product) throws ApiException {
        okhttp3.Call localVarCall = v2ProductSearchPostValidateBeforeCall(product, null);
        Type localVarReturnType = new TypeToken<List<Product>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Search for specific Product: - &#x60;name&#x60; - &#x60;match: unique&#x60; or omit  OR  - &#x60;id&#x60; - &#x60;match: unique&#x60; or omit  May be used to confirm a Product exists or to get a Product &#x60;id&#x60; if &#x60;name&#x60; is known, or &#x60;name&#x60; if &#x60;id&#x60; is known. 
     * @param product  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of products. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ProductSearchPostAsync(@javax.annotation.Nonnull Product product, final ApiCallback<List<Product>> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2ProductSearchPostValidateBeforeCall(product, _callback);
        Type localVarReturnType = new TypeToken<List<Product>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2ProductSetPost
     * @param product  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ProductSetPostCall(@javax.annotation.Nonnull Product product, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = product;

        // create path and map variables
        String localVarPath = "/v2/product/set";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "APIKeyAuth" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call v2ProductSetPostValidateBeforeCall(@javax.annotation.Nonnull Product product, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'product' is set
        if (product == null) {
            throw new ApiException("Missing the required parameter 'product' when calling v2ProductSetPost(Async)");
        }

        return v2ProductSetPostCall(product, _callback);

    }

    /**
     * 
     * To **create** a new Product, include &#x60;name&#x60;, and omit &#x60;id&#x60; field.  To **update** an existing Product, include the &#x60;id&#x60; of the existing product the updated &#x60;name&#x60;. 
     * @param product  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public void v2ProductSetPost(@javax.annotation.Nonnull Product product) throws ApiException {
        v2ProductSetPostWithHttpInfo(product);
    }

    /**
     * 
     * To **create** a new Product, include &#x60;name&#x60;, and omit &#x60;id&#x60; field.  To **update** an existing Product, include the &#x60;id&#x60; of the existing product the updated &#x60;name&#x60;. 
     * @param product  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> v2ProductSetPostWithHttpInfo(@javax.annotation.Nonnull Product product) throws ApiException {
        okhttp3.Call localVarCall = v2ProductSetPostValidateBeforeCall(product, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * To **create** a new Product, include &#x60;name&#x60;, and omit &#x60;id&#x60; field.  To **update** an existing Product, include the &#x60;id&#x60; of the existing product the updated &#x60;name&#x60;. 
     * @param product  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ProductSetPostAsync(@javax.annotation.Nonnull Product product, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2ProductSetPostValidateBeforeCall(product, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
