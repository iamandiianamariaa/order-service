package com.example.order.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private String senderName;
    private String senderPhone;
    private String receiverName;
    private String receiverPhone;
    private Integer parcelNumber;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date pickupDate;

    private String senderCity;
    private String senderCounty;
    private String senderCountry;
    private String senderAddress;

    private String receiverCity;
    private String receiverCounty;
    private String receiverCountry;
    private String receiverAddress;

    private String username;
}
