package ztysdmy.binance.model;

import java.math.BigDecimal;

public class AggTradeData extends BinanceObject{

	long a;         // Aggregate tradeId
    BigDecimal p;  // Price
    BigDecimal q;  // Quantity
    long f;         // First tradeId
    long l;         // Last tradeId
    long T; // Timestamp
    boolean m;          // Was the buyer the maker?
    boolean M;           // Was the trade the best price match?
	public long getA() {
		return a;
	}
	public void setA(long a) {
		this.a = a;
	}
	public BigDecimal getP() {
		return p;
	}
	public void setP(BigDecimal p) {
		this.p = p;
	}
	public BigDecimal getQ() {
		return q;
	}
	public void setQ(BigDecimal q) {
		this.q = q;
	}
	public long getF() {
		return f;
	}
	public void setF(long f) {
		this.f = f;
	}
	public long getL() {
		return l;
	}
	public void setL(long l) {
		this.l = l;
	}
	public long getT() {
		return T;
	}
	public void setT(long t) {
		T = t;
	}
	public boolean ism() {
		return m;
	}
	public void setm(boolean m) {
		this.m = m;
	}
	public boolean isM() {
		return M;
	}
	public void setM(boolean m) {
		M = m;
	}
    
    
	
}
