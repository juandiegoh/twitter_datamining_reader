package com.jherenu.twitter_reader.configuration

import com.jherenu.twitter_reader.sentiment.SentimentCalculator

class TweetDataConfiguration {

    String keywords
    String extraWords
    Boolean mandatoryLocation
    Integer sizeOfData
    SentimentCalculator sentimentCalculator
}
