package com.halyoon.app.stay;

import com.halyoon.app.stay.media.ImageRepository;
import com.halyoon.app.stay.media.StayImages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StayService {

    private final StayRepository repository;

    private final ImageRepository imageRepository;


    public List<Stay> getStays(Integer page) {
        return this.repository.findAll();
    }

    public Object Length() {
        return this.repository.Length();
    }

    public Stay saveStay(StayRequest stayRequest) {
        Stay newstay = Stay.builder()
                .bedrooms(stayRequest.getBedrooms())
                .bathrooms(stayRequest.getBathrooms())
                .name(stayRequest.getName())
                .type(stayRequest.getType())
                .price(stayRequest.getPrice())
                .summary(stayRequest.getSummary())
                .capacity(stayRequest.getCapacity())
                .build();
        var stay = repository.save(newstay);
        for (String imgUrl : stayRequest.getImgUrls()) {
            StayImages image1 = StayImages.builder().stay(newstay).imageUrl(imgUrl).build();
            var savedImage1 = imageRepository.save(image1);
        }
        return stay;
    }

    public Object images() {
        List<StayImages> stayImages = imageRepository.findByStayId(102);
        return stayImages;
    }
}
