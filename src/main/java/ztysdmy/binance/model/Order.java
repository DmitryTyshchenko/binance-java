package ztysdmy.binance.model;

import java.math.BigDecimal;

public class Order extends BinanceObject {

	private String symbol;
	private long orderId;
	private long orderListId;
	private String clientOrderId;
	private BigDecimal price;
	private BigDecimal origQty;
    private BigDecimal executedQty;
    private BigDecimal cummulativeQuoteQty;
    private OrderStatus status;
    private TimeInForce timeInForce;
    private OrderType type;
    private OrderSide side;
    private BigDecimal stopPrice;
    private BigDecimal icebergQty;
    private long time;
    private long updateTime;
    private boolean isWorking;
    private BigDecimal origQuoteOrderQty;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public long getOrderListId() {
		return orderListId;
	}
	public void setOrderListId(int orderListId) {
		this.orderListId = orderListId;
	}
	public String getClientOrderId() {
		return clientOrderId;
	}
	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getOrigQty() {
		return origQty;
	}
	public void setOrigQty(BigDecimal origQty) {
		this.origQty = origQty;
	}
	public BigDecimal getExecutedQty() {
		return executedQty;
	}
	public void setExecutedQty(BigDecimal executedQty) {
		this.executedQty = executedQty;
	}
	public BigDecimal getCummulativeQuoteQty() {
		return cummulativeQuoteQty;
	}
	public void setCummulativeQuoteQty(BigDecimal cummulativeQuoteQty) {
		this.cummulativeQuoteQty = cummulativeQuoteQty;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public TimeInForce getTimeInForce() {
		return timeInForce;
	}
	public void setTimeInForce(TimeInForce timeInForce) {
		this.timeInForce = timeInForce;
	}
	public OrderType getType() {
		return type;
	}
	public void setType(OrderType type) {
		this.type = type;
	}
	public OrderSide getSide() {
		return side;
	}
	public void setSide(OrderSide side) {
		this.side = side;
	}
	public BigDecimal getStopPrice() {
		return stopPrice;
	}
	public void setStopPrice(BigDecimal stopPrice) {
		this.stopPrice = stopPrice;
	}
	public BigDecimal getIcebergQty() {
		return icebergQty;
	}
	public void setIcebergQty(BigDecimal icebergQty) {
		this.icebergQty = icebergQty;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public boolean isWorking() {
		return isWorking;
	}
	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
	public BigDecimal getOrigQuoteOrderQty() {
		return origQuoteOrderQty;
	}
	public void setOrigQuoteOrderQty(BigDecimal origQuoteOrderQty) {
		this.origQuoteOrderQty = origQuoteOrderQty;
	}
	
}
