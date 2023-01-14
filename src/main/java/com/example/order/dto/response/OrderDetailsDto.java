package com.example.order.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailsDto {
    private String senderCity;
    private String senderCounty;
    private String senderCountry;
    private String senderAddress;

    private String receiverCity;
    private String receiverCounty;
    private String receiverCountry;
    private String receiverAddress;
}
