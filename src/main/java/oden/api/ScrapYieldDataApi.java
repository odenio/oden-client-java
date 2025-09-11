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
import oden.model.V2LineSearchPost400Response;
import oden.model.V2LineSearchPost409Response;
import oden.model.V2LineSearchPost500Response;
import oden.model.V2ScrapYieldSearchPostRequest;
import oden.model.V2ScrapYieldSetPostRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapYieldDataApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public ScrapYieldDataApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ScrapYieldDataApi(ApiClient apiClient) {
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
     * Build call for v2ScrapYieldDeletePost
     * @param v2ScrapYieldSearchPostRequest  (required)
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
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ScrapYieldDeletePostCall(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = v2ScrapYieldSearchPostRequest;

        // create path and map variables
        String localVarPath = "/v2/scrap_yield/delete";

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
    private okhttp3.Call v2ScrapYieldDeletePostValidateBeforeCall(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'v2ScrapYieldSearchPostRequest' is set
        if (v2ScrapYieldSearchPostRequest == null) {
            throw new ApiException("Missing the required parameter 'v2ScrapYieldSearchPostRequest' when calling v2ScrapYieldDeletePost(Async)");
        }

        return v2ScrapYieldDeletePostCall(v2ScrapYieldSearchPostRequest, _callback);

    }

    /**
     * 
     * Deletes Scrap Yield record by ID and line 
     * @param v2ScrapYieldSearchPostRequest  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public void v2ScrapYieldDeletePost(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest) throws ApiException {
        v2ScrapYieldDeletePostWithHttpInfo(v2ScrapYieldSearchPostRequest);
    }

    /**
     * 
     * Deletes Scrap Yield record by ID and line 
     * @param v2ScrapYieldSearchPostRequest  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> v2ScrapYieldDeletePostWithHttpInfo(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest) throws ApiException {
        okhttp3.Call localVarCall = v2ScrapYieldDeletePostValidateBeforeCall(v2ScrapYieldSearchPostRequest, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Deletes Scrap Yield record by ID and line 
     * @param v2ScrapYieldSearchPostRequest  (required)
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
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ScrapYieldDeletePostAsync(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2ScrapYieldDeletePostValidateBeforeCall(v2ScrapYieldSearchPostRequest, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2ScrapYieldSearchPost
     * @param v2ScrapYieldSearchPostRequest  (required)
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
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ScrapYieldSearchPostCall(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = v2ScrapYieldSearchPostRequest;

        // create path and map variables
        String localVarPath = "/v2/scrap_yield/search";

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
    private okhttp3.Call v2ScrapYieldSearchPostValidateBeforeCall(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'v2ScrapYieldSearchPostRequest' is set
        if (v2ScrapYieldSearchPostRequest == null) {
            throw new ApiException("Missing the required parameter 'v2ScrapYieldSearchPostRequest' when calling v2ScrapYieldSearchPost(Async)");
        }

        return v2ScrapYieldSearchPostCall(v2ScrapYieldSearchPostRequest, _callback);

    }

    /**
     * 
     * Searches for scrap/yield records for a given Interval 
     * @param v2ScrapYieldSearchPostRequest  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public void v2ScrapYieldSearchPost(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest) throws ApiException {
        v2ScrapYieldSearchPostWithHttpInfo(v2ScrapYieldSearchPostRequest);
    }

    /**
     * 
     * Searches for scrap/yield records for a given Interval 
     * @param v2ScrapYieldSearchPostRequest  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> v2ScrapYieldSearchPostWithHttpInfo(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest) throws ApiException {
        okhttp3.Call localVarCall = v2ScrapYieldSearchPostValidateBeforeCall(v2ScrapYieldSearchPostRequest, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Searches for scrap/yield records for a given Interval 
     * @param v2ScrapYieldSearchPostRequest  (required)
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
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ScrapYieldSearchPostAsync(@javax.annotation.Nonnull V2ScrapYieldSearchPostRequest v2ScrapYieldSearchPostRequest, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2ScrapYieldSearchPostValidateBeforeCall(v2ScrapYieldSearchPostRequest, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2ScrapYieldSetPost
     * @param v2ScrapYieldSetPostRequest  (required)
     * @param pattern Optional pattern type to use for matching: - &#x60;exact&#x60; for exact match - &#x60;contains&#x60; for the string to be contained in the query - &#x60;regex&#x60; to match based on a regular expression  (optional, default to exact)
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
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ScrapYieldSetPostCall(@javax.annotation.Nonnull V2ScrapYieldSetPostRequest v2ScrapYieldSetPostRequest, @javax.annotation.Nullable String pattern, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = v2ScrapYieldSetPostRequest;

        // create path and map variables
        String localVarPath = "/v2/scrap_yield/set";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (pattern != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("pattern", pattern));
        }

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
    private okhttp3.Call v2ScrapYieldSetPostValidateBeforeCall(@javax.annotation.Nonnull V2ScrapYieldSetPostRequest v2ScrapYieldSetPostRequest, @javax.annotation.Nullable String pattern, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'v2ScrapYieldSetPostRequest' is set
        if (v2ScrapYieldSetPostRequest == null) {
            throw new ApiException("Missing the required parameter 'v2ScrapYieldSetPostRequest' when calling v2ScrapYieldSetPost(Async)");
        }

        return v2ScrapYieldSetPostCall(v2ScrapYieldSetPostRequest, pattern, _callback);

    }

    /**
     * 
     * Uploads scrap or yield raw data.  Notes:  - If &#x60;id&#x60; is provided the existing Scrap/Yield record will be updated.  - If &#x60;id&#x60; is omitted a new Scrap/Yield record will be created.  - The scrap yield for an interval is an aggregate of all scrap yield raw data records associated with that interval     - Therefore, multiple scrap yield records can exist for a single interval, each contributing to the \&quot;aggregate\&quot; (i.e. sum total) scrap/yield of that interval  - Changing an aggregate can be done by either adding another record with an offset, or updating an existing record.     - Example: If you have 3 scrap records in an interval: 50 50 50 &#x3D; 150 and want to make the aggregate 100 for a given interval, either update one of the existing scrap records from 50 -&gt; 0, or add a new one with value -50  - Duplicate keys should be avoided, see Schema docs above for details. 
     * @param v2ScrapYieldSetPostRequest  (required)
     * @param pattern Optional pattern type to use for matching: - &#x60;exact&#x60; for exact match - &#x60;contains&#x60; for the string to be contained in the query - &#x60;regex&#x60; to match based on a regular expression  (optional, default to exact)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public void v2ScrapYieldSetPost(@javax.annotation.Nonnull V2ScrapYieldSetPostRequest v2ScrapYieldSetPostRequest, @javax.annotation.Nullable String pattern) throws ApiException {
        v2ScrapYieldSetPostWithHttpInfo(v2ScrapYieldSetPostRequest, pattern);
    }

    /**
     * 
     * Uploads scrap or yield raw data.  Notes:  - If &#x60;id&#x60; is provided the existing Scrap/Yield record will be updated.  - If &#x60;id&#x60; is omitted a new Scrap/Yield record will be created.  - The scrap yield for an interval is an aggregate of all scrap yield raw data records associated with that interval     - Therefore, multiple scrap yield records can exist for a single interval, each contributing to the \&quot;aggregate\&quot; (i.e. sum total) scrap/yield of that interval  - Changing an aggregate can be done by either adding another record with an offset, or updating an existing record.     - Example: If you have 3 scrap records in an interval: 50 50 50 &#x3D; 150 and want to make the aggregate 100 for a given interval, either update one of the existing scrap records from 50 -&gt; 0, or add a new one with value -50  - Duplicate keys should be avoided, see Schema docs above for details. 
     * @param v2ScrapYieldSetPostRequest  (required)
     * @param pattern Optional pattern type to use for matching: - &#x60;exact&#x60; for exact match - &#x60;contains&#x60; for the string to be contained in the query - &#x60;regex&#x60; to match based on a regular expression  (optional, default to exact)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful response </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> v2ScrapYieldSetPostWithHttpInfo(@javax.annotation.Nonnull V2ScrapYieldSetPostRequest v2ScrapYieldSetPostRequest, @javax.annotation.Nullable String pattern) throws ApiException {
        okhttp3.Call localVarCall = v2ScrapYieldSetPostValidateBeforeCall(v2ScrapYieldSetPostRequest, pattern, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     *  (asynchronously)
     * Uploads scrap or yield raw data.  Notes:  - If &#x60;id&#x60; is provided the existing Scrap/Yield record will be updated.  - If &#x60;id&#x60; is omitted a new Scrap/Yield record will be created.  - The scrap yield for an interval is an aggregate of all scrap yield raw data records associated with that interval     - Therefore, multiple scrap yield records can exist for a single interval, each contributing to the \&quot;aggregate\&quot; (i.e. sum total) scrap/yield of that interval  - Changing an aggregate can be done by either adding another record with an offset, or updating an existing record.     - Example: If you have 3 scrap records in an interval: 50 50 50 &#x3D; 150 and want to make the aggregate 100 for a given interval, either update one of the existing scrap records from 50 -&gt; 0, or add a new one with value -50  - Duplicate keys should be avoided, see Schema docs above for details. 
     * @param v2ScrapYieldSetPostRequest  (required)
     * @param pattern Optional pattern type to use for matching: - &#x60;exact&#x60; for exact match - &#x60;contains&#x60; for the string to be contained in the query - &#x60;regex&#x60; to match based on a regular expression  (optional, default to exact)
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
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2ScrapYieldSetPostAsync(@javax.annotation.Nonnull V2ScrapYieldSetPostRequest v2ScrapYieldSetPostRequest, @javax.annotation.Nullable String pattern, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2ScrapYieldSetPostValidateBeforeCall(v2ScrapYieldSetPostRequest, pattern, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
