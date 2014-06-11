package com.jherenu.twitter_reader

class TweetDataToAnalyze {
    Date createdAt
    int favoriteCount
    boolean favorited
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
        return "createdAt, favoriteCount, favorited, latitude, longitude, id, lang, country, countryCode, " +
                "text, userId, userFollowersCount, userFriendsCount, strength"
    }

    String toRow() {
        return "${createdAt}, ${favoriteCount}, ${favorited}, ${latitude}, ${longitude},${id}, ${lang}, ${country}, " +
                "${countryCode}, ${text}, ${userId}, ${userFollowersCount},${userFriendsCount}, ${strength}"
    }
}
