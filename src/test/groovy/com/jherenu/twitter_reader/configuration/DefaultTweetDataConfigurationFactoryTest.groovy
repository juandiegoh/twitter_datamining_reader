package com.jherenu.twitter_reader.configuration

import com.jherenu.twitter_reader.utils.PropertiesAdapter
import org.junit.Before
import org.junit.Test

class DefaultTweetDataConfigurationFactoryTest {

    def propertiesAdapter
    DefaultTweetDataConfigurationFactory tweetDataConfigurationFactory

    @Before
    public void setUp() {
        this.propertiesAdapter = new PropertiesAdapter('config_test.groovy')
        this.tweetDataConfigurationFactory = new DefaultTweetDataConfigurationFactory(propertiesAdapter)
    }

    @Test
    public void getMandatoryDefaultValueBecausePropertyDoesNotExist() {
        assert false == this.tweetDataConfigurationFactory.getValueFromKey('mandatory_location')
    }


}
