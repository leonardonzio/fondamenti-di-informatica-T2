package gasforlife.model;

import java.text.NumberFormat;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Bill {

	private BillingFrequency billFreq;
	private double consumption, costm3, extraCostm3, fixedCost, value, variableCost;
	private Optional<Month> month;
	private List<Share> quotes;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
	private final String SEP_LINE = System.lineSeparator();
	
	public Bill(BillingFrequency billFreq, double value, double fixedCost, double variableCost, double consumption,
			double costm3, double extraCostm3, Optional<Month> month) {

		if (value <= 0 || fixedCost <= 0 || variableCost <= 0 || consumption <= 0 || costm3 <= 0 ||
				extraCostm3 <= 0 || value <= 0) {
			throw new IllegalArgumentException("un valore double è minore o uguale di 0");
		}
		
		if (billFreq == null || month == null)
			throw new IllegalArgumentException("elementi nulli non accettabili");
		
		this.billFreq = billFreq;
		this.value = value;
		this.fixedCost = fixedCost;
		this.variableCost = variableCost;
		this.consumption = consumption;
		this.costm3 = costm3;
		this.extraCostm3 = extraCostm3;
		this.month = month;
		this.quotes = new ArrayList<>();
	}

	public boolean addShare(Share quote) {
		return this.quotes.add(quote);
	}
	
	public BillingFrequency getBillingFrequency() {
		return billFreq;
	}
	
	public double getConsumption() {
		return consumption;
	}
	
	public double getCostm3() {
		return costm3;
	}
	
	public double getExtraCostm3() {
		return extraCostm3;
	}
	
	public double getFixedCost() {
		return fixedCost;
	}
	
	public double getValue() {
		return value;
	}
	
	public double getVariableCost() {
		return variableCost;
	}
	
	public Optional<Month> getMonth() {
		return month;
	}
	
	public List<Share> getShares() {
		return quotes;
	}
	
	@Override
	public String toString() {
		var builder = new StringBuilder();
		
		builder.append(billFreq.name()).append(SEP_LINE);
		builder.append(consumption).append(SEP_LINE);
		builder.append(costm3).append(SEP_LINE);
		builder.append(extraCostm3).append(SEP_LINE);
		builder.append(fixedCost).append(SEP_LINE);
		builder.append(value).append(SEP_LINE);
		builder.append(variableCost).append(SEP_LINE);
		builder.append(getMonthAsString()).append(SEP_LINE);
		String quotesAsString = this.quotes.stream()
				.map(Share::toString)
				.collect(Collectors.joining(SEP_LINE));
		
		return builder.append(quotesAsString).toString();
	}
	
	private String getMonthAsString() {
		
		if (getMonth().isEmpty()) 
			return "mese non presente";
		
		return formatter.format(getMonth().get());
	}
	
	
}
