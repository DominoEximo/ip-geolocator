package geolocator;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;

import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

/**
 * Interface to obtain geolocation information for an IP address or host name.
 * The actual implementation uses the
 * <a href="https://reallyfreegeoip.org/json/">https://reallyfreegeoip.org/json/</a> API.
 * The {@link #newInstance()} method is provided for obtaining a
 * {@code GeoLocator} object.
 */
public interface GeoLocator {

    /**
     * @return geolocation information for the JVM running the application
     *
     * @throws feign.FeignException if any error occures.
     */
    @RequestLine("GET")
    GeoLocation getGeoLocation();

    /**
     * @param ipOrHostName an IP address or host name
     * @return geolocation information for the specified IP or host.
     *
     * @throws feign.FeignException if any error occures.
     */
    @RequestLine("GET /{ipOrHostName}")
    GeoLocation getGeoLocation(@Param("ipOrHostName") String ipOrHostName);

    /**
     * @return an object implementing the {@code GeoLocator} interface.
     */
    static GeoLocator newInstance() {
        return Feign.builder()
            .decoder(new GsonDecoder(new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()))
            .target(GeoLocator.class, "https://reallyfreegeoip.org/json/");
    }

}
