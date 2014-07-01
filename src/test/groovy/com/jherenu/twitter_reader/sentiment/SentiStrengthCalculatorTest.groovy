package com.jherenu.twitter_reader.sentiment

import com.jherenu.twitter_reader.sentiment.SentiValue
import com.jherenu.twitter_reader.sentiment.SentiStrengthCalculator
import org.junit.Before
import org.junit.Test

class SentiStrengthCalculatorTest {

    String keyword = 'dogs'
    SentiStrengthCalculator sentiValueCalculator

    @Before
    void init() {
        this.sentiValueCalculator = new SentiStrengthCalculator(keyword)
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
