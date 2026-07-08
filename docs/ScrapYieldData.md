

# ScrapYieldData

An object representing scrap and yield data for a line for a particular run or batch interval. Data can be sent unstructured in the `raw_data` field as long as we have a scrap/yield schema for the factory. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**rawData** | **String** |  |  [optional] |
|**id** | **UUID** |  |  [optional] |
|**schema** | [**ScrapYieldSchema**](ScrapYieldSchema.md) |  |  [optional] |
|**timestamp** | **OffsetDateTime** |  |  [optional] |
|**match** | **Match** |  |  [optional] |



