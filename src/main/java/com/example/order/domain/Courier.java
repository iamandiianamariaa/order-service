package com.example.order.domain;

import com.example.order.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "couriers")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @Column(name = "assigned_city")
    private String assignedCity;
    @Column(name = "assigned_county")
    private String assignedCounty;
    @Column(name = "assigned_country")
    private String assignedCountry;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<Order> orders;
}
