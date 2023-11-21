package com.halyoon.app.stay;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StayMapper {


    public List<StayResponse> getStays(List<Stay> stays) {
        List<StayResponse> list = new ArrayList<>();
        for (Stay stay : stays) {
            StayResponse stayResponse = StayResponse.builder()
                    ._id(stay.getId()).name(stay.getName()).price(stay.getPrice()).summary(stay.getSummary()).type(stay.getType()).capacity(stay.getCapacity()).user(stay.getUser()).build();
            list.add(stayResponse);
        }
        return list;
    }
}
