package org.penzgtu.Application.adapter;

import org.penzgtu.Application.models.user.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeoLocationAdapterImpl implements GeoLocationAdapter {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public GeoLocation getGeoLocation() {
        String url = "https://ipinfo.io/";
        GeoLocationApiResponse response = restTemplate.getForObject(url, GeoLocationApiResponse.class);
        return adapt(response);
    }
    @Override
    public GeoLocation adapt(GeoLocationApiResponse response) {
        GeoLocation geoLocation = new GeoLocation();
        if (response != null && response.getLoc() != null) {
            String[] locParts = response.getLoc().split(",");
            if (locParts.length == 2) {
                geoLocation.setLat(locParts[0]);
                geoLocation.setLng(locParts[1]);
            }
        }
        return geoLocation;
    }
}

