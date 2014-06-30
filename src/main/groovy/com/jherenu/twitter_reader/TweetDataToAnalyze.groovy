package com.jherenu.twitter_reader

import com.jherenu.twitter_reader.sentiment.SentiValue

class TweetDataToAnalyze {
    Date createdAt
    int favoriteCount
    int retweetCount
    Number latitude
    Number longitude
    String id
    String lang
    String country
    String countryCode
    String text
    long userId
    int userFollowersCount
    int userFriendsCount
    SentiValue strength

    static String titleRow() {
        return "createdAt, favoriteCount, retweetCount, latitude, longitude, id, lang, country, countryCode, " +
                "text, userId, userFollowersCount, userFriendsCount, strength"
    }

    String toRow() {
        return "${createdAt}, ${favoriteCount}, ${retweetCount}, ${latitude}, ${longitude},${id}, ${lang}, ${country}, " +
                "${countryCode}, ${text}, ${userId}, ${userFollowersCount},${userFriendsCount}, ${strength}"
    }
}
