

# DashboardExecuteFilters

Optional filter overrides applied to every module. Every field is optional; omitting one means \"no override on that dimension\". 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**lines** | [**List&lt;DashboardExecuteFiltersLinesInner&gt;**](DashboardExecuteFiltersLinesInner.md) | Lines to restrict to. Each entry must supply &#x60;id&#x60;, &#x60;name&#x60;, or both; entries that supply neither are rejected. Other Line fields (factory, secondary_name, match) are not used here and are intentionally omitted so generated clients don&#39;t suggest them as inputs.  |  [optional] |
|**shifts** | **List&lt;Integer&gt;** |  |  [optional] |
|**productIds** | **List&lt;UUID&gt;** |  |  [optional] |
|**productAttributeValueIds** | **List&lt;UUID&gt;** |  |  [optional] |
|**scrapCategories** | **List&lt;UUID&gt;** |  |  [optional] |
|**states** | [**DashboardExecuteFiltersStates**](DashboardExecuteFiltersStates.md) |  |  [optional] |
|**customIntervals** | [**List&lt;DashboardExecuteFiltersCustomIntervalsInner&gt;**](DashboardExecuteFiltersCustomIntervalsInner.md) |  |  [optional] |



