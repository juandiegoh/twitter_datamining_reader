package com.jherenu.twitter_reader

import com.google.common.collect.Queues
import com.jherenu.twitter_reader.configuration.DefaultTweetDataConfigurationFactory
import com.jherenu.twitter_reader.reader.TwitterStreamReader
import com.jherenu.twitter_reader.utils.PropertiesAdapter
import com.jherenu.twitter_reader.validators.TweetDataValidatorFactory

class Main {
    static main(args) {
        PropertiesAdapter propertiesAdapter = new PropertiesAdapter()
        def tweetDataConfigurationFactory = new DefaultTweetDataConfigurationFactory(propertiesAdapter)

        def configuration = tweetDataConfigurationFactory.createTweetDataConfiguration()

        println '***************** Running with Configuration: *****************'
        println "* keywords = ${configuration.getKeywords()}"
        println "* extra_words = ${configuration.getExtraWords()}"
        println "* sentiment_calculator = ${configuration.getSentimentCalculator()}"
        println "* size_of_data = ${configuration.getSizeOfData()}"
        println "***************************************************************"

        def queue = Queues.newArrayBlockingQueue(100)

        def twitterStreamReader = new TwitterStreamReader(configuration.getKeywords(), queue)
        twitterStreamReader.startConsumer()

        def tweetDataCollection = [:]
        def tweetDataValidator = new TweetDataValidatorFactory().createValidator(configuration, tweetDataCollection)
        def tweetDataFactory = new TweetDataFactory()
        def sentimentCalculator = configuration.getSentimentCalculator()
        while(tweetDataCollection.size() < configuration.getSizeOfData()) {
            def tweetJSON = queue.poll()

            if(tweetJSON != null) {
                def tweetData = tweetDataFactory.createFromMapWithoutSentiment(tweetJSON)
                if(tweetDataValidator.validateTweetData(tweetData)) {
                    tweetData.setStrength(sentimentCalculator.calculateSentiValue(tweetData.getText()))
                    tweetDataCollection.put(tweetData.getId(), tweetData)
                }
            } else {
                println "Nothing to read, sleep 1000 ms"
                sleep 1000
            }
            println "Size of results: ${tweetDataCollection.size()}"
        }

        twitterStreamReader.stopConsumer()

        new File("salida.csv").withWriter { out ->
            out.writeLine(TweetDataToAnalyze.titleRow())
            tweetDataCollection.values().each() { tweetData ->
                out.writeLine( tweetData.toRow() )
            }
        }

    }
}
