package com.jherenu.twitter_reader.configuration

import com.jherenu.twitter_reader.sentiment.SentimentCalculator
import com.jherenu.twitter_reader.sentiment.SentimentCalculatorFactory
import com.jherenu.twitter_reader.utils.PropertiesAdapter

class DefaultTweetDataConfigurationFactory {

    PropertiesAdapter propertiesAdapter

    public static final def defaults = [
            'keywords':'argentina',
            'extra_words':'messi,world cup,worldcup,brazil,brasil,football,soccer',
            'sentiment_calculator':'SentimentStrength',
            'size_of_data':100,
            'mandatory_location':false
    ]

    DefaultTweetDataConfigurationFactory(PropertiesAdapter propertiesAdapter) {
        this.propertiesAdapter = propertiesAdapter
    }

    TweetDataConfiguration createTweetDataConfiguration() {
        return new TweetDataConfiguration().with { tdc ->
            tdc.keywords = this.getKeywords()
            tdc.extraWords = this.getExtraWords()
            tdc.sentimentCalculator = this.getSentimentCalculator(tdc.keywords)
            tdc.sizeOfData = this.getSizeData()
            tdc.mandatoryLocation = this.getMandatoryLocation()
            return tdc
        }
    }

    def getValueFromKey(String key) {
        def value = this.propertiesAdapter.getValueFromKey(key)
        if(value != null) {
            value
        } else {
            defaults."$key"
        }
    }

    String getKeywords() {
        this.getValueFromKey('keywords')
    }

    String getExtraWords() {
        this.getValueFromKey('extra_words')
    }

    SentimentCalculator getSentimentCalculator(keywords) {
        String sentimentKey = this.getValueFromKey('sentiment_calculator')
        SentimentCalculatorFactory sentimentCalculatorFactory = new SentimentCalculatorFactory()
        return sentimentCalculatorFactory.createSentimentCalculator(sentimentKey, keywords)
    }

    def getSizeData() {
        this.getValueFromKey('size_of_data').toInteger()
    }

    boolean getMandatoryLocation() {
        return this.getValueFromKey('mandatory_location')
    }
}
