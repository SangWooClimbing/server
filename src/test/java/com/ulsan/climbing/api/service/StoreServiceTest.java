package com.ulsan.climbing.api.service;

import com.ulsan.climbing.api.domain.Store;
import com.ulsan.climbing.api.domain.request.store.StoreCreateRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTest {
    @Autowired
    private StoreService storeService;

    @DisplayName("")
    @Test
    void createStore(){
        //given
        StoreCreateRequest request = StoreFixture.createStoreCreateRequest();

        //when
        Store store = storeService.create(request);

        //then
        assertThat(store.getId()).isNotNull();
        assertThat(store.getName()).isEqualTo(request.name());
    }

    @DisplayName("")
    @Test
    void findById(){
        //given
        StoreCreateRequest request = StoreFixture.createStoreCreateRequest();
        Store store = storeService.create(request);

        //when
        Store result = storeService.findById(store.getId());

        //then
        assertThat(result.getName()).isEqualTo(store.getName());
        assertThat(result.getAddress()).isEqualTo(store.getAddress());
    }
}
