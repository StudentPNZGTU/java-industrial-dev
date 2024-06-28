
package org.penzgtu.Application.adapter;

import org.penzgtu.Application.models.user.GeoLocation;

public interface GeoLocationAdapter {
    GeoLocation adapt(GeoLocationApiResponse response);
    GeoLocation getGeoLocation();
}

