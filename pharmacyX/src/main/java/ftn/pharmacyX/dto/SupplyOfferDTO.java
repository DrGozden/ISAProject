package ftn.pharmacyX.dto;

public class SupplyOfferDTO {

	private double totalPrice;
	private String deliveryDeadline;
	private Long orderId;
	
	public SupplyOfferDTO() {
		
	}
	
	

	public SupplyOfferDTO(double totalPrice, String deliveryDeadline, Long orderId) {
		super();
		this.totalPrice = totalPrice;
		this.deliveryDeadline = deliveryDeadline;
		this.orderId = orderId;
	}



	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryDeadline() {
		return deliveryDeadline;
	}

	public void setDeliveryDeadline(String deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
}
