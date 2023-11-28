package com.halyoon.app.stay;

import com.halyoon.app.stay.media.ImageRepository;
import com.halyoon.app.stay.media.StayImages;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StayMapper {

    private final ImageRepository imageRepository;

    private UserResponse map(User user) {
        if (user == null)
            return null;
        return UserResponse.builder()
                ._id(user.getId())
                .fullname(user.getFirstname() + " " + user.getLastname())
                .createAt(user.getCreatedAt())
                .about(user.getAbout())
                .location(user.getLocation())
                .responseTime((user.getResponseTime()))
                .pictureUrl(user.getImgUrl())
                .isSuperhost(user.isSuperhost())
                .build();
    }

    public List<StayResponse> getStays(List<Stay> stays) {
        List<StayResponse> list = new ArrayList<>();

        for (Stay stay : stays) {

            //map user
            var hostResponse = map(stay.getUser());
            //map images
            var images = map(stay.getImages());

            var stayResponse =
                    StayResponse.builder()
                            ._id(stay.getId())
                            .name(stay.getName())
                            .price(stay.getPrice())
                            .summary(stay.getSummary())
                            .bathrooms(stay.getBathrooms())
                            .bedrooms(stay.getBedrooms())
                            .imgUrls(images)
                            .type(stay.getType())
                            .capacity(stay.getCapacity())
                            .host(hostResponse).build();

            list.add(stayResponse);

        }
        return list;
    }

    private List<String> map(List<StayImages> images) {
        return images.stream().map(x -> x.getImageUrl()).toList();
    }
}
