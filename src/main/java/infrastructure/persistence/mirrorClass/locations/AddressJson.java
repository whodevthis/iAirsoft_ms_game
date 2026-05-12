package infrastructure.persistence.mirrorClass.locations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressJson {
    private String street;
    private String city;
    private int cp;
    private String province;
    private String country;
}