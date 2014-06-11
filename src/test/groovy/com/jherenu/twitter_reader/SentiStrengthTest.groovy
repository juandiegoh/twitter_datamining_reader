package com.jherenu.twitter_reader

import org.junit.Test
import uk.ac.wlv.sentistrength.SentiStrength

class SentiStrengthTest {

    @Test
    void testSentiStrengthPositive() {

        def result = calculateSentiValue('dogs', 'I like dogs')
        assert result == SentiValue.POSITIVE
    }

    @Test
    void testSentiStrengthNegative() {

        def result = calculateSentiValue('dogs', "I hate dogs")
        assert result == SentiValue.NEGATIVE
    }

    def SentiValue calculateSentiValue(String keywords, String text) {
        def (int positive, int negative) = calculatePositiveAndNegativeValue(keywords, text)
        int result = positive + negative
        return result == 0 ? SentiValue.NEUTRAL : result > 0 ? SentiValue.POSITIVE : SentiValue.NEGATIVE
    }

    def calculatePositiveAndNegativeValue(String keywords, String text) {
        SentiStrength sentiStrength = new SentiStrength()

        String[] ssthInitialisationAndText = ["sentidata", "/Users/jherenu/Documents/SentStrength_Data/", "keywords", "dogs", "explain"]
        sentiStrength.initialise(ssthInitialisationAndText)

        String result = sentiStrength.computeSentimentScores(text)
        def tokens = result.tokenize(' ')
        def sentiStrings = tokens.subList(0,2)
        def sentiNumbers = sentiStrings.collect { Integer.valueOf(it) }
        return sentiNumbers
    }
}
