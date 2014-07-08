package com.jherenu.twitter_reader.reader

import twitter4j.FilterQuery
import twitter4j.TwitterStreamFactory
import twitter4j.UserStreamAdapter

class TwitterStreamReader {

    public static final double[][] WHOLE_WORLD = [[-180, -90], [180, 90]]
    def stream
    def queue
    def tweetsCounter = 0
    def keywords

    TwitterStreamReader(keywords, queue) {
        this.stream = new TwitterStreamFactory().instance
        this.keywords = keywords
        this.queue = queue
    }

    def startConsumer() {
        def listener = [
                onStatus: { st ->
                    println "Count: ${tweetsCounter++}"
                    queue.put(st)
                },
                onException: { ex -> ex.printStackTrace() },
                onDeletionNotice: { statusDeletionNotice -> },
                onTrackLimitationNotice: { numberOfLimitedStatuses -> }
        ] as UserStreamAdapter
        stream.addListener(listener)

        FilterQuery filter = new FilterQuery();
//        String[] keywordsArray = [keywords];
//        filter.track(keywordsArray);
        String[] languages = ['en']
        filter.language(languages)
        double[][] location = WHOLE_WORLD
        filter.locations(location)

        stream.filter(filter);
    }

    def stopConsumer() {
        stream.cleanUp()
        stream.shutdown()
    }
}