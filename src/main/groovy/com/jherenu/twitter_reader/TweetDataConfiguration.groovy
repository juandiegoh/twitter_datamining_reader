package com.jherenu.twitter_reader

class TweetDataConfiguration {

    public static final Number DEFAULT_SIZE_OF_DATA = 100
    public static final Boolean DEFAULT_MANDATORY_LOCATION = false

    String keywords
    String extraWords
    Boolean mandatoryLocation = DEFAULT_MANDATORY_LOCATION
    Number sizeOfData = DEFAULT_SIZE_OF_DATA
}
