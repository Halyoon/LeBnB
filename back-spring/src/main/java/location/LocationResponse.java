package location;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationResponse {
    private String country;
    private String countryCode;
    private String city;
    private String address;
    private double lat;
    private double lan;


}
