package hu.webuni.transport.nyhp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@ConfigurationProperties(prefix = "transport")
@Component
public class TransportConfigProperties {
	
	private Minute minute = new Minute();
	
	private Discount discount = new Discount();

	
	
	public Minute getMinute() {
		return minute;
	}

	public void setMinute(Minute minute) {
		this.minute = minute;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public static class Minute {
		private int limit1;
		private int limit2;
		private int limit3;
		
		public int getLimit1() {
			return limit1;
		}
		public void setLimit1(int limit1) {
			this.limit1 = limit1;
		}
		public int getLimit2() {
			return limit2;
		}
		public void setLimit2(int limit2) {
			this.limit2 = limit2;
		}
		public int getLimit3() {
			return limit3;
		}
		public void setLimit3(int limit3) {
			this.limit3 = limit3;
		}

	}

	public static class Discount {
		private int percent1;
		private int percent2;;
		private int percent3;
		
		public int getPercent1() {
			return percent1;
		}
		public void setPercent1(int percent1) {
			this.percent1 = percent1;
		}
		public int getPercent2() {
			return percent2;
		}
		public void setPercent2(int percent2) {
			this.percent2 = percent2;
		}
		public int getPercent3() {
			return percent3;
		}
		public void setPercent3(int percent3) {
			this.percent3 = percent3;
		}

	}

}
