package com.halyoon.app.stay;

import com.halyoon.app.like.LikedStayRepository;
import com.halyoon.app.stay.host.HostRequest;
import com.halyoon.app.media.ImageRepository;
import com.halyoon.app.media.StayImages;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserRespository;
import com.halyoon.app.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StayService {

    private final StayRepository stayRepository;
    private final LikedStayRepository LikedRepository;
    private final UserService userService;
    private final UserRespository userRespository;
    private final ImageRepository imageRepository;


    public List<Stay> getStays(PageRequest pageRequest) {
        return this.stayRepository.findAll(pageRequest).getContent();
    }

    public Object Length() {
        return this.stayRepository.Length();
    }

    public Stay saveStay(StayRequest stayRequest) {
        HostRequest host = stayRequest.getHost();
        User user = User.builder()
                .id(host.get_id())
                .responseTime(host.getResponseTime())
                .fullname(host.getFullname())
                .isSuperhost(host.isSuperhost())
                .email(host.getUsername())
                .location(host.getLocation())
                .createdAt(host.getCreateAt())
                .imgUrl(host.getPictureUrl())
                .build();
        Stay newstay = Stay.builder()
                .bedrooms(stayRequest.getBedrooms())
                .bathrooms(stayRequest.getBathrooms())
                .name(stayRequest.getName())
                .type(stayRequest.getType())
                .user(user)
                .price(stayRequest.getPrice())
                .summary(stayRequest.getSummary())
                .capacity(stayRequest.getCapacity())
                .build();
        var stay = stayRepository.save(newstay);
        for (String imgUrl : stayRequest.getImgUrls()) {
            StayImages image1 = StayImages.builder().stay(newstay).imageUrl(imgUrl).build();
            var savedImage1 = imageRepository.save(image1);
        }
        return stay;
    }

    public Stay getStayById(Integer id) {
        return this.stayRepository.getReferenceById(id);
    }

    public List<Stay> getUserStays(Integer userId, PageRequest pageRequest) {
        // Implement logic to retrieve stays for the given user ID and pagination
        // You can use your repository or any other data access method here
        return stayRepository.findByUserId(userId, pageRequest).getContent();
    }


    public List<Stay> getStaysLikedByUser(Pageable pageable, Integer userId) {
        // Implement logic to retrieve stays liked by a user with pagination
        return stayRepository.findStaysLikedByUserId(userId, pageable).getContent();
    }


}
