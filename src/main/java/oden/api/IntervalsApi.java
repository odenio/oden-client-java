/*
 * Oden API
 * The Oden Private Partner API exposes RESTful API endpoints for clients to get, create and update data on the Oden Platform.  The API is based on the OpenAPI 3.0 specification. ### Current Version The URL, and host, for the current version is [https://api.oden.app/v2](https://api.oden.app/v2).  ### Oden's Data Model - **Organization**: This represents the Organization registered as an Oden customer. An organization has an associated collection of users, factories, lines, etc. This is the entity a specific authentication token is associated with. - **Asset** or **Machinegroup**: Assets, or machinegroups, are collections of machines, which may either be a **Factory** or a **Line**:   - **Factory**: Factories are collections of lines, representing a particular manufacturing location.     - **Line**: Lines are collections of machines, often representing a particular production line. Lines may also have **Products** mapped to them, indicating what is currently being manufactured by the specific line.       - **Machine**: Machines are the physical machines that make up a line - **Product**: Products capture what entities a manufacturer produces - **Interval**: An interval is a period of time that takes place on a manufacturing line and expresses some business concern. It's Oden's way of making metrics aggregatable, traceable, and relatable to a manufacturer.   - **Run**: A run is a production interval that labels a period of production as being work on some single product   - **Batch**: A batch is a production interval that represents a portion of a particular run   - **State**: A state is an interval that tracks the availability or utilization of a line     - **State Category**: A state category describes what state a line is in - such as ex. uptime, downtime, scrapping, etc.     - **State Reason**: A state reason describes why a line is in a particular state - such as \"maintenance\" being a reason for the category \"downtime\".   - **Custom**: A custom interval can track any other type of interval-based data a manufacturer might want to analyze. These are created on a per-factory basis. - **Target**: Targets specify values and upper/lower thresholds for metrics when specific products are running on specific lines - **Scrap/Yield**: Scrap/yield output specifies amount of produced product on a line during either a run or batch interval. Oden will categorize all output as either scrap or yield - as specified by the Scrap Yield Schema for a given factory. If you have other categories, like rework/blocked/off-grade, you must choose between categorizing those amounts as either good or bad production by specifying as scrap or yield. Clients may also add scrap codes (i.e., reasons) to a given Scrap Yield Data entry.   - **Scrap Code**: A scrap code is a code that explains the reason for a scrap/yield raw data input - such as \"Rework\" - **Quality Test**: Quality Tests are results of quality assurance tests done on site, and uploaded to Oden. They may be attached to a single Batch or Run. - **Metric**: Known in factories as \"tags\", metrics are the raw data that is collected by Oden from the machines and devices on the factory floor. - **Metric Group**: Metric groups are metrics that represent the same thing across different lines. They provide common display names for tags and allow labeling groups of tags as measuring key types of data like performance or production. - **Maintenance Work Order**: A maintenance work order can be used to track work orders maintained in MaintainX and associate them with an Oden line.   ### Best Practices Under the current implementation, the Oden API does not rate limit requests from clients.  However, rate limiting will be introduced in the near future and it is recommended that users design their API clients to not exceed a request rate of **one per second**.  ### Schema All v2 API access is over HTTPS and accessed from https://api.oden.app/v2 All data is sent and received as JSON.  API requests with duplicate keys will process only the data for the first key detected and ignore the rest, so it's not recommended. Batching multiple messages in this way is currently not possible.   - Example of duplicate key in JSON: {\"raw_data\":{\"scrap\":\"10\",\"scrap\":\"100\"}}  All timestamps are returned in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601#Times) format:    `YYYY-MM-DDTHH:MM:SSZ`  All durations are returned in [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601#Times) format with the largest unit of time being the hour:     `PT[n]H[n]M[n]S`  All timestamps sent to the API in POST requests should be in ISO 8601 format. ### HTTP Verbs The ONLY HTTP call type (sometimes called *verb* or *method*) used within Oden's API is **POST**. There are three actions supported via a **POST**; call, search, set, and delete, together supporting CRUD operations;   - **search** requests are used to search for and *read* objects in the Oden Platform       - All Oden Objects may be uniquely identified by some combination of, or a single, parameter.         - Ex a `line` my be identified by either:           - `id`           - `name` AND `factory`   - **set** requests are used to *create* or *update* objects   - **delete** requests are used to *delete* objects. If a delete endpoint is not yet implemented for a given object, users may choose to update the values of a specific entity to null or 0 values.  ### URI Components All endpoints may be accessed with the URI pattern: `https://api.oden.app/v2/{object}/{action}`  Where:   - `object` is the name of the object being requested:        - `factory`, `quality_test`, `interval`, `line`, etc...   - `action` is the name of the action being requested     - `search` , `set` , `delete`  e.g. `https://api.oden.app/v2/factory/search`  # Authentication Clients can authenticate through the v2 API using a Token provided by Oden. Tokens are opaque strings similar to [Bearer](https://swagger.io/docs/specification/authentication/bearer-authentication/) tokens that the client must pass in the [HTTP Authorization request header](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Authorization) in every request. The syntax is as follows:  `Authorization: <type> <credentials>`  Where \\<type\\> is \"Token\" and \\<credentials\\> is the Token string. For example:  `Authorization: Token tokenStringProvidedByOden`  Authenticating with an invalid Token will return `401 Unauthorized Error`.  Authenticating with a Token that is not authorized to read requested data will return `403 Forbidden Error`.  Some endpoints may require requests to be broken out by machinegroup (i.e., line or factory) and the number of requests would scale accordingly. This multiplicity should be taken into consideration when deciding on the frequency the API client makes requests to the Oden endpoints.  To authenticate in this [UI](https://api.oden.app/v2/ui/), click the Lock icon, and copy/paste the token into the Authorize box. 
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


import oden.model.BulkDeleteIntervals200Response;
import oden.model.BulkUpdateIntervals200Response;
import oden.model.GenericError;
import oden.model.Interval;
import oden.model.IntervalBulkCreate;
import oden.model.IntervalBulkDelete;
import oden.model.IntervalBulkUpdate;
import oden.model.IntervalType;
import oden.model.IntervalTypeSet;
import oden.model.SearchLines400Response;
import oden.model.SearchLines409Response;
import oden.model.SearchLines500Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntervalsApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public IntervalsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public IntervalsApi(ApiClient apiClient) {
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
     * Build call for bulkDeleteIntervals
     * @param intervalBulkDelete  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing any intervals found but not deleted. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call bulkDeleteIntervalsCall(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = intervalBulkDelete;

        // create path and map variables
        String localVarPath = "/v2/intervals/delete";

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
    private okhttp3.Call bulkDeleteIntervalsValidateBeforeCall(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalBulkDelete' is set
        if (intervalBulkDelete == null) {
            throw new ApiException("Missing the required parameter 'intervalBulkDelete' when calling bulkDeleteIntervals(Async)");
        }

        return bulkDeleteIntervalsCall(intervalBulkDelete, _callback);

    }

    /**
     * Delete intervals in a time range
     * Delete a group of intervals by a single &#x60;type&#x60; and a single &#x60;line&#x60;, between &#x60;start_time&#x60; and &#x60;end_time&#x60;. Returns a list of intervals that were not deleted, and the number of intervals deleted.  Limitations: - Cannot exceed 15,000 intervals per request, or 30 days worth of data. - Currently does not support \&quot;batch\&quot; or \&quot;run\&quot; interval types. 
     * @param intervalBulkDelete  (required)
     * @return BulkDeleteIntervals200Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing any intervals found but not deleted. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public BulkDeleteIntervals200Response bulkDeleteIntervals(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete) throws ApiException {
        ApiResponse<BulkDeleteIntervals200Response> localVarResp = bulkDeleteIntervalsWithHttpInfo(intervalBulkDelete);
        return localVarResp.getData();
    }

    /**
     * Delete intervals in a time range
     * Delete a group of intervals by a single &#x60;type&#x60; and a single &#x60;line&#x60;, between &#x60;start_time&#x60; and &#x60;end_time&#x60;. Returns a list of intervals that were not deleted, and the number of intervals deleted.  Limitations: - Cannot exceed 15,000 intervals per request, or 30 days worth of data. - Currently does not support \&quot;batch\&quot; or \&quot;run\&quot; interval types. 
     * @param intervalBulkDelete  (required)
     * @return ApiResponse&lt;BulkDeleteIntervals200Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing any intervals found but not deleted. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<BulkDeleteIntervals200Response> bulkDeleteIntervalsWithHttpInfo(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete) throws ApiException {
        okhttp3.Call localVarCall = bulkDeleteIntervalsValidateBeforeCall(intervalBulkDelete, null);
        Type localVarReturnType = new TypeToken<BulkDeleteIntervals200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Delete intervals in a time range (asynchronously)
     * Delete a group of intervals by a single &#x60;type&#x60; and a single &#x60;line&#x60;, between &#x60;start_time&#x60; and &#x60;end_time&#x60;. Returns a list of intervals that were not deleted, and the number of intervals deleted.  Limitations: - Cannot exceed 15,000 intervals per request, or 30 days worth of data. - Currently does not support \&quot;batch\&quot; or \&quot;run\&quot; interval types. 
     * @param intervalBulkDelete  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing any intervals found but not deleted. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call bulkDeleteIntervalsAsync(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete, final ApiCallback<BulkDeleteIntervals200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = bulkDeleteIntervalsValidateBeforeCall(intervalBulkDelete, _callback);
        Type localVarReturnType = new TypeToken<BulkDeleteIntervals200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for bulkSetIntervals
     * @param intervalBulkCreate  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of created interval IDs. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call bulkSetIntervalsCall(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = intervalBulkCreate;

        // create path and map variables
        String localVarPath = "/v2/intervals/set";

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
    private okhttp3.Call bulkSetIntervalsValidateBeforeCall(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalBulkCreate' is set
        if (intervalBulkCreate == null) {
            throw new ApiException("Missing the required parameter 'intervalBulkCreate' when calling bulkSetIntervals(Async)");
        }

        return bulkSetIntervalsCall(intervalBulkCreate, _callback);

    }

    /**
     * Create a set of intervals
     * Create (Does not update) a group of custom intervals, for the same &#x60;type&#x60; and &#x60;line&#x60;. Line and type do not need to be included in each individual interval, just once at the top level.  Limitations: - Cannot excees 2500 intervals per request. - Will not write over other intervals - Does not support \&quot;batch\&quot;, \&quot;run\&quot;, or \&quot;state\&quot; interval types. 
     * @param intervalBulkCreate  (required)
     * @return List&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of created interval IDs. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public List<String> bulkSetIntervals(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate) throws ApiException {
        ApiResponse<List<String>> localVarResp = bulkSetIntervalsWithHttpInfo(intervalBulkCreate);
        return localVarResp.getData();
    }

    /**
     * Create a set of intervals
     * Create (Does not update) a group of custom intervals, for the same &#x60;type&#x60; and &#x60;line&#x60;. Line and type do not need to be included in each individual interval, just once at the top level.  Limitations: - Cannot excees 2500 intervals per request. - Will not write over other intervals - Does not support \&quot;batch\&quot;, \&quot;run\&quot;, or \&quot;state\&quot; interval types. 
     * @param intervalBulkCreate  (required)
     * @return ApiResponse&lt;List&lt;String&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of created interval IDs. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<String>> bulkSetIntervalsWithHttpInfo(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate) throws ApiException {
        okhttp3.Call localVarCall = bulkSetIntervalsValidateBeforeCall(intervalBulkCreate, null);
        Type localVarReturnType = new TypeToken<List<String>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create a set of intervals (asynchronously)
     * Create (Does not update) a group of custom intervals, for the same &#x60;type&#x60; and &#x60;line&#x60;. Line and type do not need to be included in each individual interval, just once at the top level.  Limitations: - Cannot excees 2500 intervals per request. - Will not write over other intervals - Does not support \&quot;batch\&quot;, \&quot;run\&quot;, or \&quot;state\&quot; interval types. 
     * @param intervalBulkCreate  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of created interval IDs. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call bulkSetIntervalsAsync(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate, final ApiCallback<List<String>> _callback) throws ApiException {

        okhttp3.Call localVarCall = bulkSetIntervalsValidateBeforeCall(intervalBulkCreate, _callback);
        Type localVarReturnType = new TypeToken<List<String>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for bulkUpdateIntervals
     * @param intervalBulkUpdate  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response containing successfully updated intervals and any failures that occurred. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call bulkUpdateIntervalsCall(@javax.annotation.Nonnull IntervalBulkUpdate intervalBulkUpdate, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = intervalBulkUpdate;

        // create path and map variables
        String localVarPath = "/v2/intervals/update";

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
    private okhttp3.Call bulkUpdateIntervalsValidateBeforeCall(@javax.annotation.Nonnull IntervalBulkUpdate intervalBulkUpdate, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalBulkUpdate' is set
        if (intervalBulkUpdate == null) {
            throw new ApiException("Missing the required parameter 'intervalBulkUpdate' when calling bulkUpdateIntervals(Async)");
        }

        return bulkUpdateIntervalsCall(intervalBulkUpdate, _callback);

    }

    /**
     * Update a set of intervals
     * Update multiple existing intervals. This endpoint only updates intervals and will not create new ones.  Each interval in the &#x60;intervals&#x60; array must include an &#x60;id&#x60; that references an existing interval.  Updatable fields for each interval: - &#x60;name&#x60;: Update the interval name - &#x60;start_time&#x60;: Modify the start time - &#x60;end_time&#x60;: Modify the end time - &#x60;metadata&#x60;: Update metadata (product, target, category, reason, etc.)  The endpoint will attempt to update all intervals and return information about successes and failures: - Successfully updated intervals are returned in the response - Failed intervals are listed with their IDs and error reasons  Limitations: - Cannot exceed 2500 intervals per request - All intervals must be of the same &#x60;type&#x60; and on the same &#x60;line&#x60;  **Note:** Interval IDs must be obtained from either: - The response when creating intervals via &#x60;/v2/interval/set&#x60; or &#x60;/v2/intervals/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60; 
     * @param intervalBulkUpdate  (required)
     * @return BulkUpdateIntervals200Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response containing successfully updated intervals and any failures that occurred. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public BulkUpdateIntervals200Response bulkUpdateIntervals(@javax.annotation.Nonnull IntervalBulkUpdate intervalBulkUpdate) throws ApiException {
        ApiResponse<BulkUpdateIntervals200Response> localVarResp = bulkUpdateIntervalsWithHttpInfo(intervalBulkUpdate);
        return localVarResp.getData();
    }

    /**
     * Update a set of intervals
     * Update multiple existing intervals. This endpoint only updates intervals and will not create new ones.  Each interval in the &#x60;intervals&#x60; array must include an &#x60;id&#x60; that references an existing interval.  Updatable fields for each interval: - &#x60;name&#x60;: Update the interval name - &#x60;start_time&#x60;: Modify the start time - &#x60;end_time&#x60;: Modify the end time - &#x60;metadata&#x60;: Update metadata (product, target, category, reason, etc.)  The endpoint will attempt to update all intervals and return information about successes and failures: - Successfully updated intervals are returned in the response - Failed intervals are listed with their IDs and error reasons  Limitations: - Cannot exceed 2500 intervals per request - All intervals must be of the same &#x60;type&#x60; and on the same &#x60;line&#x60;  **Note:** Interval IDs must be obtained from either: - The response when creating intervals via &#x60;/v2/interval/set&#x60; or &#x60;/v2/intervals/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60; 
     * @param intervalBulkUpdate  (required)
     * @return ApiResponse&lt;BulkUpdateIntervals200Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response containing successfully updated intervals and any failures that occurred. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<BulkUpdateIntervals200Response> bulkUpdateIntervalsWithHttpInfo(@javax.annotation.Nonnull IntervalBulkUpdate intervalBulkUpdate) throws ApiException {
        okhttp3.Call localVarCall = bulkUpdateIntervalsValidateBeforeCall(intervalBulkUpdate, null);
        Type localVarReturnType = new TypeToken<BulkUpdateIntervals200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update a set of intervals (asynchronously)
     * Update multiple existing intervals. This endpoint only updates intervals and will not create new ones.  Each interval in the &#x60;intervals&#x60; array must include an &#x60;id&#x60; that references an existing interval.  Updatable fields for each interval: - &#x60;name&#x60;: Update the interval name - &#x60;start_time&#x60;: Modify the start time - &#x60;end_time&#x60;: Modify the end time - &#x60;metadata&#x60;: Update metadata (product, target, category, reason, etc.)  The endpoint will attempt to update all intervals and return information about successes and failures: - Successfully updated intervals are returned in the response - Failed intervals are listed with their IDs and error reasons  Limitations: - Cannot exceed 2500 intervals per request - All intervals must be of the same &#x60;type&#x60; and on the same &#x60;line&#x60;  **Note:** Interval IDs must be obtained from either: - The response when creating intervals via &#x60;/v2/interval/set&#x60; or &#x60;/v2/intervals/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60; 
     * @param intervalBulkUpdate  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Response containing successfully updated intervals and any failures that occurred. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call bulkUpdateIntervalsAsync(@javax.annotation.Nonnull IntervalBulkUpdate intervalBulkUpdate, final ApiCallback<BulkUpdateIntervals200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = bulkUpdateIntervalsValidateBeforeCall(intervalBulkUpdate, _callback);
        Type localVarReturnType = new TypeToken<BulkUpdateIntervals200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteInterval
     * @param interval  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing the deleted interval. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteIntervalCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = interval;

        // create path and map variables
        String localVarPath = "/v2/interval/delete";

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
    private okhttp3.Call deleteIntervalValidateBeforeCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'interval' is set
        if (interval == null) {
            throw new ApiException("Missing the required parameter 'interval' when calling deleteInterval(Async)");
        }

        return deleteIntervalCall(interval, _callback);

    }

    /**
     * Delete an interval
     * Delete an interval by &#x60;type&#x60;, &#x60;line&#x60;, and &#x60;id&#x60;  **Note:** The &#x60;id&#x60; must be obtained from either: - The response when creating an interval via &#x60;/v2/interval/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60;  The examples below use placeholder IDs - replace with actual IDs from your system. 
     * @param interval  (required)
     * @return List&lt;Interval&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing the deleted interval. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public List<Interval> deleteInterval(@javax.annotation.Nonnull Interval interval) throws ApiException {
        ApiResponse<List<Interval>> localVarResp = deleteIntervalWithHttpInfo(interval);
        return localVarResp.getData();
    }

    /**
     * Delete an interval
     * Delete an interval by &#x60;type&#x60;, &#x60;line&#x60;, and &#x60;id&#x60;  **Note:** The &#x60;id&#x60; must be obtained from either: - The response when creating an interval via &#x60;/v2/interval/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60;  The examples below use placeholder IDs - replace with actual IDs from your system. 
     * @param interval  (required)
     * @return ApiResponse&lt;List&lt;Interval&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing the deleted interval. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Interval>> deleteIntervalWithHttpInfo(@javax.annotation.Nonnull Interval interval) throws ApiException {
        okhttp3.Call localVarCall = deleteIntervalValidateBeforeCall(interval, null);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Delete an interval (asynchronously)
     * Delete an interval by &#x60;type&#x60;, &#x60;line&#x60;, and &#x60;id&#x60;  **Note:** The &#x60;id&#x60; must be obtained from either: - The response when creating an interval via &#x60;/v2/interval/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60;  The examples below use placeholder IDs - replace with actual IDs from your system. 
     * @param interval  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing the deleted interval. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteIntervalAsync(@javax.annotation.Nonnull Interval interval, final ApiCallback<List<Interval>> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteIntervalValidateBeforeCall(interval, _callback);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteIntervalType
     * @param intervalType  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The deleted interval type. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteIntervalTypeCall(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = intervalType;

        // create path and map variables
        String localVarPath = "/v2/interval_type/delete";

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
    private okhttp3.Call deleteIntervalTypeValidateBeforeCall(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalType' is set
        if (intervalType == null) {
            throw new ApiException("Missing the required parameter 'intervalType' when calling deleteIntervalType(Async)");
        }

        return deleteIntervalTypeCall(intervalType, _callback);

    }

    /**
     * Delete a custom interval type
     * Delete an existing custom Interval Type. &#x60;id&#x60; is required. Built-in types (RUN, BATCH, STATE) cannot be deleted. 
     * @param intervalType  (required)
     * @return IntervalType
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The deleted interval type. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public IntervalType deleteIntervalType(@javax.annotation.Nonnull IntervalType intervalType) throws ApiException {
        ApiResponse<IntervalType> localVarResp = deleteIntervalTypeWithHttpInfo(intervalType);
        return localVarResp.getData();
    }

    /**
     * Delete a custom interval type
     * Delete an existing custom Interval Type. &#x60;id&#x60; is required. Built-in types (RUN, BATCH, STATE) cannot be deleted. 
     * @param intervalType  (required)
     * @return ApiResponse&lt;IntervalType&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The deleted interval type. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<IntervalType> deleteIntervalTypeWithHttpInfo(@javax.annotation.Nonnull IntervalType intervalType) throws ApiException {
        okhttp3.Call localVarCall = deleteIntervalTypeValidateBeforeCall(intervalType, null);
        Type localVarReturnType = new TypeToken<IntervalType>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Delete a custom interval type (asynchronously)
     * Delete an existing custom Interval Type. &#x60;id&#x60; is required. Built-in types (RUN, BATCH, STATE) cannot be deleted. 
     * @param intervalType  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The deleted interval type. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteIntervalTypeAsync(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback<IntervalType> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteIntervalTypeValidateBeforeCall(intervalType, _callback);
        Type localVarReturnType = new TypeToken<IntervalType>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for searchIntervalTypes
     * @param intervalType  (required)
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
    public okhttp3.Call searchIntervalTypesCall(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = intervalType;

        // create path and map variables
        String localVarPath = "/v2/interval_type/search";

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
    private okhttp3.Call searchIntervalTypesValidateBeforeCall(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalType' is set
        if (intervalType == null) {
            throw new ApiException("Missing the required parameter 'intervalType' when calling searchIntervalTypes(Async)");
        }

        return searchIntervalTypesCall(intervalType, _callback);

    }

    /**
     * Search interval types
     * Search for Interval Types by &#x60;name&#x60;, &#x60;id&#x60;, or just &#x60;match: all&#x60; to return all Interval Types associated with the your organization. Basic Interval Types -- &#x60;RUN&#x60;, &#x60;BATCH&#x60;, and &#x60;STATE&#x60; -- are dedicated interval types that come out-of-the-box with Oden. Custom Interval Types may be created by users. 
     * @param intervalType  (required)
     * @return List&lt;IntervalType&gt;
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
    public List<IntervalType> searchIntervalTypes(@javax.annotation.Nonnull IntervalType intervalType) throws ApiException {
        ApiResponse<List<IntervalType>> localVarResp = searchIntervalTypesWithHttpInfo(intervalType);
        return localVarResp.getData();
    }

    /**
     * Search interval types
     * Search for Interval Types by &#x60;name&#x60;, &#x60;id&#x60;, or just &#x60;match: all&#x60; to return all Interval Types associated with the your organization. Basic Interval Types -- &#x60;RUN&#x60;, &#x60;BATCH&#x60;, and &#x60;STATE&#x60; -- are dedicated interval types that come out-of-the-box with Oden. Custom Interval Types may be created by users. 
     * @param intervalType  (required)
     * @return ApiResponse&lt;List&lt;IntervalType&gt;&gt;
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
    public ApiResponse<List<IntervalType>> searchIntervalTypesWithHttpInfo(@javax.annotation.Nonnull IntervalType intervalType) throws ApiException {
        okhttp3.Call localVarCall = searchIntervalTypesValidateBeforeCall(intervalType, null);
        Type localVarReturnType = new TypeToken<List<IntervalType>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Search interval types (asynchronously)
     * Search for Interval Types by &#x60;name&#x60;, &#x60;id&#x60;, or just &#x60;match: all&#x60; to return all Interval Types associated with the your organization. Basic Interval Types -- &#x60;RUN&#x60;, &#x60;BATCH&#x60;, and &#x60;STATE&#x60; -- are dedicated interval types that come out-of-the-box with Oden. Custom Interval Types may be created by users. 
     * @param intervalType  (required)
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
    public okhttp3.Call searchIntervalTypesAsync(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback<List<IntervalType>> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchIntervalTypesValidateBeforeCall(intervalType, _callback);
        Type localVarReturnType = new TypeToken<List<IntervalType>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for searchIntervals
     * @param interval  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of intervals. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchIntervalsCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = interval;

        // create path and map variables
        String localVarPath = "/v2/interval/search";

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
    private okhttp3.Call searchIntervalsValidateBeforeCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'interval' is set
        if (interval == null) {
            throw new ApiException("Missing the required parameter 'interval' when calling searchIntervals(Async)");
        }

        return searchIntervalsCall(interval, _callback);

    }

    /**
     * Search intervals on a line
     * Searches for intervals by &#x60;type&#x60;, &#x60;line&#x60; and other optional parameters:  - &#x60;id&#x60; (for a specific Interval) - &#x60;match: unique&#x60; or omit  OR  - &#x60;match : last&#x60; for the most recent Interval of the given type on the given line  OR  - &#x60;start_time&#x60; and &#x60;end_time&#x60; (for a range of Intervals over a period of time) - &#x60;match: all&#x60; for more than one result  OR  - match all intervals for all lines in a given factory  AND/OR  - &#x60;name&#x60; (only for Intervals with a matching name) 
     * @param interval  (required)
     * @return List&lt;Interval&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of intervals. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public List<Interval> searchIntervals(@javax.annotation.Nonnull Interval interval) throws ApiException {
        ApiResponse<List<Interval>> localVarResp = searchIntervalsWithHttpInfo(interval);
        return localVarResp.getData();
    }

    /**
     * Search intervals on a line
     * Searches for intervals by &#x60;type&#x60;, &#x60;line&#x60; and other optional parameters:  - &#x60;id&#x60; (for a specific Interval) - &#x60;match: unique&#x60; or omit  OR  - &#x60;match : last&#x60; for the most recent Interval of the given type on the given line  OR  - &#x60;start_time&#x60; and &#x60;end_time&#x60; (for a range of Intervals over a period of time) - &#x60;match: all&#x60; for more than one result  OR  - match all intervals for all lines in a given factory  AND/OR  - &#x60;name&#x60; (only for Intervals with a matching name) 
     * @param interval  (required)
     * @return ApiResponse&lt;List&lt;Interval&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of intervals. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Interval>> searchIntervalsWithHttpInfo(@javax.annotation.Nonnull Interval interval) throws ApiException {
        okhttp3.Call localVarCall = searchIntervalsValidateBeforeCall(interval, null);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Search intervals on a line (asynchronously)
     * Searches for intervals by &#x60;type&#x60;, &#x60;line&#x60; and other optional parameters:  - &#x60;id&#x60; (for a specific Interval) - &#x60;match: unique&#x60; or omit  OR  - &#x60;match : last&#x60; for the most recent Interval of the given type on the given line  OR  - &#x60;start_time&#x60; and &#x60;end_time&#x60; (for a range of Intervals over a period of time) - &#x60;match: all&#x60; for more than one result  OR  - match all intervals for all lines in a given factory  AND/OR  - &#x60;name&#x60; (only for Intervals with a matching name) 
     * @param interval  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list of intervals. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchIntervalsAsync(@javax.annotation.Nonnull Interval interval, final ApiCallback<List<Interval>> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchIntervalsValidateBeforeCall(interval, _callback);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for setInterval
     * @param interval  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Updated interval. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call setIntervalCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = interval;

        // create path and map variables
        String localVarPath = "/v2/interval/set";

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
    private okhttp3.Call setIntervalValidateBeforeCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'interval' is set
        if (interval == null) {
            throw new ApiException("Missing the required parameter 'interval' when calling setInterval(Async)");
        }

        return setIntervalCall(interval, _callback);

    }

    /**
     * Create or update an interval
     * Create or update an Interval.  Must include &#x60;line&#x60; and &#x60;type&#x60;. &#x60;match&#x60; must be omitted, &#x60;unique&#x60; or &#x60;last&#x60;   - If &#x60;id&#x60; is not supplied, a new Interval will be created.   - If &#x60;id&#x60; is supplied, existing Interval will be updated. This interval&#39;s start time can be modified using &#x60;start_time&#x60; field.  To update a specific interval supply the &#x60;id&#x60; of that interval.  If the interval exists with all the same parameters nothing is done.  To update the most recent Interval of a given &#x60;type&#x60; on a &#x60;line&#x60; one may use &#x60;match: last&#x60; and omit &#x60;id&#x60;  For &#x60;RUN&#x60; type: if &#x60;product&#x60; and/or &#x60;product_mapping&#x60; does not exist a new one will be created. Further a &#x60;target&#x60; may be set by adding a &#x60;target&#x60; to the metadata field. The &#x60;line&#x60; and &#x60;product&#x60; for this target will be the same as the interval.  Please see examples for more specific information. 
     * @param interval  (required)
     * @return List&lt;Interval&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Updated interval. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public List<Interval> setInterval(@javax.annotation.Nonnull Interval interval) throws ApiException {
        ApiResponse<List<Interval>> localVarResp = setIntervalWithHttpInfo(interval);
        return localVarResp.getData();
    }

    /**
     * Create or update an interval
     * Create or update an Interval.  Must include &#x60;line&#x60; and &#x60;type&#x60;. &#x60;match&#x60; must be omitted, &#x60;unique&#x60; or &#x60;last&#x60;   - If &#x60;id&#x60; is not supplied, a new Interval will be created.   - If &#x60;id&#x60; is supplied, existing Interval will be updated. This interval&#39;s start time can be modified using &#x60;start_time&#x60; field.  To update a specific interval supply the &#x60;id&#x60; of that interval.  If the interval exists with all the same parameters nothing is done.  To update the most recent Interval of a given &#x60;type&#x60; on a &#x60;line&#x60; one may use &#x60;match: last&#x60; and omit &#x60;id&#x60;  For &#x60;RUN&#x60; type: if &#x60;product&#x60; and/or &#x60;product_mapping&#x60; does not exist a new one will be created. Further a &#x60;target&#x60; may be set by adding a &#x60;target&#x60; to the metadata field. The &#x60;line&#x60; and &#x60;product&#x60; for this target will be the same as the interval.  Please see examples for more specific information. 
     * @param interval  (required)
     * @return ApiResponse&lt;List&lt;Interval&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Updated interval. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Interval>> setIntervalWithHttpInfo(@javax.annotation.Nonnull Interval interval) throws ApiException {
        okhttp3.Call localVarCall = setIntervalValidateBeforeCall(interval, null);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create or update an interval (asynchronously)
     * Create or update an Interval.  Must include &#x60;line&#x60; and &#x60;type&#x60;. &#x60;match&#x60; must be omitted, &#x60;unique&#x60; or &#x60;last&#x60;   - If &#x60;id&#x60; is not supplied, a new Interval will be created.   - If &#x60;id&#x60; is supplied, existing Interval will be updated. This interval&#39;s start time can be modified using &#x60;start_time&#x60; field.  To update a specific interval supply the &#x60;id&#x60; of that interval.  If the interval exists with all the same parameters nothing is done.  To update the most recent Interval of a given &#x60;type&#x60; on a &#x60;line&#x60; one may use &#x60;match: last&#x60; and omit &#x60;id&#x60;  For &#x60;RUN&#x60; type: if &#x60;product&#x60; and/or &#x60;product_mapping&#x60; does not exist a new one will be created. Further a &#x60;target&#x60; may be set by adding a &#x60;target&#x60; to the metadata field. The &#x60;line&#x60; and &#x60;product&#x60; for this target will be the same as the interval.  Please see examples for more specific information. 
     * @param interval  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Updated interval. </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call setIntervalAsync(@javax.annotation.Nonnull Interval interval, final ApiCallback<List<Interval>> _callback) throws ApiException {

        okhttp3.Call localVarCall = setIntervalValidateBeforeCall(interval, _callback);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for setIntervalType
     * @param intervalTypeSet  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The interval type after set. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call setIntervalTypeCall(@javax.annotation.Nonnull IntervalTypeSet intervalTypeSet, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = intervalTypeSet;

        // create path and map variables
        String localVarPath = "/v2/interval_type/set";

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
    private okhttp3.Call setIntervalTypeValidateBeforeCall(@javax.annotation.Nonnull IntervalTypeSet intervalTypeSet, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalTypeSet' is set
        if (intervalTypeSet == null) {
            throw new ApiException("Missing the required parameter 'intervalTypeSet' when calling setIntervalType(Async)");
        }

        return setIntervalTypeCall(intervalTypeSet, _callback);

    }

    /**
     * Create or update a custom interval type
     * **Create** (no &#x60;id&#x60; in the body): send &#x60;name&#x60; (required) and optional &#x60;metadata_schema&#x60; / &#x60;tags&#x60;.  **Update** (with &#x60;id&#x60;): send the interval type’s &#x60;id&#x60; and optional &#x60;metadata_schema&#x60; / &#x60;tags&#x60; only. Do not send &#x60;name&#x60; for updates; including &#x60;name&#x60; returns 400. Renames are not supported.  **Patch (update only)**: &#x60;metadata_schema&#x60; — if the key is missing, the existing value is kept; if present, it replaces. &#x60;tags&#x60; — if the key is missing, tags are unchanged; &#x60;null&#x60; leaves tags unchanged; &#x60;[]&#x60; clears tags; a non-empty array replaces.  **Built-in types (RUN, BATCH, STATE) cannot be created, set, or deleted.** 
     * @param intervalTypeSet  (required)
     * @return IntervalType
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The interval type after set. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public IntervalType setIntervalType(@javax.annotation.Nonnull IntervalTypeSet intervalTypeSet) throws ApiException {
        ApiResponse<IntervalType> localVarResp = setIntervalTypeWithHttpInfo(intervalTypeSet);
        return localVarResp.getData();
    }

    /**
     * Create or update a custom interval type
     * **Create** (no &#x60;id&#x60; in the body): send &#x60;name&#x60; (required) and optional &#x60;metadata_schema&#x60; / &#x60;tags&#x60;.  **Update** (with &#x60;id&#x60;): send the interval type’s &#x60;id&#x60; and optional &#x60;metadata_schema&#x60; / &#x60;tags&#x60; only. Do not send &#x60;name&#x60; for updates; including &#x60;name&#x60; returns 400. Renames are not supported.  **Patch (update only)**: &#x60;metadata_schema&#x60; — if the key is missing, the existing value is kept; if present, it replaces. &#x60;tags&#x60; — if the key is missing, tags are unchanged; &#x60;null&#x60; leaves tags unchanged; &#x60;[]&#x60; clears tags; a non-empty array replaces.  **Built-in types (RUN, BATCH, STATE) cannot be created, set, or deleted.** 
     * @param intervalTypeSet  (required)
     * @return ApiResponse&lt;IntervalType&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The interval type after set. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<IntervalType> setIntervalTypeWithHttpInfo(@javax.annotation.Nonnull IntervalTypeSet intervalTypeSet) throws ApiException {
        okhttp3.Call localVarCall = setIntervalTypeValidateBeforeCall(intervalTypeSet, null);
        Type localVarReturnType = new TypeToken<IntervalType>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create or update a custom interval type (asynchronously)
     * **Create** (no &#x60;id&#x60; in the body): send &#x60;name&#x60; (required) and optional &#x60;metadata_schema&#x60; / &#x60;tags&#x60;.  **Update** (with &#x60;id&#x60;): send the interval type’s &#x60;id&#x60; and optional &#x60;metadata_schema&#x60; / &#x60;tags&#x60; only. Do not send &#x60;name&#x60; for updates; including &#x60;name&#x60; returns 400. Renames are not supported.  **Patch (update only)**: &#x60;metadata_schema&#x60; — if the key is missing, the existing value is kept; if present, it replaces. &#x60;tags&#x60; — if the key is missing, tags are unchanged; &#x60;null&#x60; leaves tags unchanged; &#x60;[]&#x60; clears tags; a non-empty array replaces.  **Built-in types (RUN, BATCH, STATE) cannot be created, set, or deleted.** 
     * @param intervalTypeSet  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The interval type after set. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call setIntervalTypeAsync(@javax.annotation.Nonnull IntervalTypeSet intervalTypeSet, final ApiCallback<IntervalType> _callback) throws ApiException {

        okhttp3.Call localVarCall = setIntervalTypeValidateBeforeCall(intervalTypeSet, _callback);
        Type localVarReturnType = new TypeToken<IntervalType>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateInterval
     * @param interval  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Updated interval. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateIntervalCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = interval;

        // create path and map variables
        String localVarPath = "/v2/interval/update";

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
    private okhttp3.Call updateIntervalValidateBeforeCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'interval' is set
        if (interval == null) {
            throw new ApiException("Missing the required parameter 'interval' when calling updateInterval(Async)");
        }

        return updateIntervalCall(interval, _callback);

    }

    /**
     * Update an interval
     * Update an existing Interval. This endpoint only updates intervals and will not create new ones.  Must include &#x60;line&#x60;, &#x60;type&#x60;, and &#x60;id&#x60;. The &#x60;id&#x60; must reference an existing interval.  This interval&#39;s properties can be modified using the following fields: - &#x60;name&#x60;: Update the interval name - &#x60;start_time&#x60;: Modify the start time - &#x60;end_time&#x60;: Modify the end time - &#x60;metadata&#x60;: Update metadata (product, target, category, reason, etc.)  If the interval does not exist, a 404 error will be returned.  **Note:** The &#x60;id&#x60; must be obtained from either: - The response when creating an interval via &#x60;/v2/interval/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60; 
     * @param interval  (required)
     * @return List&lt;Interval&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Updated interval. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public List<Interval> updateInterval(@javax.annotation.Nonnull Interval interval) throws ApiException {
        ApiResponse<List<Interval>> localVarResp = updateIntervalWithHttpInfo(interval);
        return localVarResp.getData();
    }

    /**
     * Update an interval
     * Update an existing Interval. This endpoint only updates intervals and will not create new ones.  Must include &#x60;line&#x60;, &#x60;type&#x60;, and &#x60;id&#x60;. The &#x60;id&#x60; must reference an existing interval.  This interval&#39;s properties can be modified using the following fields: - &#x60;name&#x60;: Update the interval name - &#x60;start_time&#x60;: Modify the start time - &#x60;end_time&#x60;: Modify the end time - &#x60;metadata&#x60;: Update metadata (product, target, category, reason, etc.)  If the interval does not exist, a 404 error will be returned.  **Note:** The &#x60;id&#x60; must be obtained from either: - The response when creating an interval via &#x60;/v2/interval/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60; 
     * @param interval  (required)
     * @return ApiResponse&lt;List&lt;Interval&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Updated interval. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Interval>> updateIntervalWithHttpInfo(@javax.annotation.Nonnull Interval interval) throws ApiException {
        okhttp3.Call localVarCall = updateIntervalValidateBeforeCall(interval, null);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Update an interval (asynchronously)
     * Update an existing Interval. This endpoint only updates intervals and will not create new ones.  Must include &#x60;line&#x60;, &#x60;type&#x60;, and &#x60;id&#x60;. The &#x60;id&#x60; must reference an existing interval.  This interval&#39;s properties can be modified using the following fields: - &#x60;name&#x60;: Update the interval name - &#x60;start_time&#x60;: Modify the start time - &#x60;end_time&#x60;: Modify the end time - &#x60;metadata&#x60;: Update metadata (product, target, category, reason, etc.)  If the interval does not exist, a 404 error will be returned.  **Note:** The &#x60;id&#x60; must be obtained from either: - The response when creating an interval via &#x60;/v2/interval/set&#x60; - Searching for intervals via &#x60;/v2/interval/search&#x60; 
     * @param interval  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Updated interval. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateIntervalAsync(@javax.annotation.Nonnull Interval interval, final ApiCallback<List<Interval>> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateIntervalValidateBeforeCall(interval, _callback);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
