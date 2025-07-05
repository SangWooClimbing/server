package com.ulsan.climbing.api.domain.request.store;

public record StoreCreateRequest(
        String name,
        String address,
        String phone,
        String information,
        String instagram,
        Double latitude,
        Double longitude
) {
}
