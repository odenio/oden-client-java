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


import oden.model.GenericError;
import oden.model.SearchLines400Response;
import oden.model.SearchLines500Response;
import oden.model.StateCategoryDetail;
import oden.model.StateCategorySearch;
import oden.model.StateCategorySet;
import oden.model.StateReasonDetail;
import oden.model.StateReasonSearch;
import oden.model.StateReasonSet;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatesApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public StatesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public StatesApi(ApiClient apiClient) {
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
     * Build call for searchStateCategories
     * @param stateCategorySearch  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The matching state categories. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchStateCategoriesCall(@javax.annotation.Nonnull StateCategorySearch stateCategorySearch, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = stateCategorySearch;

        // create path and map variables
        String localVarPath = "/v2/state_category/search";

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
    private okhttp3.Call searchStateCategoriesValidateBeforeCall(@javax.annotation.Nonnull StateCategorySearch stateCategorySearch, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'stateCategorySearch' is set
        if (stateCategorySearch == null) {
            throw new ApiException("Missing the required parameter 'stateCategorySearch' when calling searchStateCategories(Async)");
        }

        return searchStateCategoriesCall(stateCategorySearch, _callback);

    }

    /**
     * Search state categories
     * Returns the state categories available to your organization, including Oden&#39;s built-in ones. Filter by &#x60;id&#x60; or &#x60;name&#x60;; omit both to list all. Built-in categories have no &#x60;organization&#x60; and cannot be modified.  &#x60;type&#x60; summarises how the platform treats time in the category: - &#x60;uptime&#x60;: producing good product - &#x60;scrapping&#x60;: running but not producing good product - &#x60;planned_downtime&#x60;, &#x60;unplanned_downtime&#x60;: not running 
     * @param stateCategorySearch  (required)
     * @return List&lt;StateCategoryDetail&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The matching state categories. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public List<StateCategoryDetail> searchStateCategories(@javax.annotation.Nonnull StateCategorySearch stateCategorySearch) throws ApiException {
        ApiResponse<List<StateCategoryDetail>> localVarResp = searchStateCategoriesWithHttpInfo(stateCategorySearch);
        return localVarResp.getData();
    }

    /**
     * Search state categories
     * Returns the state categories available to your organization, including Oden&#39;s built-in ones. Filter by &#x60;id&#x60; or &#x60;name&#x60;; omit both to list all. Built-in categories have no &#x60;organization&#x60; and cannot be modified.  &#x60;type&#x60; summarises how the platform treats time in the category: - &#x60;uptime&#x60;: producing good product - &#x60;scrapping&#x60;: running but not producing good product - &#x60;planned_downtime&#x60;, &#x60;unplanned_downtime&#x60;: not running 
     * @param stateCategorySearch  (required)
     * @return ApiResponse&lt;List&lt;StateCategoryDetail&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The matching state categories. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<StateCategoryDetail>> searchStateCategoriesWithHttpInfo(@javax.annotation.Nonnull StateCategorySearch stateCategorySearch) throws ApiException {
        okhttp3.Call localVarCall = searchStateCategoriesValidateBeforeCall(stateCategorySearch, null);
        Type localVarReturnType = new TypeToken<List<StateCategoryDetail>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Search state categories (asynchronously)
     * Returns the state categories available to your organization, including Oden&#39;s built-in ones. Filter by &#x60;id&#x60; or &#x60;name&#x60;; omit both to list all. Built-in categories have no &#x60;organization&#x60; and cannot be modified.  &#x60;type&#x60; summarises how the platform treats time in the category: - &#x60;uptime&#x60;: producing good product - &#x60;scrapping&#x60;: running but not producing good product - &#x60;planned_downtime&#x60;, &#x60;unplanned_downtime&#x60;: not running 
     * @param stateCategorySearch  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The matching state categories. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchStateCategoriesAsync(@javax.annotation.Nonnull StateCategorySearch stateCategorySearch, final ApiCallback<List<StateCategoryDetail>> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchStateCategoriesValidateBeforeCall(stateCategorySearch, _callback);
        Type localVarReturnType = new TypeToken<List<StateCategoryDetail>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for searchStateReasons
     * @param stateReasonSearch  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The matching state reasons. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchStateReasonsCall(@javax.annotation.Nonnull StateReasonSearch stateReasonSearch, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = stateReasonSearch;

        // create path and map variables
        String localVarPath = "/v2/state_reason/search";

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
    private okhttp3.Call searchStateReasonsValidateBeforeCall(@javax.annotation.Nonnull StateReasonSearch stateReasonSearch, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'stateReasonSearch' is set
        if (stateReasonSearch == null) {
            throw new ApiException("Missing the required parameter 'stateReasonSearch' when calling searchStateReasons(Async)");
        }

        return searchStateReasonsCall(stateReasonSearch, _callback);

    }

    /**
     * Search state reasons on one or more lines
     * Returns the state reasons defined on every line in &#x60;lines&#x60; (required; each by &#x60;id&#x60;, or by &#x60;name&#x60; plus &#x60;factory&#x60;), optionally restricted to one &#x60;category&#x60; (by &#x60;id&#x60; or &#x60;name&#x60;). Each result names its line, so one request can audit a whole fleet. Inactive reasons are included, with &#x60;active: false&#x60;. 
     * @param stateReasonSearch  (required)
     * @return List&lt;StateReasonDetail&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The matching state reasons. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public List<StateReasonDetail> searchStateReasons(@javax.annotation.Nonnull StateReasonSearch stateReasonSearch) throws ApiException {
        ApiResponse<List<StateReasonDetail>> localVarResp = searchStateReasonsWithHttpInfo(stateReasonSearch);
        return localVarResp.getData();
    }

    /**
     * Search state reasons on one or more lines
     * Returns the state reasons defined on every line in &#x60;lines&#x60; (required; each by &#x60;id&#x60;, or by &#x60;name&#x60; plus &#x60;factory&#x60;), optionally restricted to one &#x60;category&#x60; (by &#x60;id&#x60; or &#x60;name&#x60;). Each result names its line, so one request can audit a whole fleet. Inactive reasons are included, with &#x60;active: false&#x60;. 
     * @param stateReasonSearch  (required)
     * @return ApiResponse&lt;List&lt;StateReasonDetail&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The matching state reasons. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<StateReasonDetail>> searchStateReasonsWithHttpInfo(@javax.annotation.Nonnull StateReasonSearch stateReasonSearch) throws ApiException {
        okhttp3.Call localVarCall = searchStateReasonsValidateBeforeCall(stateReasonSearch, null);
        Type localVarReturnType = new TypeToken<List<StateReasonDetail>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Search state reasons on one or more lines (asynchronously)
     * Returns the state reasons defined on every line in &#x60;lines&#x60; (required; each by &#x60;id&#x60;, or by &#x60;name&#x60; plus &#x60;factory&#x60;), optionally restricted to one &#x60;category&#x60; (by &#x60;id&#x60; or &#x60;name&#x60;). Each result names its line, so one request can audit a whole fleet. Inactive reasons are included, with &#x60;active: false&#x60;. 
     * @param stateReasonSearch  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The matching state reasons. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchStateReasonsAsync(@javax.annotation.Nonnull StateReasonSearch stateReasonSearch, final ApiCallback<List<StateReasonDetail>> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchStateReasonsValidateBeforeCall(stateReasonSearch, _callback);
        Type localVarReturnType = new TypeToken<List<StateReasonDetail>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for setStateCategory
     * @param stateCategorySet  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The state category after set. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call setStateCategoryCall(@javax.annotation.Nonnull StateCategorySet stateCategorySet, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = stateCategorySet;

        // create path and map variables
        String localVarPath = "/v2/state_category/set";

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
    private okhttp3.Call setStateCategoryValidateBeforeCall(@javax.annotation.Nonnull StateCategorySet stateCategorySet, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'stateCategorySet' is set
        if (stateCategorySet == null) {
            throw new ApiException("Missing the required parameter 'stateCategorySet' when calling setStateCategory(Async)");
        }

        return setStateCategoryCall(stateCategorySet, _callback);

    }

    /**
     * Create or update a state category
     * **Create** (no &#x60;id&#x60;): &#x60;name&#x60; and &#x60;type&#x60; are required. If a category with that &#x60;name&#x60; already exists for your organization and has the same &#x60;type&#x60;, it is returned unchanged; a different &#x60;type&#x60; is a 409.  **Update** (with &#x60;id&#x60;): fields you omit keep their current value. Built-in categories (no organization) cannot be updated.  Requires an organization admin token. 
     * @param stateCategorySet  (required)
     * @return StateCategoryDetail
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The state category after set. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public StateCategoryDetail setStateCategory(@javax.annotation.Nonnull StateCategorySet stateCategorySet) throws ApiException {
        ApiResponse<StateCategoryDetail> localVarResp = setStateCategoryWithHttpInfo(stateCategorySet);
        return localVarResp.getData();
    }

    /**
     * Create or update a state category
     * **Create** (no &#x60;id&#x60;): &#x60;name&#x60; and &#x60;type&#x60; are required. If a category with that &#x60;name&#x60; already exists for your organization and has the same &#x60;type&#x60;, it is returned unchanged; a different &#x60;type&#x60; is a 409.  **Update** (with &#x60;id&#x60;): fields you omit keep their current value. Built-in categories (no organization) cannot be updated.  Requires an organization admin token. 
     * @param stateCategorySet  (required)
     * @return ApiResponse&lt;StateCategoryDetail&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The state category after set. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<StateCategoryDetail> setStateCategoryWithHttpInfo(@javax.annotation.Nonnull StateCategorySet stateCategorySet) throws ApiException {
        okhttp3.Call localVarCall = setStateCategoryValidateBeforeCall(stateCategorySet, null);
        Type localVarReturnType = new TypeToken<StateCategoryDetail>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create or update a state category (asynchronously)
     * **Create** (no &#x60;id&#x60;): &#x60;name&#x60; and &#x60;type&#x60; are required. If a category with that &#x60;name&#x60; already exists for your organization and has the same &#x60;type&#x60;, it is returned unchanged; a different &#x60;type&#x60; is a 409.  **Update** (with &#x60;id&#x60;): fields you omit keep their current value. Built-in categories (no organization) cannot be updated.  Requires an organization admin token. 
     * @param stateCategorySet  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The state category after set. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call setStateCategoryAsync(@javax.annotation.Nonnull StateCategorySet stateCategorySet, final ApiCallback<StateCategoryDetail> _callback) throws ApiException {

        okhttp3.Call localVarCall = setStateCategoryValidateBeforeCall(stateCategorySet, _callback);
        Type localVarReturnType = new TypeToken<StateCategoryDetail>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for setStateReasons
     * @param stateReasonSet  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The state reason on each requested line. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call setStateReasonsCall(@javax.annotation.Nonnull StateReasonSet stateReasonSet, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = stateReasonSet;

        // create path and map variables
        String localVarPath = "/v2/state_reason/set";

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
    private okhttp3.Call setStateReasonsValidateBeforeCall(@javax.annotation.Nonnull StateReasonSet stateReasonSet, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'stateReasonSet' is set
        if (stateReasonSet == null) {
            throw new ApiException("Missing the required parameter 'stateReasonSet' when calling setStateReasons(Async)");
        }

        return setStateReasonsCall(stateReasonSet, _callback);

    }

    /**
     * Create a state reason on one or more lines
     * Ensures the reason &#x60;name&#x60; exists under &#x60;category&#x60; on every line in &#x60;lines&#x60;. Lines that already have it are returned as they are; the rest are created in one transaction. The response has one entry per line, in request order, so calling this again is a no-op.  &#x60;category&#x60; may be given by &#x60;id&#x60; or &#x60;name&#x60; and must be one of your organization&#39;s own categories; Oden&#39;s built-in categories are read-only. &#x60;active&#x60; defaults to true and also re-activates an existing inactive reason (or deactivates with &#x60;false&#x60;).  Requires an organization admin token. 
     * @param stateReasonSet  (required)
     * @return List&lt;StateReasonDetail&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The state reason on each requested line. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public List<StateReasonDetail> setStateReasons(@javax.annotation.Nonnull StateReasonSet stateReasonSet) throws ApiException {
        ApiResponse<List<StateReasonDetail>> localVarResp = setStateReasonsWithHttpInfo(stateReasonSet);
        return localVarResp.getData();
    }

    /**
     * Create a state reason on one or more lines
     * Ensures the reason &#x60;name&#x60; exists under &#x60;category&#x60; on every line in &#x60;lines&#x60;. Lines that already have it are returned as they are; the rest are created in one transaction. The response has one entry per line, in request order, so calling this again is a no-op.  &#x60;category&#x60; may be given by &#x60;id&#x60; or &#x60;name&#x60; and must be one of your organization&#39;s own categories; Oden&#39;s built-in categories are read-only. &#x60;active&#x60; defaults to true and also re-activates an existing inactive reason (or deactivates with &#x60;false&#x60;).  Requires an organization admin token. 
     * @param stateReasonSet  (required)
     * @return ApiResponse&lt;List&lt;StateReasonDetail&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The state reason on each requested line. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<StateReasonDetail>> setStateReasonsWithHttpInfo(@javax.annotation.Nonnull StateReasonSet stateReasonSet) throws ApiException {
        okhttp3.Call localVarCall = setStateReasonsValidateBeforeCall(stateReasonSet, null);
        Type localVarReturnType = new TypeToken<List<StateReasonDetail>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create a state reason on one or more lines (asynchronously)
     * Ensures the reason &#x60;name&#x60; exists under &#x60;category&#x60; on every line in &#x60;lines&#x60;. Lines that already have it are returned as they are; the rest are created in one transaction. The response has one entry per line, in request order, so calling this again is a no-op.  &#x60;category&#x60; may be given by &#x60;id&#x60; or &#x60;name&#x60; and must be one of your organization&#39;s own categories; Oden&#39;s built-in categories are read-only. &#x60;active&#x60; defaults to true and also re-activates an existing inactive reason (or deactivates with &#x60;false&#x60;).  Requires an organization admin token. 
     * @param stateReasonSet  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> The state reason on each requested line. </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> The request is malformed. Either one of the input parameters is invalid (ParameterError body), or this endpoint does not support the combination of inputs supplied (GenericError body, error message prefixed \&quot;Unimplemented:\&quot;).  </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> User has provided either no credentials or invalid credentials </td><td>  -  </td></tr>
        <tr><td> 403 </td><td> User has provided valid credentials but is not authorized to access the entity  </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entity not found </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> The entity already exists in a shape that conflicts with the request, or a concurrent request created it and the conflict did not resolve on retry. The message says which.  </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> An internal server error has occurred. If reporting the error to Oden, include the ID returned in this response to aid in debugging.  </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call setStateReasonsAsync(@javax.annotation.Nonnull StateReasonSet stateReasonSet, final ApiCallback<List<StateReasonDetail>> _callback) throws ApiException {

        okhttp3.Call localVarCall = setStateReasonsValidateBeforeCall(stateReasonSet, _callback);
        Type localVarReturnType = new TypeToken<List<StateReasonDetail>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
