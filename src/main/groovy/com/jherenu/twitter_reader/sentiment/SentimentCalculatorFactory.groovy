package com.jherenu.twitter_reader.sentiment

class SentimentCalculatorFactory {

    SentimentCalculator createSentimentCalculator(String sentiment, String keywords) {
        switch (sentiment) {
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
