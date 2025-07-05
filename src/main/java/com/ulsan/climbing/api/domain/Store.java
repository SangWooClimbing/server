package com.ulsan.climbing.api.domain;

import com.ulsan.climbing.api.domain.request.store.StoreCreateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Entity
@Table(name = "STORE")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Store extends BaseEntity {
    @Column(length = 20, nullable = false)
    String name;
    @Column(length = 50, nullable = false)
    String address;
    @Column(length = 50, nullable = false)
    String phone;
    @Column(length = 200)
    String instagram;
    @Column(length = 500, nullable = false)
    String information;
    @Column()
    Double latitude;
    @Column()
    Double longitude;
    @Column()
    boolean isVisible;

    public static Store create(StoreCreateRequest request) {
        Store store = new Store();

        store.name = request.name();
        store.address = request.address();
        store.phone = request.phone();
        store.latitude = request.latitude();
        store.longitude = request.longitude();
        store.instagram = request.instagram();
        store.information = request.information();

        return store;
    }
}
