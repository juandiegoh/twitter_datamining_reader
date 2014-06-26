package com.jherenu.twitter_reader

import com.google.common.collect.Queues

class Main {
    static main(args) {
        def queue = Queues.newArrayBlockingQueue(20)
        def tweetDataCollection = []

        String keywords = 'argentina,messi'
        def twitterStreamReader = new TwitterStreamReader(keywords, queue)
        twitterStreamReader.startConsumer()

        def tweetDataFactory = new TweetDataFactory(keywords)

        while(tweetDataCollection.size() < 100) {
            def tweetJSON = queue.poll()

            if(tweetJSON != null) {
                def tweetData = tweetDataFactory.createFromMap(tweetJSON)
                tweetDataCollection.add(tweetData)
            } else {
                println "Nothing to read, sleep 1000 ms"
                sleep 1000
            }
        }

        twitterStreamReader.stopConsumer()

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
