package geolocator;

/**
 * Wraps geolocation information for an IP address or host name.
 */
@lombok.Data

public class GeoLocation {

    private String ip;
    private String countryCode;
    private String countryName;
    private String regionCode;
    private String regionName;
    private String city;
    private String zipCode;
    private String timeZone;
    private double latitude;
    private double longitude;
    private String metroCode;



}
