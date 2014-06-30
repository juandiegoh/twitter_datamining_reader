package com.jherenu.twitter_reader

import com.jherenu.twitter_reader.sentiment.SentiValue
import com.jherenu.twitter_reader.sentiment.SentimentAPICalculator
import com.jherenu.twitter_reader.sentiment.SentimentCalculator
import org.junit.Before
import org.junit.Test

class SentimentAPICalculatorTest {

    SentimentCalculator sentiValueCalculator
    String keyword = 'dogs'

    @Before
    void init() {
        this.sentiValueCalculator = new SentimentAPICalculator(keyword)
    }

    @Test
    void testSentiStrengthPositive() {

        def result = this.sentiValueCalculator.calculateSentiValue('I love dogs')
        assert result == SentiValue.POSITIVE
    }

    @Test
    void testSentiStrengthNegative() {

        def result = this.sentiValueCalculator.calculateSentiValue('I hate dogs')
        assert result == SentiValue.NEGATIVE
    }

    @Test
    void testSentiStrengthNeutral() {

        def result = this.sentiValueCalculator.calculateSentiValue('Dogs are running')
        assert result == SentiValue.NEUTRAL
    }
}
