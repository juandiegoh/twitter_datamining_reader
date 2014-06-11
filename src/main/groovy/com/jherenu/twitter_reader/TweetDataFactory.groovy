package com.jherenu.twitter_reader

import twitter4j.StatusJSONImpl
import uk.ac.wlv.sentistrength.SentiStrength

class TweetDataFactory {

    def keywords

    TweetDataFactory(keywords) {
        this.keywords = keywords.replace(' ',',')
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
            it.text = tweetJSON.text.replace('\n',' ').replace(',','')
            it.userId = tweetJSON.user?.id
            it.userFollowersCount = tweetJSON.user?.followersCount
            it.userFriendsCount = tweetJSON.user?.friendsCount
            it.strength = this.calculateStrength(it.text)
        }
        return tweetDataToAnalyze
    }

    def calculateStrength(String text) {
        return calculateSentiValue(keywords, text)
    }

    def SentiValue calculateSentiValue(String keywords, String text) {
        def (int positive, int negative) = calculatePositiveAndNegativeValue(keywords, text)
        int result = positive + negative
        return result == 0 ? SentiValue.NEUTRAL : result > 0 ? SentiValue.POSITIVE : SentiValue.NEGATIVE
    }

    def calculatePositiveAndNegativeValue(String keywords, String text) {
        SentiStrength sentiStrength = new SentiStrength()

        String[] ssthInitialisationAndText = ["sentidata", "/Users/jherenu/Documents/SentStrength_Data/", "keywords", keywords, "explain"]
        sentiStrength.initialise(ssthInitialisationAndText)

        String result = sentiStrength.computeSentimentScores(text)
        def tokens = result.tokenize(' ')
        def sentiStrings = tokens.subList(0,2)
        def sentiNumbers = sentiStrings.collect { Integer.valueOf(it) }
        return sentiNumbers
    }
}
