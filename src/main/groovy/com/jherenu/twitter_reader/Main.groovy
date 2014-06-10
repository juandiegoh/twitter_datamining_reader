package com.jherenu.twitter_reader

class Main {
    static main(args) {
        println 'Hello World'
        def twitterStreamReader = new TwitterStreamReader()
        twitterStreamReader.startConsumer()

        while(twitterStreamReader.keepReading()) {
            sleep 1000
        }

        twitterStreamReader.stopConsumer()

        def tweetDataFactory = new TweetDataFactory()

        def results = twitterStreamReader.getResult()
        def tweetDataCollection = results.collect { tweetDataFactory.createFromMap(it) }
    }
}
