package com.jherenu.twitter_reader

import twitter4j.FilterQuery
import twitter4j.TwitterStreamFactory
import twitter4j.UserStreamAdapter

class TwitterStreamReader {

    def MAX_TWEETS = 5

    def stream
    def tweets = []
    def tweetsCounter = 0
    def counter = 0

    TwitterStreamReader() {
        this.stream = new TwitterStreamFactory().instance
    }

    def startConsumer() {
        def listener = [
                onStatus: { st ->
                    println "Count: ${tweetsCounter++}"
                    println "Tweets Counter: $st.text"
                    tweets.add(st)
                },//"$st.user.screenName: $st.text" },
                onException: { ex -> ex.printStackTrace() },
                onDeletionNotice: { statusDeletionNotice -> },
                onTrackLimitationNotice: { numberOfLimitedStatuses -> }
        ] as UserStreamAdapter
        stream.addListener(listener)

        FilterQuery filter = new FilterQuery();
        String[] keywordsArray = ['san antonio spurs'];
        String[] languages = ['en']
        filter.language(languages)
        filter.track(keywordsArray);

        stream.filter(filter);
    }

    boolean keepReading() {
        return tweets.size() < MAX_TWEETS
    }

    def stopConsumer() {
        stream.cleanUp()
        stream.shutdown()
    }

    def getResult() {
        return tweets
    }
}