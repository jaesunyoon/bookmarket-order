package bookmarket.external;

public class Payment {

    private Long id;
    private Long orderId;
    private String status;
    private Long customerId;
    private String isMile;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getIsMile() {
        return isMile;
    }

    public void setIsMile(String isMile) {
        this.isMile = isMile;
    }
}
