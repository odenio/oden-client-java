

# DashboardExecuteResult

Executed output of a single dashboard module.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**moduleId** | **UUID** |  |  |
|**moduleName** | **String** |  |  |
|**moduleType** | **String** | The module&#39;s stored visualization (e.g. &#x60;table&#x60;, &#x60;line_chart&#x60;, &#x60;bar_chart&#x60;). Type label only — does not change the response shape.  |  |
|**range** | [**DashboardExecuteResultRange**](DashboardExecuteResultRange.md) |  |  [optional] |
|**filtersApplied** | **Map&lt;String, Object&gt;** | Echo of the filter dimensions that were applied, resolved to human-readable values where possible (e.g. line names instead of IDs).  |  [optional] |
|**columns** | [**List&lt;DashboardColumnSpec&gt;**](DashboardColumnSpec.md) | Column metadata. &#x60;type&#x60; is derived from the first non-null cell in the column. Null when the module errored.  |  [optional] |
|**rows** | **List&lt;Map&lt;String, Object&gt;&gt;** | Row data as objects keyed by column name (not positional arrays). Values are typed JSON natively. Null when the module errored.  |  [optional] |
|**error** | **String** | Set to a short message when the module failed to parse or execute. Null on success.  |  [optional] |



