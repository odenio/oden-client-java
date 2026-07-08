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


package oden.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import oden.model.DashboardColumnSpec;
import oden.model.DashboardExecuteResultRange;
import org.openapitools.jackson.nullable.JsonNullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oden.JSON;

/**
 * Executed output of a single dashboard module.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-07-08T05:17:44.787119627Z[Etc/UTC]", comments = "Generator version: 7.23.0")
public class DashboardExecuteResult {
  public static final String SERIALIZED_NAME_MODULE_ID = "module_id";
  @SerializedName(SERIALIZED_NAME_MODULE_ID)
  @javax.annotation.Nonnull
  private UUID moduleId;

  public static final String SERIALIZED_NAME_MODULE_NAME = "module_name";
  @SerializedName(SERIALIZED_NAME_MODULE_NAME)
  @javax.annotation.Nonnull
  private String moduleName;

  public static final String SERIALIZED_NAME_MODULE_TYPE = "module_type";
  @SerializedName(SERIALIZED_NAME_MODULE_TYPE)
  @javax.annotation.Nonnull
  private String moduleType;

  public static final String SERIALIZED_NAME_RANGE = "range";
  @SerializedName(SERIALIZED_NAME_RANGE)
  @javax.annotation.Nullable
  private DashboardExecuteResultRange range;

  public static final String SERIALIZED_NAME_FILTERS_APPLIED = "filters_applied";
  @SerializedName(SERIALIZED_NAME_FILTERS_APPLIED)
  @javax.annotation.Nullable
  private Map<String, Object> filtersApplied = new HashMap<>();

  public static final String SERIALIZED_NAME_COLUMNS = "columns";
  @SerializedName(SERIALIZED_NAME_COLUMNS)
  @javax.annotation.Nullable
  private List<DashboardColumnSpec> columns;

  public static final String SERIALIZED_NAME_ROWS = "rows";
  @SerializedName(SERIALIZED_NAME_ROWS)
  @javax.annotation.Nullable
  private List<Map<String, Object>> rows;

  public static final String SERIALIZED_NAME_ERROR = "error";
  @SerializedName(SERIALIZED_NAME_ERROR)
  @javax.annotation.Nullable
  private String error;

  public DashboardExecuteResult() {
  }

  public DashboardExecuteResult moduleId(@javax.annotation.Nonnull UUID moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  /**
   * Get moduleId
   * @return moduleId
   */
  @javax.annotation.Nonnull
  public UUID getModuleId() {
    return moduleId;
  }

  public void setModuleId(@javax.annotation.Nonnull UUID moduleId) {
    this.moduleId = moduleId;
  }


  public DashboardExecuteResult moduleName(@javax.annotation.Nonnull String moduleName) {
    this.moduleName = moduleName;
    return this;
  }

  /**
   * Get moduleName
   * @return moduleName
   */
  @javax.annotation.Nonnull
  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(@javax.annotation.Nonnull String moduleName) {
    this.moduleName = moduleName;
  }


  public DashboardExecuteResult moduleType(@javax.annotation.Nonnull String moduleType) {
    this.moduleType = moduleType;
    return this;
  }

  /**
   * The module&#39;s stored visualization (e.g. &#x60;table&#x60;, &#x60;line_chart&#x60;, &#x60;bar_chart&#x60;). Type label only — does not change the response shape. 
   * @return moduleType
   */
  @javax.annotation.Nonnull
  public String getModuleType() {
    return moduleType;
  }

  public void setModuleType(@javax.annotation.Nonnull String moduleType) {
    this.moduleType = moduleType;
  }


  public DashboardExecuteResult range(@javax.annotation.Nullable DashboardExecuteResultRange range) {
    this.range = range;
    return this;
  }

  /**
   * Get range
   * @return range
   */
  @javax.annotation.Nullable
  public DashboardExecuteResultRange getRange() {
    return range;
  }

  public void setRange(@javax.annotation.Nullable DashboardExecuteResultRange range) {
    this.range = range;
  }


  public DashboardExecuteResult filtersApplied(@javax.annotation.Nullable Map<String, Object> filtersApplied) {
    this.filtersApplied = filtersApplied;
    return this;
  }

  public DashboardExecuteResult putFiltersAppliedItem(String key, Object filtersAppliedItem) {
    if (this.filtersApplied == null) {
      this.filtersApplied = new HashMap<>();
    }
    this.filtersApplied.put(key, filtersAppliedItem);
    return this;
  }

  /**
   * Echo of the filter dimensions that were applied, resolved to human-readable values where possible (e.g. line names instead of IDs). 
   * @return filtersApplied
   */
  @javax.annotation.Nullable
  public Map<String, Object> getFiltersApplied() {
    return filtersApplied;
  }

  public void setFiltersApplied(@javax.annotation.Nullable Map<String, Object> filtersApplied) {
    this.filtersApplied = filtersApplied;
  }


  public DashboardExecuteResult columns(@javax.annotation.Nullable List<DashboardColumnSpec> columns) {
    this.columns = columns;
    return this;
  }

  public DashboardExecuteResult addColumnsItem(DashboardColumnSpec columnsItem) {
    if (this.columns == null) {
      this.columns = new ArrayList<>();
    }
    this.columns.add(columnsItem);
    return this;
  }

  /**
   * Column metadata. &#x60;type&#x60; is derived from the first non-null cell in the column. Null when the module errored. 
   * @return columns
   */
  @javax.annotation.Nullable
  public List<DashboardColumnSpec> getColumns() {
    return columns;
  }

  public void setColumns(@javax.annotation.Nullable List<DashboardColumnSpec> columns) {
    this.columns = columns;
  }


  public DashboardExecuteResult rows(@javax.annotation.Nullable List<Map<String, Object>> rows) {
    this.rows = rows;
    return this;
  }

  public DashboardExecuteResult addRowsItem(Map<String, Object> rowsItem) {
    if (this.rows == null) {
      this.rows = new ArrayList<>();
    }
    this.rows.add(rowsItem);
    return this;
  }

  /**
   * Row data as objects keyed by column name (not positional arrays). Values are typed JSON natively. Null when the module errored. 
   * @return rows
   */
  @javax.annotation.Nullable
  public List<Map<String, Object>> getRows() {
    return rows;
  }

  public void setRows(@javax.annotation.Nullable List<Map<String, Object>> rows) {
    this.rows = rows;
  }


  public DashboardExecuteResult error(@javax.annotation.Nullable String error) {
    this.error = error;
    return this;
  }

  /**
   * Set to a short message when the module failed to parse or execute. Null on success. 
   * @return error
   */
  @javax.annotation.Nullable
  public String getError() {
    return error;
  }

  public void setError(@javax.annotation.Nullable String error) {
    this.error = error;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardExecuteResult dashboardExecuteResult = (DashboardExecuteResult) o;
    return Objects.equals(this.moduleId, dashboardExecuteResult.moduleId) &&
        Objects.equals(this.moduleName, dashboardExecuteResult.moduleName) &&
        Objects.equals(this.moduleType, dashboardExecuteResult.moduleType) &&
        Objects.equals(this.range, dashboardExecuteResult.range) &&
        Objects.equals(this.filtersApplied, dashboardExecuteResult.filtersApplied) &&
        Objects.equals(this.columns, dashboardExecuteResult.columns) &&
        Objects.equals(this.rows, dashboardExecuteResult.rows) &&
        Objects.equals(this.error, dashboardExecuteResult.error);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(moduleId, moduleName, moduleType, range, filtersApplied, columns, rows, error);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardExecuteResult {\n");
    sb.append("    moduleId: ").append(toIndentedString(moduleId)).append("\n");
    sb.append("    moduleName: ").append(toIndentedString(moduleName)).append("\n");
    sb.append("    moduleType: ").append(toIndentedString(moduleType)).append("\n");
    sb.append("    range: ").append(toIndentedString(range)).append("\n");
    sb.append("    filtersApplied: ").append(toIndentedString(filtersApplied)).append("\n");
    sb.append("    columns: ").append(toIndentedString(columns)).append("\n");
    sb.append("    rows: ").append(toIndentedString(rows)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    return o == null ? "null" : o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>(Arrays.asList("module_id", "module_name", "module_type", "range", "filters_applied", "columns", "rows", "error"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("module_id", "module_name", "module_type"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to DashboardExecuteResult
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!DashboardExecuteResult.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field(s) %s in DashboardExecuteResult is not found in the empty JSON string", DashboardExecuteResult.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!DashboardExecuteResult.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The field `%s` in the JSON string is not defined in the `DashboardExecuteResult` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : DashboardExecuteResult.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("module_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `module_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("module_id").toString()));
      }
      if (!jsonObj.get("module_name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `module_name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("module_name").toString()));
      }
      if (!jsonObj.get("module_type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `module_type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("module_type").toString()));
      }
      // validate the optional field `range`
      if (jsonObj.get("range") != null && !jsonObj.get("range").isJsonNull()) {
        DashboardExecuteResultRange.validateJsonElement(jsonObj.get("range"));
      }
      if (jsonObj.get("columns") != null && !jsonObj.get("columns").isJsonNull()) {
        JsonArray jsonArraycolumns = jsonObj.getAsJsonArray("columns");
        if (jsonArraycolumns != null) {
          // ensure the json data is an array
          if (!jsonObj.get("columns").isJsonArray()) {
            throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `columns` to be an array in the JSON string but got `%s`", jsonObj.get("columns").toString()));
          }

          // validate the optional field `columns` (array)
          for (int i = 0; i < jsonArraycolumns.size(); i++) {
            DashboardColumnSpec.validateJsonElement(jsonArraycolumns.get(i));
          };
        }
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("rows") != null && !jsonObj.get("rows").isJsonNull() && !jsonObj.get("rows").isJsonArray()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `rows` to be an array in the JSON string but got `%s`", jsonObj.get("rows").toString()));
      }
      if ((jsonObj.get("error") != null && !jsonObj.get("error").isJsonNull()) && !jsonObj.get("error").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `error` to be a primitive type in the JSON string but got `%s`", jsonObj.get("error").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!DashboardExecuteResult.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'DashboardExecuteResult' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<DashboardExecuteResult> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(DashboardExecuteResult.class));

       return (TypeAdapter<T>) new TypeAdapter<DashboardExecuteResult>() {
           @Override
           public void write(JsonWriter out, DashboardExecuteResult value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public DashboardExecuteResult read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of DashboardExecuteResult given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of DashboardExecuteResult
   * @throws IOException if the JSON string is invalid with respect to DashboardExecuteResult
   */
  public static DashboardExecuteResult fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, DashboardExecuteResult.class);
  }

  /**
   * Convert an instance of DashboardExecuteResult to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

