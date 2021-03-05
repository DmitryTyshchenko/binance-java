# binance-java
## Unofficial Java Binance API Implementation ##

This library is an unofficial lightweight Java Client of [Binance API](https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#general-api-information). Requires at least Java 14. Based on Java Http Client and, for now, supports only synchronous calls to Binance.

## Example of Usage 
Getting current price for symbol
```java
BinanceApi api = new HttpBinanceApi();
var price = api.price("BTCBUSD");
System.out.println(price.toString());
```
Output
```java
{"symbol":"BTCBUSD","price":48121.51000000}
```
