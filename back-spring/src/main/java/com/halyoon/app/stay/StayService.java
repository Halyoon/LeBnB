package com.halyoon.app.stay;

import com.halyoon.app.stay.host.HostRequest;
import com.halyoon.app.stay.media.ImageRepository;
import com.halyoon.app.stay.media.StayImages;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StayService {

    private final StayRepository repository;
    private final UserService userService;
    private final ImageRepository imageRepository;


    public List<Stay> getStays(PageRequest pageRequest) {
        return this.repository.findAll(pageRequest).getContent();
    }

    public Object Length() {
        return this.repository.Length();
    }

    public Stay saveStay(StayRequest stayRequest) {
        HostRequest host =stayRequest.getHost();
        User user = User.builder()
                .id(host.get_id())
                .email(host.getFullname())
                .about(host.getAbout())
                .firstname(host.getFullname())
                .responseTime(host.getResponseTime())
                .isSuperhost(host.isSuperhost())
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
        var stay = repository.save(newstay);
        for (String imgUrl : stayRequest.getImgUrls()) {
            StayImages image1 = StayImages.builder().stay(newstay).imageUrl(imgUrl).build();
            var savedImage1 = imageRepository.save(image1);
        }
        return stay;
    }

    public Stay getStayById(Integer id) {
        return  this.repository.getReferenceById(id);
    }

    public List<Stay> getUserStays(Integer userId, PageRequest pageRequest) {
        // Implement logic to retrieve stays for the given user ID and pagination
        // You can use your repository or any other data access method here
        return repository.findByUserId(userId, pageRequest).getContent();
    }

}
