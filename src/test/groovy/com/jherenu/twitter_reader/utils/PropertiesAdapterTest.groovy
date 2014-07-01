package com.jherenu.twitter_reader.utils

import org.junit.Before
import org.junit.Test

class PropertiesAdapterTest {

    PropertiesAdapter propertiesAdapter

    @Before
    void setUp() {
        this.propertiesAdapter = new PropertiesAdapter('config_test.groovy')
    }

    @Test
    void testGetSentimentPropertyGetter() {
        assert 'test_ok' == propertiesAdapter.getSentimentProperty()
    }
}
