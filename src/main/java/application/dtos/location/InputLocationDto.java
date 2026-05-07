package application.dtos.location;

public record InputLocationDto(String street, String city, int cp, String province, String country, double lat,
                               double lon)
{
}
