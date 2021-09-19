package payroll.demopayroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {
    
    private @Id @GeneratedValue Long id;

    private String descripcion;
    private Status status;

    Order() {}

    Order(String descripcion, Status status) {
        this.descripcion = descripcion;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.descripcion;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o)
         return true;
        if(!(o instanceof Order))
         return false;
        Order order = (Order) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.descripcion, order.descripcion) 
        && this.status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.descripcion, this.status);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + this.id + ", descripcion='" + this.descripcion + '\'' + ", status" + this.status + '}';
    }

}
