package com.jherenu.twitter_reader.validators.data_validators

import com.jherenu.twitter_reader.configuration.TweetDataConfiguration
import com.jherenu.twitter_reader.TweetDataToAnalyze

class MandatoryLocationValidator implements DataValidator {

    TweetDataConfiguration configuration

    MandatoryLocationValidator(TweetDataConfiguration configuration) {
        this.configuration = configuration
    }

    @Override
    Boolean validate(data) {
        if(configuration.getMandatoryLocation()) {
            return dataWithCountry(data) || dataWithGeolocalization(data)
        }
        return true
    }

    boolean dataWithGeolocalization(data) {
        return data.getLatitude() != null || data.getLongitude() != null
    }

    private boolean dataWithCountry(TweetDataToAnalyze data) {
        return data.getCountry() != null ||  data.getCountryCode() != null
    }
}
