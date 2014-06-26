package com.jherenu.twitter_reader

import org.junit.Before
import org.junit.Test
import uk.ac.wlv.sentistrength.SentiStrength

class SentiStrengthTest {

    String keyword = 'dogs'
    SentiValueCalculator sentiValueCalculator

    @Before
    void init() {
        this.sentiValueCalculator = new SentiValueCalculator(keyword)
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
}
