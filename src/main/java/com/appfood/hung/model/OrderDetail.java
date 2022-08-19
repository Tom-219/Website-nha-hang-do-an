package com.appfood.hung.model;
import javax.persistence.*;

@Entity
@Table(name = "orderDetails")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer quantity;
	
	@Column
	private Double price;
	
	@Column
	private Float discount;
	
	@Column
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

	public OrderDetail(){}

	public OrderDetail(Integer quantity, Double price, Order order, Product product) {
		this.quantity = quantity;
		this.price = price;
		this.order = order;
		this.product = product;
	}

	public OrderDetail(Integer quantity, Double price, Float discount, String note, Order order, Product product) {
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.note = note;
		this.order = order;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
