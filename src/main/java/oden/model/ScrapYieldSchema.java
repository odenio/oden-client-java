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


package oden.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import oden.model.Factory;
import oden.model.Unit;

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
 * Scrap yield schema represents a factory&#39;s scrap/yield data ingestion configuration. 
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-10-22T02:05:12.729237221Z[Etc/UTC]", comments = "Generator version: 7.15.0")
public class ScrapYieldSchema {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nullable
  private UUID id;

  public static final String SERIALIZED_NAME_FACTORY = "factory";
  @SerializedName(SERIALIZED_NAME_FACTORY)
  @javax.annotation.Nullable
  private Factory factory;

  public static final String SERIALIZED_NAME_SCRAP_CONVERSION_FACTOR = "scrap_conversion_factor";
  @SerializedName(SERIALIZED_NAME_SCRAP_CONVERSION_FACTOR)
  @javax.annotation.Nullable
  private Double scrapConversionFactor;

  public static final String SERIALIZED_NAME_SCRAP_UNIT = "scrap_unit";
  @SerializedName(SERIALIZED_NAME_SCRAP_UNIT)
  @javax.annotation.Nullable
  private Unit scrapUnit;

  public static final String SERIALIZED_NAME_YIELD_CONVERSION_FACTOR = "yield_conversion_factor";
  @SerializedName(SERIALIZED_NAME_YIELD_CONVERSION_FACTOR)
  @javax.annotation.Nullable
  private Double yieldConversionFactor;

  public static final String SERIALIZED_NAME_YIELD_UNIT = "yield_unit";
  @SerializedName(SERIALIZED_NAME_YIELD_UNIT)
  @javax.annotation.Nullable
  private Unit yieldUnit;

  public ScrapYieldSchema() {
  }

  public ScrapYieldSchema id(@javax.annotation.Nullable UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @javax.annotation.Nullable
  public UUID getId() {
    return id;
  }

  public void setId(@javax.annotation.Nullable UUID id) {
    this.id = id;
  }


  public ScrapYieldSchema factory(@javax.annotation.Nullable Factory factory) {
    this.factory = factory;
    return this;
  }

  /**
   * Get factory
   * @return factory
   */
  @javax.annotation.Nullable
  public Factory getFactory() {
    return factory;
  }

  public void setFactory(@javax.annotation.Nullable Factory factory) {
    this.factory = factory;
  }


  public ScrapYieldSchema scrapConversionFactor(@javax.annotation.Nullable Double scrapConversionFactor) {
    this.scrapConversionFactor = scrapConversionFactor;
    return this;
  }

  /**
   * Get scrapConversionFactor
   * @return scrapConversionFactor
   */
  @javax.annotation.Nullable
  public Double getScrapConversionFactor() {
    return scrapConversionFactor;
  }

  public void setScrapConversionFactor(@javax.annotation.Nullable Double scrapConversionFactor) {
    this.scrapConversionFactor = scrapConversionFactor;
  }


  public ScrapYieldSchema scrapUnit(@javax.annotation.Nullable Unit scrapUnit) {
    this.scrapUnit = scrapUnit;
    return this;
  }

  /**
   * Get scrapUnit
   * @return scrapUnit
   */
  @javax.annotation.Nullable
  public Unit getScrapUnit() {
    return scrapUnit;
  }

  public void setScrapUnit(@javax.annotation.Nullable Unit scrapUnit) {
    this.scrapUnit = scrapUnit;
  }


  public ScrapYieldSchema yieldConversionFactor(@javax.annotation.Nullable Double yieldConversionFactor) {
    this.yieldConversionFactor = yieldConversionFactor;
    return this;
  }

  /**
   * Get yieldConversionFactor
   * @return yieldConversionFactor
   */
  @javax.annotation.Nullable
  public Double getYieldConversionFactor() {
    return yieldConversionFactor;
  }

  public void setYieldConversionFactor(@javax.annotation.Nullable Double yieldConversionFactor) {
    this.yieldConversionFactor = yieldConversionFactor;
  }


  public ScrapYieldSchema yieldUnit(@javax.annotation.Nullable Unit yieldUnit) {
    this.yieldUnit = yieldUnit;
    return this;
  }

  /**
   * Get yieldUnit
   * @return yieldUnit
   */
  @javax.annotation.Nullable
  public Unit getYieldUnit() {
    return yieldUnit;
  }

  public void setYieldUnit(@javax.annotation.Nullable Unit yieldUnit) {
    this.yieldUnit = yieldUnit;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScrapYieldSchema scrapYieldSchema = (ScrapYieldSchema) o;
    return Objects.equals(this.id, scrapYieldSchema.id) &&
        Objects.equals(this.factory, scrapYieldSchema.factory) &&
        Objects.equals(this.scrapConversionFactor, scrapYieldSchema.scrapConversionFactor) &&
        Objects.equals(this.scrapUnit, scrapYieldSchema.scrapUnit) &&
        Objects.equals(this.yieldConversionFactor, scrapYieldSchema.yieldConversionFactor) &&
        Objects.equals(this.yieldUnit, scrapYieldSchema.yieldUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, factory, scrapConversionFactor, scrapUnit, yieldConversionFactor, yieldUnit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScrapYieldSchema {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    factory: ").append(toIndentedString(factory)).append("\n");
    sb.append("    scrapConversionFactor: ").append(toIndentedString(scrapConversionFactor)).append("\n");
    sb.append("    scrapUnit: ").append(toIndentedString(scrapUnit)).append("\n");
    sb.append("    yieldConversionFactor: ").append(toIndentedString(yieldConversionFactor)).append("\n");
    sb.append("    yieldUnit: ").append(toIndentedString(yieldUnit)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>(Arrays.asList("id", "factory", "scrap_conversion_factor", "scrap_unit", "yield_conversion_factor", "yield_unit"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to ScrapYieldSchema
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ScrapYieldSchema.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ScrapYieldSchema is not found in the empty JSON string", ScrapYieldSchema.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ScrapYieldSchema.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ScrapYieldSchema` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("id") != null && !jsonObj.get("id").isJsonNull()) && !jsonObj.get("id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("id").toString()));
      }
      // validate the optional field `factory`
      if (jsonObj.get("factory") != null && !jsonObj.get("factory").isJsonNull()) {
        Factory.validateJsonElement(jsonObj.get("factory"));
      }
      // validate the optional field `scrap_unit`
      if (jsonObj.get("scrap_unit") != null && !jsonObj.get("scrap_unit").isJsonNull()) {
        Unit.validateJsonElement(jsonObj.get("scrap_unit"));
      }
      // validate the optional field `yield_unit`
      if (jsonObj.get("yield_unit") != null && !jsonObj.get("yield_unit").isJsonNull()) {
        Unit.validateJsonElement(jsonObj.get("yield_unit"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ScrapYieldSchema.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ScrapYieldSchema' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ScrapYieldSchema> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ScrapYieldSchema.class));

       return (TypeAdapter<T>) new TypeAdapter<ScrapYieldSchema>() {
           @Override
           public void write(JsonWriter out, ScrapYieldSchema value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ScrapYieldSchema read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of ScrapYieldSchema given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ScrapYieldSchema
   * @throws IOException if the JSON string is invalid with respect to ScrapYieldSchema
   */
  public static ScrapYieldSchema fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ScrapYieldSchema.class);
  }

  /**
   * Convert an instance of ScrapYieldSchema to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

