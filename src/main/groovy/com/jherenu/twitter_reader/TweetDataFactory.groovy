package com.jherenu.twitter_reader

import twitter4j.StatusJSONImpl
//import uk.ac.wlv.sentistrength.*

class TweetDataFactory {

    TweetDataFactory() {
//        SentiStrength sentiStrength = new SentiStrength()
    }

    def createFromMap(StatusJSONImpl tweetJSON) {
        TweetDataToAnalyze tweetDataToAnalyze = new TweetDataToAnalyze()
        tweetDataToAnalyze.with {
            it.createdAt = tweetJSON.createdAt
            it.favoriteCount = tweetJSON.favoriteCount
            it.favorited = tweetJSON.favorited
            it.latitude = tweetJSON.geoLocation?.latitude
            it.longitude = tweetJSON.geoLocation?.longitude
            it.id = tweetJSON.id.toString()
            it.lang = tweetJSON.lang
            it.country = tweetJSON.place?.country
            it.countryCode = tweetJSON.place?.countryCode
            it.text = tweetJSON.text
            it.userId = tweetJSON.user?.id
            it.userFollowersCount = tweetJSON.user?.followersCount
            it.userFriendsCount = tweetJSON.user?.friendsCount
            it.strength = this.calculateStrength(it.text)
        }
        return tweetJSON
    }

    def calculateStrength(String text) {

    }
}
