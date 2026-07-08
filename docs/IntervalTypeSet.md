

# IntervalTypeSet

Create or update a custom interval type. Omit `id` to create (requires `name`); include `id` to update. On update, `name` (if present) is ignored. Use `null` for `tags` to leave tags unchanged; `[]` clears tags. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **UUID** |  |  [optional] |
|**name** | **String** |  |  [optional] |
|**metadataSchema** | **String** |  |  [optional] |
|**tags** | **List&lt;String&gt;** |  |  [optional] |



