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

import oden.ApiException;
import oden.model.GenericError;
import oden.model.SearchLines400Response;
import oden.model.SearchLines500Response;
import oden.model.StateCategoryDetail;
import oden.model.StateCategorySearch;
import oden.model.StateCategorySet;
import oden.model.StateReasonDetail;
import oden.model.StateReasonSearch;
import oden.model.StateReasonSet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for StatesApi
 */
@Disabled
public class StatesApiTest {

    private final StatesApi api = new StatesApi();

    /**
     * Search state categories
     *
     * Returns the state categories available to your organization, including Oden&#39;s built-in ones. Filter by &#x60;id&#x60; or &#x60;name&#x60;; omit both to list all. Built-in categories have no &#x60;organization&#x60; and cannot be modified.  &#x60;type&#x60; summarises how the platform treats time in the category: - &#x60;uptime&#x60;: producing good product - &#x60;scrapping&#x60;: running but not producing good product - &#x60;planned_downtime&#x60;, &#x60;unplanned_downtime&#x60;: not running 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void searchStateCategoriesTest() throws ApiException {
        StateCategorySearch stateCategorySearch = null;
        List<StateCategoryDetail> response = api.searchStateCategories(stateCategorySearch);
        // TODO: test validations
    }

    /**
     * Search state reasons on one or more lines
     *
     * Returns the state reasons defined on every line in &#x60;lines&#x60; (required; each by &#x60;id&#x60;, or by &#x60;name&#x60; plus &#x60;factory&#x60;), optionally restricted to one &#x60;category&#x60; (by &#x60;id&#x60; or &#x60;name&#x60;). Each result names its line, so one request can audit a whole fleet. Inactive reasons are included, with &#x60;active: false&#x60;. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void searchStateReasonsTest() throws ApiException {
        StateReasonSearch stateReasonSearch = null;
        List<StateReasonDetail> response = api.searchStateReasons(stateReasonSearch);
        // TODO: test validations
    }

    /**
     * Create or update a state category
     *
     * **Create** (no &#x60;id&#x60;): &#x60;name&#x60; and &#x60;type&#x60; are required. If a category with that &#x60;name&#x60; already exists for your organization and has the same &#x60;type&#x60;, it is returned unchanged; a different &#x60;type&#x60; is a 409.  **Update** (with &#x60;id&#x60;): fields you omit keep their current value. Built-in categories (no organization) cannot be updated.  Requires an organization admin token. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void setStateCategoryTest() throws ApiException {
        StateCategorySet stateCategorySet = null;
        StateCategoryDetail response = api.setStateCategory(stateCategorySet);
        // TODO: test validations
    }

    /**
     * Create a state reason on one or more lines
     *
     * Ensures the reason &#x60;name&#x60; exists under &#x60;category&#x60; on every line in &#x60;lines&#x60;. Lines that already have it are returned as they are; the rest are created in one transaction. The response has one entry per line, in request order, so calling this again is a no-op.  &#x60;category&#x60; may be given by &#x60;id&#x60; or &#x60;name&#x60; and must be one of your organization&#39;s own categories; Oden&#39;s built-in categories are read-only. &#x60;active&#x60; defaults to true and also re-activates an existing inactive reason (or deactivates with &#x60;false&#x60;).  Requires an organization admin token. 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void setStateReasonsTest() throws ApiException {
        StateReasonSet stateReasonSet = null;
        List<StateReasonDetail> response = api.setStateReasons(stateReasonSet);
        // TODO: test validations
    }

}
