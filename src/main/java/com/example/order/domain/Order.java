package com.example.order.domain;

import com.example.order.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "sender_phone")
    private String senderPhone;

    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "receiver_phone")
    private String receiverPhone;
    @Column(name = "sender_city")
    private String senderCity;
    @Column(name = "sender_county")
    private String senderCounty;
    @Column(name = "sender_country")
    private String senderCountry;

    @Column(name = "sender_address")
    private String senderAddress;

    @Column(name = "receiver_city")
    private String receiverCity;
    @Column(name = "receiver_county")
    private String receiverCounty;
    @Column(name = "receiver_country")
    private String receiverCountry;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "parcel_number")
    private Integer parcelNumber;

    private Double cost;
    @Column(name = "pickup_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pickupDate;

    @ManyToOne
    @JoinColumn(name = "fk_courier_id")
    private Courier courier;

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "fk_order_details", referencedColumnName = "id")
//    private OrderDetails orderDetails;
}
