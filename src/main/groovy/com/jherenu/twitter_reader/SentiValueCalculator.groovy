package com.jherenu.twitter_reader

import uk.ac.wlv.sentistrength.SentiStrength

class SentiValueCalculator {

    String keywords

    SentiValueCalculator(String keys) {
        this.keywords = keys.replace(' ',',')
    }

    SentiValue calculateSentiValue(CharSequence text) {
        def (int positive, int negative) = calculatePositiveAndNegativeValue(keywords, text)
        int result = positive + negative
        return result == 0 ? SentiValue.NEUTRAL : result > 0 ? SentiValue.POSITIVE : SentiValue.NEGATIVE
    }

    def calculatePositiveAndNegativeValue(String keywords, String text) {
        SentiStrength sentiStrength = new SentiStrength()

        String[] ssthInitialisationAndText = ["sentidata", "./resources/SentStrength_Data/", "keywords", keywords, "explain"]
        sentiStrength.initialise(ssthInitialisationAndText)

        String result = sentiStrength.computeSentimentScores(text)
        def tokens = result.tokenize(' ')
        def sentiStrings = tokens.subList(0,2)
        def sentiNumbers = sentiStrings.collect { Integer.valueOf(it) }
        return sentiNumbers
    }
}
