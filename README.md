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
## Support us

If you like it, please, support us:

BTC: 1jJ1iPqEarCn61AtmxjuzKr1kUwq3DsJU

ETH: 0x5e4682fd90f2b26147cc9965bcebb950af893b3f

BNB: bnb136ns6lfw4zs5hg4n85vdthaad7hq5m4gtkgf23

BNB MEMO:
