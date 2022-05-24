package hu.webuni.hr.nyhp.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@ConfigurationProperties(prefix = "hr.employee")
@Component
public class HrConfigProperties {
	
	private Default def = new Default();
	private Smart smart = new Smart();
	
	private Jwt jwt = new Jwt();
	
	public Jwt getJwt() {
		return jwt;
	}

	public void setJwt(Jwt jwt) {
		this.jwt = jwt;
	}

	public Default getDef() {
		return def;
	}

	public void setDef(Default def) {
		this.def = def;
	}

	public Smart getSmart() {
		return smart;
	}

	public void setSmart(Smart smart) {
		this.smart = smart;
	}

	public static class Default {
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
		
	}
	
	public static class Smart {
		private int percent1;
		private int percent2;
		private int percent3;
		private int percent4;
		private double limit1;
		private double limit2;
		private double limit3;
		
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
		public int getPercent4() {
			return percent4;
		}
		public void setPercent4(int percent4) {
			this.percent4 = percent4;
		}
		public double getLimit1() {
			return limit1;
		}
		public void setLimit1(double limit1) {
			this.limit1 = limit1;
		}
		public double getLimit2() {
			return limit2;
		}
		public void setLimit2(double limit2) {
			this.limit2 = limit2;
		}
		public double getLimit3() {
			return limit3;
		}
		public void setLimit3(double limit3) {
			this.limit3 = limit3;
		}
		
	}
	
	public static class Jwt{
		private String issuer;
		private String alg;
		private String secret;
		private Duration duration;
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public String getAlg() {
			return alg;
		}
		public void setAlg(String alg) {
			this.alg = alg;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		public Duration getDuration() {
			return duration;
		}
		public void setDuration(Duration duration) {
			this.duration = duration;
		}
		
	}

}
