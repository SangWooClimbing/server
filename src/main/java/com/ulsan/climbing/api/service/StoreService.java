package com.ulsan.climbing.api.service;

import com.ulsan.climbing.api.domain.Store;
import com.ulsan.climbing.api.domain.request.store.StoreCreateRequest;
import com.ulsan.climbing.api.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository repository;

    public Store create(StoreCreateRequest request) {
        var store = Store.create(request);

        return repository.save(store);
    }

    public Store findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("찾을 수 없는 매장입니다. id :" + id)
        );
    }
}
