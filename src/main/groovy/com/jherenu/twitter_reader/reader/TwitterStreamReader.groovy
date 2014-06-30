package com.jherenu.twitter_reader.reader

import twitter4j.FilterQuery
import twitter4j.TwitterStreamFactory
import twitter4j.UserStreamAdapter

class TwitterStreamReader {

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
        String[] keywordsArray = [keywords];
        String[] languages = ['en']
        filter.language(languages)
        filter.track(keywordsArray);

        stream.filter(filter);
    }

    def stopConsumer() {
        stream.cleanUp()
        stream.shutdown()
    }
}