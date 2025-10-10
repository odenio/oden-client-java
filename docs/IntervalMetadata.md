

# IntervalMetadata

Metadata about this interval, such as the associated run or the state category. Will differ based on the type of interval. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**metadataType** | [**MetadataTypeEnum**](#MetadataTypeEnum) |  |  |
|**run** | [**Interval**](Interval.md) |  |  [optional] |
|**product** | [**Product**](Product.md) |  |  [optional] |
|**target** | [**Target**](Target.md) |  |  [optional] |
|**reason** | [**StateReason**](StateReason.md) |  |  [optional] |
|**comment** | **String** |  |  [optional] |
|**category** | [**StateCategory**](StateCategory.md) |  |  [optional] |



## Enum: MetadataTypeEnum

| Name | Value |
|---- | -----|
| CUSTOM | &quot;custom&quot; |



