package ztysdmy.binance.model;


public class ExchangeInfo extends BinanceObject {

	String timezone;
	long serverTime;

	RateLimiters[] rateLimits;
	Symbol[] symbols;
	//String[] exchangeFilters;

	public long getServerTime() {
		return serverTime;
	}

	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}

	public RateLimiters[] getRateLimits() {
		return rateLimits;
	}

	public void setRateLimits(RateLimiters[] rateLimits) {
		this.rateLimits = rateLimits;
	}

	/**public String[] getExchangeFilters() {
		return exchangeFilters;
	}

	public void setExchangeFilters(String[] exchangeFilters) {
		this.exchangeFilters = exchangeFilters;
	}**/
	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Symbol[] getSymbols() {
		return symbols;
	}

	public void setSymbols(Symbol[] symbols) {
		this.symbols = symbols;
	}
	
	public static class Symbol {
		
		String symbol;
		OrderType[] orderTypes;
		SymbolStatus symbolStatus;
		
		String baseAsset;
		int baseAssetPrecision;
	    String quoteAsset;
	    int quotePrecision;
	    int quoteAssetPrecision;
	    int baseCommissionPrecision;
	    int quoteCommissionPrecision;

	   
		boolean icebergAllowed;
	    boolean ocoAllowed;
	    boolean quoteOrderQtyMarketAllowed;
	    boolean isSpotTradingAllowed;
	    boolean isMarginTradingAllowed;
	    
	   /** String[] filters;
	    public String[] getFilters() {
			return filters;
		}
		public void setFilters(String[] filters) {
			this.filters = filters;
		}**/
		public String[] getPermissions() {
			return permissions;
		}
		public void setPermissions(String[] permissions) {
			this.permissions = permissions;
		}
		String[] permissions;
	   
	    public boolean isIcebergAllowed() {
			return icebergAllowed;
		}
		public void setIcebergAllowed(boolean icebergAllowed) {
			this.icebergAllowed = icebergAllowed;
		}
		public boolean isOcoAllowed() {
			return ocoAllowed;
		}
		public void setOcoAllowed(boolean ocoAllowed) {
			this.ocoAllowed = ocoAllowed;
		}
		public boolean isQuoteOrderQtyMarketAllowed() {
			return quoteOrderQtyMarketAllowed;
		}
		public void setQuoteOrderQtyMarketAllowed(boolean quoteOrderQtyMarketAllowed) {
			this.quoteOrderQtyMarketAllowed = quoteOrderQtyMarketAllowed;
		}
		public boolean isSpotTradingAllowed() {
			return isSpotTradingAllowed;
		}
		public void setSpotTradingAllowed(boolean isSpotTradingAllowed) {
			this.isSpotTradingAllowed = isSpotTradingAllowed;
		}
		public boolean isMarginTradingAllowed() {
			return isMarginTradingAllowed;
		}
		public void setMarginTradingAllowed(boolean isMarginTradingAllowed) {
			this.isMarginTradingAllowed = isMarginTradingAllowed;
		}
	    
	    public String getBaseAsset() {
			return baseAsset;
		}
		public void setBaseAsset(String baseAsset) {
			this.baseAsset = baseAsset;
		}
		public int getBaseAssetPrecision() {
			return baseAssetPrecision;
		}
		public void setBaseAssetPrecision(int baseAssetPrecision) {
			this.baseAssetPrecision = baseAssetPrecision;
		}
		public String getQuoteAsset() {
			return quoteAsset;
		}
		public void setQuoteAsset(String quoteAsset) {
			this.quoteAsset = quoteAsset;
		}
		public int getQuotePrecision() {
			return quotePrecision;
		}
		public void setQuotePrecision(int quotePrecision) {
			this.quotePrecision = quotePrecision;
		}
		public int getQuoteAssetPrecision() {
			return quoteAssetPrecision;
		}
		public void setQuoteAssetPrecision(int quoteAssetPrecision) {
			this.quoteAssetPrecision = quoteAssetPrecision;
		}
		public int getBaseCommissionPrecision() {
			return baseCommissionPrecision;
		}
		public void setBaseCommissionPrecision(int baseCommissionPrecision) {
			this.baseCommissionPrecision = baseCommissionPrecision;
		}
		public int getQuoteCommissionPrecision() {
			return quoteCommissionPrecision;
		}
		public void setQuoteCommissionPrecision(int quoteCommissionPrecision) {
			this.quoteCommissionPrecision = quoteCommissionPrecision;
		}
			
		public SymbolStatus getSymbolStatus() {
			return symbolStatus;
		}
		public void setSymbolStatus(SymbolStatus symbolStatus) {
			this.symbolStatus = symbolStatus;
		}
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		
		public OrderType[] getOrderTypes() {
			return orderTypes;
		}
		public void setOrderTypes(OrderType[] orderTypes) {
			this.orderTypes = orderTypes;
		}
	}
	
	
}
