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
import java.util.List;
import java.util.UUID;
import oden.model.DashboardExecuteFiltersCustomIntervalsInner;
import oden.model.DashboardExecuteFiltersLinesInner;
import oden.model.DashboardExecuteFiltersStates;

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
 * Optional filter overrides applied to every module. Every field is optional; omitting one means \&quot;no override on that dimension\&quot;. 
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2026-07-08T05:17:44.787119627Z[Etc/UTC]", comments = "Generator version: 7.23.0")
public class DashboardExecuteFilters {
  public static final String SERIALIZED_NAME_LINES = "lines";
  @SerializedName(SERIALIZED_NAME_LINES)
  @javax.annotation.Nullable
  private List<DashboardExecuteFiltersLinesInner> lines = new ArrayList<>();

  public static final String SERIALIZED_NAME_SHIFTS = "shifts";
  @SerializedName(SERIALIZED_NAME_SHIFTS)
  @javax.annotation.Nullable
  private List<Integer> shifts = new ArrayList<>();

  public static final String SERIALIZED_NAME_PRODUCT_IDS = "product_ids";
  @SerializedName(SERIALIZED_NAME_PRODUCT_IDS)
  @javax.annotation.Nullable
  private List<UUID> productIds = new ArrayList<>();

  public static final String SERIALIZED_NAME_PRODUCT_ATTRIBUTE_VALUE_IDS = "product_attribute_value_ids";
  @SerializedName(SERIALIZED_NAME_PRODUCT_ATTRIBUTE_VALUE_IDS)
  @javax.annotation.Nullable
  private List<UUID> productAttributeValueIds = new ArrayList<>();

  public static final String SERIALIZED_NAME_SCRAP_CATEGORIES = "scrap_categories";
  @SerializedName(SERIALIZED_NAME_SCRAP_CATEGORIES)
  @javax.annotation.Nullable
  private List<UUID> scrapCategories = new ArrayList<>();

  public static final String SERIALIZED_NAME_STATES = "states";
  @SerializedName(SERIALIZED_NAME_STATES)
  @javax.annotation.Nullable
  private DashboardExecuteFiltersStates states;

  public static final String SERIALIZED_NAME_CUSTOM_INTERVALS = "custom_intervals";
  @SerializedName(SERIALIZED_NAME_CUSTOM_INTERVALS)
  @javax.annotation.Nullable
  private List<DashboardExecuteFiltersCustomIntervalsInner> customIntervals = new ArrayList<>();

  public DashboardExecuteFilters() {
  }

  public DashboardExecuteFilters lines(@javax.annotation.Nullable List<DashboardExecuteFiltersLinesInner> lines) {
    this.lines = lines;
    return this;
  }

  public DashboardExecuteFilters addLinesItem(DashboardExecuteFiltersLinesInner linesItem) {
    if (this.lines == null) {
      this.lines = new ArrayList<>();
    }
    this.lines.add(linesItem);
    return this;
  }

  /**
   * Lines to restrict to. Each entry must supply &#x60;id&#x60;, &#x60;name&#x60;, or both; entries that supply neither are rejected. Other Line fields (factory, secondary_name, match) are not used here and are intentionally omitted so generated clients don&#39;t suggest them as inputs. 
   * @return lines
   */
  @javax.annotation.Nullable
  public List<DashboardExecuteFiltersLinesInner> getLines() {
    return lines;
  }

  public void setLines(@javax.annotation.Nullable List<DashboardExecuteFiltersLinesInner> lines) {
    this.lines = lines;
  }


  public DashboardExecuteFilters shifts(@javax.annotation.Nullable List<Integer> shifts) {
    this.shifts = shifts;
    return this;
  }

  public DashboardExecuteFilters addShiftsItem(Integer shiftsItem) {
    if (this.shifts == null) {
      this.shifts = new ArrayList<>();
    }
    this.shifts.add(shiftsItem);
    return this;
  }

  /**
   * Get shifts
   * @return shifts
   */
  @javax.annotation.Nullable
  public List<Integer> getShifts() {
    return shifts;
  }

  public void setShifts(@javax.annotation.Nullable List<Integer> shifts) {
    this.shifts = shifts;
  }


  public DashboardExecuteFilters productIds(@javax.annotation.Nullable List<UUID> productIds) {
    this.productIds = productIds;
    return this;
  }

  public DashboardExecuteFilters addProductIdsItem(UUID productIdsItem) {
    if (this.productIds == null) {
      this.productIds = new ArrayList<>();
    }
    this.productIds.add(productIdsItem);
    return this;
  }

  /**
   * Get productIds
   * @return productIds
   */
  @javax.annotation.Nullable
  public List<UUID> getProductIds() {
    return productIds;
  }

  public void setProductIds(@javax.annotation.Nullable List<UUID> productIds) {
    this.productIds = productIds;
  }


  public DashboardExecuteFilters productAttributeValueIds(@javax.annotation.Nullable List<UUID> productAttributeValueIds) {
    this.productAttributeValueIds = productAttributeValueIds;
    return this;
  }

  public DashboardExecuteFilters addProductAttributeValueIdsItem(UUID productAttributeValueIdsItem) {
    if (this.productAttributeValueIds == null) {
      this.productAttributeValueIds = new ArrayList<>();
    }
    this.productAttributeValueIds.add(productAttributeValueIdsItem);
    return this;
  }

  /**
   * Get productAttributeValueIds
   * @return productAttributeValueIds
   */
  @javax.annotation.Nullable
  public List<UUID> getProductAttributeValueIds() {
    return productAttributeValueIds;
  }

  public void setProductAttributeValueIds(@javax.annotation.Nullable List<UUID> productAttributeValueIds) {
    this.productAttributeValueIds = productAttributeValueIds;
  }


  public DashboardExecuteFilters scrapCategories(@javax.annotation.Nullable List<UUID> scrapCategories) {
    this.scrapCategories = scrapCategories;
    return this;
  }

  public DashboardExecuteFilters addScrapCategoriesItem(UUID scrapCategoriesItem) {
    if (this.scrapCategories == null) {
      this.scrapCategories = new ArrayList<>();
    }
    this.scrapCategories.add(scrapCategoriesItem);
    return this;
  }

  /**
   * Get scrapCategories
   * @return scrapCategories
   */
  @javax.annotation.Nullable
  public List<UUID> getScrapCategories() {
    return scrapCategories;
  }

  public void setScrapCategories(@javax.annotation.Nullable List<UUID> scrapCategories) {
    this.scrapCategories = scrapCategories;
  }


  public DashboardExecuteFilters states(@javax.annotation.Nullable DashboardExecuteFiltersStates states) {
    this.states = states;
    return this;
  }

  /**
   * Get states
   * @return states
   */
  @javax.annotation.Nullable
  public DashboardExecuteFiltersStates getStates() {
    return states;
  }

  public void setStates(@javax.annotation.Nullable DashboardExecuteFiltersStates states) {
    this.states = states;
  }


  public DashboardExecuteFilters customIntervals(@javax.annotation.Nullable List<DashboardExecuteFiltersCustomIntervalsInner> customIntervals) {
    this.customIntervals = customIntervals;
    return this;
  }

  public DashboardExecuteFilters addCustomIntervalsItem(DashboardExecuteFiltersCustomIntervalsInner customIntervalsItem) {
    if (this.customIntervals == null) {
      this.customIntervals = new ArrayList<>();
    }
    this.customIntervals.add(customIntervalsItem);
    return this;
  }

  /**
   * Get customIntervals
   * @return customIntervals
   */
  @javax.annotation.Nullable
  public List<DashboardExecuteFiltersCustomIntervalsInner> getCustomIntervals() {
    return customIntervals;
  }

  public void setCustomIntervals(@javax.annotation.Nullable List<DashboardExecuteFiltersCustomIntervalsInner> customIntervals) {
    this.customIntervals = customIntervals;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardExecuteFilters dashboardExecuteFilters = (DashboardExecuteFilters) o;
    return Objects.equals(this.lines, dashboardExecuteFilters.lines) &&
        Objects.equals(this.shifts, dashboardExecuteFilters.shifts) &&
        Objects.equals(this.productIds, dashboardExecuteFilters.productIds) &&
        Objects.equals(this.productAttributeValueIds, dashboardExecuteFilters.productAttributeValueIds) &&
        Objects.equals(this.scrapCategories, dashboardExecuteFilters.scrapCategories) &&
        Objects.equals(this.states, dashboardExecuteFilters.states) &&
        Objects.equals(this.customIntervals, dashboardExecuteFilters.customIntervals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lines, shifts, productIds, productAttributeValueIds, scrapCategories, states, customIntervals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardExecuteFilters {\n");
    sb.append("    lines: ").append(toIndentedString(lines)).append("\n");
    sb.append("    shifts: ").append(toIndentedString(shifts)).append("\n");
    sb.append("    productIds: ").append(toIndentedString(productIds)).append("\n");
    sb.append("    productAttributeValueIds: ").append(toIndentedString(productAttributeValueIds)).append("\n");
    sb.append("    scrapCategories: ").append(toIndentedString(scrapCategories)).append("\n");
    sb.append("    states: ").append(toIndentedString(states)).append("\n");
    sb.append("    customIntervals: ").append(toIndentedString(customIntervals)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("lines", "shifts", "product_ids", "product_attribute_value_ids", "scrap_categories", "states", "custom_intervals"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to DashboardExecuteFilters
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!DashboardExecuteFilters.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field(s) %s in DashboardExecuteFilters is not found in the empty JSON string", DashboardExecuteFilters.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!DashboardExecuteFilters.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The field `%s` in the JSON string is not defined in the `DashboardExecuteFilters` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (jsonObj.get("lines") != null && !jsonObj.get("lines").isJsonNull()) {
        JsonArray jsonArraylines = jsonObj.getAsJsonArray("lines");
        if (jsonArraylines != null) {
          // ensure the json data is an array
          if (!jsonObj.get("lines").isJsonArray()) {
            throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `lines` to be an array in the JSON string but got `%s`", jsonObj.get("lines").toString()));
          }

          // validate the optional field `lines` (array)
          for (int i = 0; i < jsonArraylines.size(); i++) {
            DashboardExecuteFiltersLinesInner.validateJsonElement(jsonArraylines.get(i));
          };
        }
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("shifts") != null && !jsonObj.get("shifts").isJsonNull() && !jsonObj.get("shifts").isJsonArray()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `shifts` to be an array in the JSON string but got `%s`", jsonObj.get("shifts").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("product_ids") != null && !jsonObj.get("product_ids").isJsonNull() && !jsonObj.get("product_ids").isJsonArray()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `product_ids` to be an array in the JSON string but got `%s`", jsonObj.get("product_ids").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("product_attribute_value_ids") != null && !jsonObj.get("product_attribute_value_ids").isJsonNull() && !jsonObj.get("product_attribute_value_ids").isJsonArray()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `product_attribute_value_ids` to be an array in the JSON string but got `%s`", jsonObj.get("product_attribute_value_ids").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("scrap_categories") != null && !jsonObj.get("scrap_categories").isJsonNull() && !jsonObj.get("scrap_categories").isJsonArray()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `scrap_categories` to be an array in the JSON string but got `%s`", jsonObj.get("scrap_categories").toString()));
      }
      // validate the optional field `states`
      if (jsonObj.get("states") != null && !jsonObj.get("states").isJsonNull()) {
        DashboardExecuteFiltersStates.validateJsonElement(jsonObj.get("states"));
      }
      if (jsonObj.get("custom_intervals") != null && !jsonObj.get("custom_intervals").isJsonNull()) {
        JsonArray jsonArraycustomIntervals = jsonObj.getAsJsonArray("custom_intervals");
        if (jsonArraycustomIntervals != null) {
          // ensure the json data is an array
          if (!jsonObj.get("custom_intervals").isJsonArray()) {
            throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `custom_intervals` to be an array in the JSON string but got `%s`", jsonObj.get("custom_intervals").toString()));
          }

          // validate the optional field `custom_intervals` (array)
          for (int i = 0; i < jsonArraycustomIntervals.size(); i++) {
            DashboardExecuteFiltersCustomIntervalsInner.validateJsonElement(jsonArraycustomIntervals.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!DashboardExecuteFilters.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'DashboardExecuteFilters' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<DashboardExecuteFilters> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(DashboardExecuteFilters.class));

       return (TypeAdapter<T>) new TypeAdapter<DashboardExecuteFilters>() {
           @Override
           public void write(JsonWriter out, DashboardExecuteFilters value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public DashboardExecuteFilters read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of DashboardExecuteFilters given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of DashboardExecuteFilters
   * @throws IOException if the JSON string is invalid with respect to DashboardExecuteFilters
   */
  public static DashboardExecuteFilters fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, DashboardExecuteFilters.class);
  }

  /**
   * Convert an instance of DashboardExecuteFilters to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

