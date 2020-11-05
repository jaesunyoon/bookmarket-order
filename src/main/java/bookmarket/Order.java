package bookmarket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long bookId;
    private Long qty;
    private String status;
    private Long customerId;
    private String isMile;

    @PostPersist
    public void onPostPersist(){
/*        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

 */
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.setStatus("Ordered");
        ordered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        if("Y".equals(this.getIsMile())){
            bookmarket.external.Mileage mileage = new bookmarket.external.Mileage();
            mileage.setOrderId(this.getId());
            mileage.setStatus("OrderedByMileage");
            mileage.setCustomerId(this.getCustomerId());
            mileage.setMileage(this.getQty());
            mileage.setIsMile("Y");
            // mappings goes here
            OrderApplication.applicationContext.getBean(bookmarket.external.MileageService.class)
                    .payReq(mileage);

        } else{
            bookmarket.external.Payment payment = new bookmarket.external.Payment();
            payment.setOrderId(this.getId());
            payment.setStatus("Ordered");
            payment.setCustomerId(this.getCustomerId());
            payment.setIsMile("N");
            // mappings goes here
            OrderApplication.applicationContext.getBean(bookmarket.external.PaymentService.class)
                    .payReq(payment);

        }

    }

    @PreRemove
    public void onPreRemove(){
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.setStatus("OrderCanceled");
        orderCanceled.publishAfterCommit();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
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
