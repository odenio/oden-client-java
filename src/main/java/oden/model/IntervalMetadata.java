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
import oden.model.BatchMetadata;
import oden.model.Interval;
import oden.model.Product;
import oden.model.RunMetadata;
import oden.model.StateCategory;
import oden.model.StateMetadata;
import oden.model.StateReason;
import oden.model.Target;



import java.io.IOException;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

import oden.JSON;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-10-03T04:36:32.429490336Z[Etc/UTC]", comments = "Generator version: 7.15.0")
public class IntervalMetadata extends AbstractOpenApiSchema {
    private static final Logger log = Logger.getLogger(IntervalMetadata.class.getName());

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!IntervalMetadata.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'IntervalMetadata' and its subtypes
            }
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<BatchMetadata> adapterBatchMetadata = gson.getDelegateAdapter(this, TypeToken.get(BatchMetadata.class));
            final TypeAdapter<RunMetadata> adapterRunMetadata = gson.getDelegateAdapter(this, TypeToken.get(RunMetadata.class));
            final TypeAdapter<StateMetadata> adapterStateMetadata = gson.getDelegateAdapter(this, TypeToken.get(StateMetadata.class));
            final TypeAdapter<Object> adapterObject = gson.getDelegateAdapter(this, TypeToken.get(Object.class));

            return (TypeAdapter<T>) new TypeAdapter<IntervalMetadata>() {
                @Override
                public void write(JsonWriter out, IntervalMetadata value) throws IOException {
                    if (value == null || value.getActualInstance() == null) {
                        elementAdapter.write(out, null);
                        return;
                    }

                    // check if the actual instance is of the type `BatchMetadata`
                    if (value.getActualInstance() instanceof BatchMetadata) {
                        JsonElement element = adapterBatchMetadata.toJsonTree((BatchMetadata)value.getActualInstance());
                        elementAdapter.write(out, element);
                        return;
                    }
                    // check if the actual instance is of the type `RunMetadata`
                    if (value.getActualInstance() instanceof RunMetadata) {
                        JsonElement element = adapterRunMetadata.toJsonTree((RunMetadata)value.getActualInstance());
                        elementAdapter.write(out, element);
                        return;
                    }
                    // check if the actual instance is of the type `StateMetadata`
                    if (value.getActualInstance() instanceof StateMetadata) {
                        JsonElement element = adapterStateMetadata.toJsonTree((StateMetadata)value.getActualInstance());
                        elementAdapter.write(out, element);
                        return;
                    }
                    // check if the actual instance is of the type `Object`
                    if (value.getActualInstance() instanceof Object) {
                        JsonPrimitive primitive = adapterObject.toJsonTree((Object)value.getActualInstance()).getAsJsonPrimitive();
                        elementAdapter.write(out, primitive);
                        return;
                    }
                    throw new IOException("Failed to serialize as the type doesn't match oneOf schemas: BatchMetadata, Object, RunMetadata, StateMetadata");
                }

                @Override
                public IntervalMetadata read(JsonReader in) throws IOException {
                    Object deserialized = null;
                    JsonElement jsonElement = elementAdapter.read(in);

                    int match = 0;
                    ArrayList<String> errorMessages = new ArrayList<>();
                    TypeAdapter actualAdapter = elementAdapter;

                    // deserialize BatchMetadata
                    try {
                        // validate the JSON object to see if any exception is thrown
                        BatchMetadata.validateJsonElement(jsonElement);
                        actualAdapter = adapterBatchMetadata;
                        match++;
                        log.log(Level.FINER, "Input data matches schema 'BatchMetadata'");
                    } catch (Exception e) {
                        // deserialization failed, continue
                        errorMessages.add(String.format("Deserialization for BatchMetadata failed with `%s`.", e.getMessage()));
                        log.log(Level.FINER, "Input data does not match schema 'BatchMetadata'", e);
                    }
                    // deserialize RunMetadata
                    try {
                        // validate the JSON object to see if any exception is thrown
                        RunMetadata.validateJsonElement(jsonElement);
                        actualAdapter = adapterRunMetadata;
                        match++;
                        log.log(Level.FINER, "Input data matches schema 'RunMetadata'");
                    } catch (Exception e) {
                        // deserialization failed, continue
                        errorMessages.add(String.format("Deserialization for RunMetadata failed with `%s`.", e.getMessage()));
                        log.log(Level.FINER, "Input data does not match schema 'RunMetadata'", e);
                    }
                    // deserialize StateMetadata
                    try {
                        // validate the JSON object to see if any exception is thrown
                        StateMetadata.validateJsonElement(jsonElement);
                        actualAdapter = adapterStateMetadata;
                        match++;
                        log.log(Level.FINER, "Input data matches schema 'StateMetadata'");
                    } catch (Exception e) {
                        // deserialization failed, continue
                        errorMessages.add(String.format("Deserialization for StateMetadata failed with `%s`.", e.getMessage()));
                        log.log(Level.FINER, "Input data does not match schema 'StateMetadata'", e);
                    }
                    // deserialize Object
                    try {
                        // validate the JSON object to see if any exception is thrown
                        if (!jsonElement.getAsJsonPrimitive().isNumber()) {
                            throw new IllegalArgumentException(String.format("Expected json element to be of type Number in the JSON string but got `%s`", jsonElement.toString()));
                        }
                        actualAdapter = adapterObject;
                        match++;
                        log.log(Level.FINER, "Input data matches schema 'Object'");
                    } catch (Exception e) {
                        // deserialization failed, continue
                        errorMessages.add(String.format("Deserialization for Object failed with `%s`.", e.getMessage()));
                        log.log(Level.FINER, "Input data does not match schema 'Object'", e);
                    }

                    if (match == 1) {
                        IntervalMetadata ret = new IntervalMetadata();
                        ret.setActualInstance(actualAdapter.fromJsonTree(jsonElement));
                        return ret;
                    }

                    throw new IOException(String.format("Failed deserialization for IntervalMetadata: %d classes match result, expected 1. Detailed failure message for oneOf schemas: %s. JSON: %s", match, errorMessages, jsonElement.toString()));
                }
            }.nullSafe();
        }
    }

    // store a list of schema names defined in oneOf
    public static final Map<String, Class<?>> schemas = new HashMap<String, Class<?>>();

    public IntervalMetadata() {
        super("oneOf", Boolean.FALSE);
    }

    public IntervalMetadata(Object o) {
        super("oneOf", Boolean.FALSE);
        setActualInstance(o);
    }

    static {
        schemas.put("BatchMetadata", BatchMetadata.class);
        schemas.put("RunMetadata", RunMetadata.class);
        schemas.put("StateMetadata", StateMetadata.class);
        schemas.put("Object", Object.class);
    }

    @Override
    public Map<String, Class<?>> getSchemas() {
        return IntervalMetadata.schemas;
    }

    /**
     * Set the instance that matches the oneOf child schema, check
     * the instance parameter is valid against the oneOf child schemas:
     * BatchMetadata, Object, RunMetadata, StateMetadata
     *
     * It could be an instance of the 'oneOf' schemas.
     */
    @Override
    public void setActualInstance(Object instance) {
        if (instance instanceof BatchMetadata) {
            super.setActualInstance(instance);
            return;
        }

        if (instance instanceof RunMetadata) {
            super.setActualInstance(instance);
            return;
        }

        if (instance instanceof StateMetadata) {
            super.setActualInstance(instance);
            return;
        }

        if (instance instanceof Object) {
            super.setActualInstance(instance);
            return;
        }

        throw new RuntimeException("Invalid instance type. Must be BatchMetadata, Object, RunMetadata, StateMetadata");
    }

    /**
     * Get the actual instance, which can be the following:
     * BatchMetadata, Object, RunMetadata, StateMetadata
     *
     * @return The actual instance (BatchMetadata, Object, RunMetadata, StateMetadata)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object getActualInstance() {
        return super.getActualInstance();
    }

    /**
     * Get the actual instance of `BatchMetadata`. If the actual instance is not `BatchMetadata`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `BatchMetadata`
     * @throws ClassCastException if the instance is not `BatchMetadata`
     */
    public BatchMetadata getBatchMetadata() throws ClassCastException {
        return (BatchMetadata)super.getActualInstance();
    }

    /**
     * Get the actual instance of `RunMetadata`. If the actual instance is not `RunMetadata`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `RunMetadata`
     * @throws ClassCastException if the instance is not `RunMetadata`
     */
    public RunMetadata getRunMetadata() throws ClassCastException {
        return (RunMetadata)super.getActualInstance();
    }

    /**
     * Get the actual instance of `StateMetadata`. If the actual instance is not `StateMetadata`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `StateMetadata`
     * @throws ClassCastException if the instance is not `StateMetadata`
     */
    public StateMetadata getStateMetadata() throws ClassCastException {
        return (StateMetadata)super.getActualInstance();
    }

    /**
     * Get the actual instance of `Object`. If the actual instance is not `Object`,
     * the ClassCastException will be thrown.
     *
     * @return The actual instance of `Object`
     * @throws ClassCastException if the instance is not `Object`
     */
    public Object getObject() throws ClassCastException {
        return (Object)super.getActualInstance();
    }

    /**
     * Validates the JSON Element and throws an exception if issues found
     *
     * @param jsonElement JSON Element
     * @throws IOException if the JSON Element is invalid with respect to IntervalMetadata
     */
    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
        // validate oneOf schemas one by one
        int validCount = 0;
        ArrayList<String> errorMessages = new ArrayList<>();
        // validate the json string with BatchMetadata
        try {
            BatchMetadata.validateJsonElement(jsonElement);
            validCount++;
        } catch (Exception e) {
            errorMessages.add(String.format("Deserialization for BatchMetadata failed with `%s`.", e.getMessage()));
            // continue to the next one
        }
        // validate the json string with RunMetadata
        try {
            RunMetadata.validateJsonElement(jsonElement);
            validCount++;
        } catch (Exception e) {
            errorMessages.add(String.format("Deserialization for RunMetadata failed with `%s`.", e.getMessage()));
            // continue to the next one
        }
        // validate the json string with StateMetadata
        try {
            StateMetadata.validateJsonElement(jsonElement);
            validCount++;
        } catch (Exception e) {
            errorMessages.add(String.format("Deserialization for StateMetadata failed with `%s`.", e.getMessage()));
            // continue to the next one
        }
        // validate the json string with Object
        try {
            if (!jsonElement.getAsJsonPrimitive().isNumber()) {
                throw new IllegalArgumentException(String.format("Expected json element to be of type Number in the JSON string but got `%s`", jsonElement.toString()));
            }
            validCount++;
        } catch (Exception e) {
            errorMessages.add(String.format("Deserialization for Object failed with `%s`.", e.getMessage()));
            // continue to the next one
        }
        if (validCount != 1) {
            throw new IOException(String.format("The JSON string is invalid for IntervalMetadata with oneOf schemas: BatchMetadata, Object, RunMetadata, StateMetadata. %d class(es) match the result, expected 1. Detailed failure message for oneOf schemas: %s. JSON: %s", validCount, errorMessages, jsonElement.toString()));
        }
    }

    /**
     * Create an instance of IntervalMetadata given an JSON string
     *
     * @param jsonString JSON string
     * @return An instance of IntervalMetadata
     * @throws IOException if the JSON string is invalid with respect to IntervalMetadata
     */
    public static IntervalMetadata fromJson(String jsonString) throws IOException {
        return JSON.getGson().fromJson(jsonString, IntervalMetadata.class);
    }

    /**
     * Convert an instance of IntervalMetadata to an JSON string
     *
     * @return JSON string
     */
    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}

