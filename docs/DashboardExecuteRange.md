

# DashboardExecuteRange

Time window applied to every module. Either supply an absolute window (`start` and `end`) OR a relative window (`anchor` and `offset`, optionally with `timezone`), not both. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**start** | **OffsetDateTime** |  |  [optional] |
|**end** | **OffsetDateTime** |  |  [optional] |
|**anchor** | **String** | Anchor expression for a relative range, e.g. &#x60;now&#x60;. |  [optional] |
|**offset** | **String** | Offset expression for a relative range, e.g. &#x60;-7D&#x60;. |  [optional] |
|**timezone** | **String** | IANA timezone identifier (defaults to UTC). |  [optional] |



