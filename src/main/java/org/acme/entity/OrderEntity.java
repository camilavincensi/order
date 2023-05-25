package org.acme.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Entity
@Table(name = "orderProducts")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderEntity {

    @Id
    @GeneratedValue
    private Long orderId;
    private Long costumerId;
    private String costumerName;
    private Long productId;
    private BigDecimal orderValue;

}
