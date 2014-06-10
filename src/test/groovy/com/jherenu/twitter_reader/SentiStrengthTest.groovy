package com.jherenu.twitter_reader

import org.junit.Test
import uk.ac.wlv.sentistrength.SentiStrength

class SentiStrengthTest {

    @Test
    void testSentiStrength() {
        SentiStrength sentiStrength = new SentiStrength()

        String[] ssthInitialisationAndText = ["sentidata", "/Users/jherenu/Documents/SentStrength_Data/", "keywords", "dogs", "explain"]
        sentiStrength.initialise(ssthInitialisationAndText)

        def result = sentiStrength.computeSentimentScores("I love rabbits and hate dogs")

        assert result == 'ok'
    }

}
