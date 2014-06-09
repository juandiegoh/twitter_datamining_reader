package com.jherenu.tweeter_reader

import twitter4j.FilterQuery
import twitter4j.TwitterStreamFactory
import twitter4j.UserStreamAdapter

class TweeterStreamAdapter {

    def startReading() {
        def stream = new TwitterStreamFactory().instance
        def listener = [
                onStatus: { st ->
                    println "Count: ${counter++}"
                    tweetsCounter++
                    println "Tweets Counter: $st.text"

                    if(tweetsCounter > maxTweets) {
                        stream.cleanUp()
                        stream.shutdown()
                    }
                },//"$st.user.screenName: $st.text" },
                onException: { ex -> ex.printStackTrace() },
                onDeletionNotice: { statusDeletionNotice -> },
                onTrackLimitationNotice: { numberOfLimitedStatuses -> }
        ] as UserStreamAdapter
        stream.addListener(listener)

        FilterQuery filter = new FilterQuery();
        String[] keywordsArray = ['banega', 'eber banega'];
        String[] languages = ['en']
        filter.language(languages)
        filter.track(keywordsArray);

        stream.filter(filter);
    }
}
