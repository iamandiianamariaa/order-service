package com.example.order.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EntityNotFoundException extends RuntimeException {

    private final String entityType;

    private final Long entityId;
}
