package com.ulsan.climbing.api.service;

import com.ulsan.climbing.api.domain.request.store.StoreCreateRequest;

public class StoreFixture {
    public static StoreCreateRequest createStoreCreateRequest(){
        return new StoreCreateRequest("테스트 암벽장", "서울 특별시 광진구", " 01000000000", "테스트 매장입니다.", "https://instagream/teser", 0.0, 0.0);
    }
}
