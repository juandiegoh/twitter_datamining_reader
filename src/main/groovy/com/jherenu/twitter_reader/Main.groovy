package com.jherenu.twitter_reader

class Main {
    static main(args) {
        String keywords = 'miami heat'

        def twitterStreamReader = new TwitterStreamReader(keywords, 100)
        twitterStreamReader.startConsumer()

        while(twitterStreamReader.keepReading()) {
            sleep 1000
        }

        twitterStreamReader.stopConsumer()

        def tweetDataFactory = new TweetDataFactory(keywords)

        def results = twitterStreamReader.getResult()
        def tweetDataCollection = results.collect { tweetDataFactory.createFromMap(it) }

        tweetDataCollection.each() { tweetData ->
            println( tweetData.toRow() )
        }

        new File("salida.csv").withWriter { out ->
            out.writeLine(TweetDataToAnalyze.titleRow())
            tweetDataCollection.each() { tweetData ->
                out.writeLine( tweetData.toRow() )
            }
        }

    }
}
