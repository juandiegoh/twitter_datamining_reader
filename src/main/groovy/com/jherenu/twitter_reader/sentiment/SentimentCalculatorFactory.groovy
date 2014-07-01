package com.jherenu.twitter_reader.sentiment

import com.jherenu.twitter_reader.utils.PropertiesAdapter

class SentimentCalculatorFactory {

    PropertiesAdapter propertiesAdapter

    SentimentCalculatorFactory(PropertiesAdapter propertiesAdapter) {
        this.propertiesAdapter = propertiesAdapter
    }

    SentimentCalculator createSentimentCalculator(String keywords) {
        switch (propertiesAdapter.getSentimentProperty()) {
            case 'SentimentAPI':
                return new SentimentAPICalculator(keywords)
                break
            case 'SentimentStrength':
                return new SentiStrengthCalculator(keywords)
                break
            default:
                return getDefaultSentimentCalculator(keywords)
                break
        }
    }

    private SentimentCalculator getDefaultSentimentCalculator(keywords) {
        return new SentiStrengthCalculator(keywords)
    }
}
