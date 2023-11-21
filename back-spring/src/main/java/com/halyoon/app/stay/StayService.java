package com.halyoon.app.stay;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StayService {

    private final StayRepository repository;

    public List<Stay> getStays(StayRequest page) {
        return this.repository.findAll();
    }

    public Object Length() {
        return this.repository.Length();
    }
}
