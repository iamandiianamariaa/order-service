package com.example.order.domain;

import com.example.order.domain.enums.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    private String username;

    @Column(name = "pickup_date")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date pickupDate;

    @ManyToOne
    @JoinColumn(name = "fk_courier_id")
    private Courier courier;
}
