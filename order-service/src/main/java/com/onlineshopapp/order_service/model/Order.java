package com.onlineshopapp.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL) // cascade = CascadeType.ALL means When you save, update, or delete an Order, the same operation will be cascaded to its orderLineItems.
    private List<OrderLineItems> orderLineItems; // list of items included in the order
}
