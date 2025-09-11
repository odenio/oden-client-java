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
import oden.model.Interval;
import oden.model.IntervalBulkCreate;
import oden.model.IntervalBulkDelete;
import oden.model.IntervalType;
import oden.model.V2IntervalsDeletePost200Response;
import oden.model.V2IntervalsSetPost200Response;
import oden.model.V2LineSearchPost400Response;
import oden.model.V2LineSearchPost409Response;
import oden.model.V2LineSearchPost500Response;

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
     * Build call for v2IntervalDeletePost
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
    public okhttp3.Call v2IntervalDeletePostCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call v2IntervalDeletePostValidateBeforeCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'interval' is set
        if (interval == null) {
            throw new ApiException("Missing the required parameter 'interval' when calling v2IntervalDeletePost(Async)");
        }

        return v2IntervalDeletePostCall(interval, _callback);

    }

    /**
     * 
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
    public List<Interval> v2IntervalDeletePost(@javax.annotation.Nonnull Interval interval) throws ApiException {
        ApiResponse<List<Interval>> localVarResp = v2IntervalDeletePostWithHttpInfo(interval);
        return localVarResp.getData();
    }

    /**
     * 
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
    public ApiResponse<List<Interval>> v2IntervalDeletePostWithHttpInfo(@javax.annotation.Nonnull Interval interval) throws ApiException {
        okhttp3.Call localVarCall = v2IntervalDeletePostValidateBeforeCall(interval, null);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
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
    public okhttp3.Call v2IntervalDeletePostAsync(@javax.annotation.Nonnull Interval interval, final ApiCallback<List<Interval>> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2IntervalDeletePostValidateBeforeCall(interval, _callback);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2IntervalSearchPost
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
    public okhttp3.Call v2IntervalSearchPostCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call v2IntervalSearchPostValidateBeforeCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'interval' is set
        if (interval == null) {
            throw new ApiException("Missing the required parameter 'interval' when calling v2IntervalSearchPost(Async)");
        }

        return v2IntervalSearchPostCall(interval, _callback);

    }

    /**
     * 
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
    public List<Interval> v2IntervalSearchPost(@javax.annotation.Nonnull Interval interval) throws ApiException {
        ApiResponse<List<Interval>> localVarResp = v2IntervalSearchPostWithHttpInfo(interval);
        return localVarResp.getData();
    }

    /**
     * 
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
    public ApiResponse<List<Interval>> v2IntervalSearchPostWithHttpInfo(@javax.annotation.Nonnull Interval interval) throws ApiException {
        okhttp3.Call localVarCall = v2IntervalSearchPostValidateBeforeCall(interval, null);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
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
    public okhttp3.Call v2IntervalSearchPostAsync(@javax.annotation.Nonnull Interval interval, final ApiCallback<List<Interval>> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2IntervalSearchPostValidateBeforeCall(interval, _callback);
        Type localVarReturnType = new TypeToken<List<Interval>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2IntervalSetPost
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
    public okhttp3.Call v2IntervalSetPostCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call v2IntervalSetPostValidateBeforeCall(@javax.annotation.Nonnull Interval interval, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'interval' is set
        if (interval == null) {
            throw new ApiException("Missing the required parameter 'interval' when calling v2IntervalSetPost(Async)");
        }

        return v2IntervalSetPostCall(interval, _callback);

    }

    /**
     * 
     * Create or update an Interval.  Must include &#x60;line&#x60; and &#x60;type&#x60;. &#x60;match&#x60; must be omitted, &#x60;unique&#x60; or &#x60;last&#x60;   - If &#x60;id&#x60; is not supplied, a new Interval will be created.   - If &#x60;id&#x60; is supplied, existing Interval will be updated. This interval&#39;s start time can be modified using &#x60;start_time&#x60; field.  To update a specific interval supply the &#x60;id&#x60; of that interval.  If the interval exists with all the same parameters nothing is done.  To update the most recent Interval of a given &#x60;type&#x60; on a &#x60;line&#x60; one may use &#x60;match: last&#x60; and omit &#x60;id&#x60;  For &#x60;RUN&#x60; type: if &#x60;product&#x60; and/or &#x60;product_mapping&#x60; does not exist a new one will be created. Further a &#x60;target&#x60; may be set by adding a &#x60;target&#x60; to the metadata field. The &#x60;line&#x60; and &#x60;product&#x60; for this target will be the same as the interval.  Please see examples for more specific information. 
     * @param interval  (required)
     * @return Interval
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
    public Interval v2IntervalSetPost(@javax.annotation.Nonnull Interval interval) throws ApiException {
        ApiResponse<Interval> localVarResp = v2IntervalSetPostWithHttpInfo(interval);
        return localVarResp.getData();
    }

    /**
     * 
     * Create or update an Interval.  Must include &#x60;line&#x60; and &#x60;type&#x60;. &#x60;match&#x60; must be omitted, &#x60;unique&#x60; or &#x60;last&#x60;   - If &#x60;id&#x60; is not supplied, a new Interval will be created.   - If &#x60;id&#x60; is supplied, existing Interval will be updated. This interval&#39;s start time can be modified using &#x60;start_time&#x60; field.  To update a specific interval supply the &#x60;id&#x60; of that interval.  If the interval exists with all the same parameters nothing is done.  To update the most recent Interval of a given &#x60;type&#x60; on a &#x60;line&#x60; one may use &#x60;match: last&#x60; and omit &#x60;id&#x60;  For &#x60;RUN&#x60; type: if &#x60;product&#x60; and/or &#x60;product_mapping&#x60; does not exist a new one will be created. Further a &#x60;target&#x60; may be set by adding a &#x60;target&#x60; to the metadata field. The &#x60;line&#x60; and &#x60;product&#x60; for this target will be the same as the interval.  Please see examples for more specific information. 
     * @param interval  (required)
     * @return ApiResponse&lt;Interval&gt;
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
    public ApiResponse<Interval> v2IntervalSetPostWithHttpInfo(@javax.annotation.Nonnull Interval interval) throws ApiException {
        okhttp3.Call localVarCall = v2IntervalSetPostValidateBeforeCall(interval, null);
        Type localVarReturnType = new TypeToken<Interval>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
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
    public okhttp3.Call v2IntervalSetPostAsync(@javax.annotation.Nonnull Interval interval, final ApiCallback<Interval> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2IntervalSetPostValidateBeforeCall(interval, _callback);
        Type localVarReturnType = new TypeToken<Interval>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2IntervalTypeSearchPost
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
    public okhttp3.Call v2IntervalTypeSearchPostCall(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call v2IntervalTypeSearchPostValidateBeforeCall(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalType' is set
        if (intervalType == null) {
            throw new ApiException("Missing the required parameter 'intervalType' when calling v2IntervalTypeSearchPost(Async)");
        }

        return v2IntervalTypeSearchPostCall(intervalType, _callback);

    }

    /**
     * 
     * Search for Interval Types by &#x60;name&#x60;, &#x60;id&#x60;, &#x60;factory&#x60; or just &#x60;match: all&#x60; to return all Interval Types associated with the your organization. Basic Interval Types -- &#x60;RUN&#x60;, &#x60;BATCH&#x60;, and &#x60;STATE&#x60; -- are associated with every factory in Oden&#39;s system. Custom Interval Types may be created by users, are set on a per factory basis, and may only describe Intervals on Lines associated with that Factory. 
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
    public List<IntervalType> v2IntervalTypeSearchPost(@javax.annotation.Nonnull IntervalType intervalType) throws ApiException {
        ApiResponse<List<IntervalType>> localVarResp = v2IntervalTypeSearchPostWithHttpInfo(intervalType);
        return localVarResp.getData();
    }

    /**
     * 
     * Search for Interval Types by &#x60;name&#x60;, &#x60;id&#x60;, &#x60;factory&#x60; or just &#x60;match: all&#x60; to return all Interval Types associated with the your organization. Basic Interval Types -- &#x60;RUN&#x60;, &#x60;BATCH&#x60;, and &#x60;STATE&#x60; -- are associated with every factory in Oden&#39;s system. Custom Interval Types may be created by users, are set on a per factory basis, and may only describe Intervals on Lines associated with that Factory. 
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
    public ApiResponse<List<IntervalType>> v2IntervalTypeSearchPostWithHttpInfo(@javax.annotation.Nonnull IntervalType intervalType) throws ApiException {
        okhttp3.Call localVarCall = v2IntervalTypeSearchPostValidateBeforeCall(intervalType, null);
        Type localVarReturnType = new TypeToken<List<IntervalType>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Search for Interval Types by &#x60;name&#x60;, &#x60;id&#x60;, &#x60;factory&#x60; or just &#x60;match: all&#x60; to return all Interval Types associated with the your organization. Basic Interval Types -- &#x60;RUN&#x60;, &#x60;BATCH&#x60;, and &#x60;STATE&#x60; -- are associated with every factory in Oden&#39;s system. Custom Interval Types may be created by users, are set on a per factory basis, and may only describe Intervals on Lines associated with that Factory. 
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
    public okhttp3.Call v2IntervalTypeSearchPostAsync(@javax.annotation.Nonnull IntervalType intervalType, final ApiCallback<List<IntervalType>> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2IntervalTypeSearchPostValidateBeforeCall(intervalType, _callback);
        Type localVarReturnType = new TypeToken<List<IntervalType>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2IntervalsDeletePost
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
    public okhttp3.Call v2IntervalsDeletePostCall(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call v2IntervalsDeletePostValidateBeforeCall(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalBulkDelete' is set
        if (intervalBulkDelete == null) {
            throw new ApiException("Missing the required parameter 'intervalBulkDelete' when calling v2IntervalsDeletePost(Async)");
        }

        return v2IntervalsDeletePostCall(intervalBulkDelete, _callback);

    }

    /**
     * 
     * Delete a group of intervals by a single &#x60;type&#x60; and a single &#x60;line&#x60;, between &#x60;start_time&#x60; and &#x60;end_time&#x60;. Returns a list of intervals that were not deleted, and the number of intervals deleted.  Limitations: - Cannot exceed 15,000 intervals per request, or 30 days worth of data. - Currently does not support \&quot;batch\&quot; or \&quot;run\&quot; interval types. 
     * @param intervalBulkDelete  (required)
     * @return V2IntervalsDeletePost200Response
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
    public V2IntervalsDeletePost200Response v2IntervalsDeletePost(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete) throws ApiException {
        ApiResponse<V2IntervalsDeletePost200Response> localVarResp = v2IntervalsDeletePostWithHttpInfo(intervalBulkDelete);
        return localVarResp.getData();
    }

    /**
     * 
     * Delete a group of intervals by a single &#x60;type&#x60; and a single &#x60;line&#x60;, between &#x60;start_time&#x60; and &#x60;end_time&#x60;. Returns a list of intervals that were not deleted, and the number of intervals deleted.  Limitations: - Cannot exceed 15,000 intervals per request, or 30 days worth of data. - Currently does not support \&quot;batch\&quot; or \&quot;run\&quot; interval types. 
     * @param intervalBulkDelete  (required)
     * @return ApiResponse&lt;V2IntervalsDeletePost200Response&gt;
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
    public ApiResponse<V2IntervalsDeletePost200Response> v2IntervalsDeletePostWithHttpInfo(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete) throws ApiException {
        okhttp3.Call localVarCall = v2IntervalsDeletePostValidateBeforeCall(intervalBulkDelete, null);
        Type localVarReturnType = new TypeToken<V2IntervalsDeletePost200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
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
    public okhttp3.Call v2IntervalsDeletePostAsync(@javax.annotation.Nonnull IntervalBulkDelete intervalBulkDelete, final ApiCallback<V2IntervalsDeletePost200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2IntervalsDeletePostValidateBeforeCall(intervalBulkDelete, _callback);
        Type localVarReturnType = new TypeToken<V2IntervalsDeletePost200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for v2IntervalsSetPost
     * @param intervalBulkCreate  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing any intervals found but not created. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2IntervalsSetPostCall(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate, final ApiCallback _callback) throws ApiException {
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
    private okhttp3.Call v2IntervalsSetPostValidateBeforeCall(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'intervalBulkCreate' is set
        if (intervalBulkCreate == null) {
            throw new ApiException("Missing the required parameter 'intervalBulkCreate' when calling v2IntervalsSetPost(Async)");
        }

        return v2IntervalsSetPostCall(intervalBulkCreate, _callback);

    }

    /**
     * 
     * Create (Does not update) a group of custom intervals, for the same &#x60;type&#x60; and &#x60;line&#x60;. Line and type do not need to be included in each individual interval, just once at the top level.  Limitations: - Cannot excees 2500 intervals per request. - Will not write over other intervals - Does not support \&quot;batch\&quot;, \&quot;run\&quot;, or \&quot;state\&quot; interval types. 
     * @param intervalBulkCreate  (required)
     * @return V2IntervalsSetPost200Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing any intervals found but not created. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public V2IntervalsSetPost200Response v2IntervalsSetPost(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate) throws ApiException {
        ApiResponse<V2IntervalsSetPost200Response> localVarResp = v2IntervalsSetPostWithHttpInfo(intervalBulkCreate);
        return localVarResp.getData();
    }

    /**
     * 
     * Create (Does not update) a group of custom intervals, for the same &#x60;type&#x60; and &#x60;line&#x60;. Line and type do not need to be included in each individual interval, just once at the top level.  Limitations: - Cannot excees 2500 intervals per request. - Will not write over other intervals - Does not support \&quot;batch\&quot;, \&quot;run\&quot;, or \&quot;state\&quot; interval types. 
     * @param intervalBulkCreate  (required)
     * @return ApiResponse&lt;V2IntervalsSetPost200Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing any intervals found but not created. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<V2IntervalsSetPost200Response> v2IntervalsSetPostWithHttpInfo(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate) throws ApiException {
        okhttp3.Call localVarCall = v2IntervalsSetPostValidateBeforeCall(intervalBulkCreate, null);
        Type localVarReturnType = new TypeToken<V2IntervalsSetPost200Response>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Create (Does not update) a group of custom intervals, for the same &#x60;type&#x60; and &#x60;line&#x60;. Line and type do not need to be included in each individual interval, just once at the top level.  Limitations: - Cannot excees 2500 intervals per request. - Will not write over other intervals - Does not support \&quot;batch\&quot;, \&quot;run\&quot;, or \&quot;state\&quot; interval types. 
     * @param intervalBulkCreate  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> A list containing any intervals found but not created. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> An error occurred regarding one of the input parameters </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> A {match: \&quot;unique\&quot;} was requested, but multiple entities matched the search parameters.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
        <tr><td> 501 </td><td> Endpoint is not yet implemented </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call v2IntervalsSetPostAsync(@javax.annotation.Nonnull IntervalBulkCreate intervalBulkCreate, final ApiCallback<V2IntervalsSetPost200Response> _callback) throws ApiException {

        okhttp3.Call localVarCall = v2IntervalsSetPostValidateBeforeCall(intervalBulkCreate, _callback);
        Type localVarReturnType = new TypeToken<V2IntervalsSetPost200Response>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
