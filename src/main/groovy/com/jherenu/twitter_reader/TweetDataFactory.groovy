package com.jherenu.twitter_reader

import com.jherenu.twitter_reader.sentiment.SentiValue
import com.jherenu.twitter_reader.sentiment.SentiStrengthCalculator

class TweetDataFactory {

    SentiStrengthCalculator sentiValueCalculator

    TweetDataFactory(keywords) {
        this.sentiValueCalculator = new SentiStrengthCalculator(keywords)
    }

    def createFromMap(def tweetJSON) {
        TweetDataToAnalyze tweetDataToAnalyze = new TweetDataToAnalyze()

        // Si es un retweet me interesa el tweet original
        if(tweetJSON.retweetedStatus != null) {
            tweetJSON = tweetJSON.retweetedStatus
        }

        tweetDataToAnalyze.with {
            it.createdAt = tweetJSON.createdAt
            it.favoriteCount = tweetJSON.favoriteCount
            it.retweetCount = tweetJSON.retweetCount
            it.latitude = tweetJSON.geoLocation?.latitude
            it.longitude = tweetJSON.geoLocation?.longitude
            it.id = tweetJSON.id.toString()
            it.lang = tweetJSON.lang
            it.country = tweetJSON.place?.country
            it.countryCode = tweetJSON.place?.countryCode
            it.text = tweetJSON.text.replace('\n',' ').replace(',','')
            it.userId = tweetJSON.user?.id
            it.userFollowersCount = tweetJSON.user?.followersCount
            it.userFriendsCount = tweetJSON.user?.friendsCount
            it.strength = this.calculateStrength(it.text)
        }
        return tweetDataToAnalyze
    }

    SentiValue calculateStrength(String text) {
        return this.sentiValueCalculator.calculateSentiValue(text)
    }
}
