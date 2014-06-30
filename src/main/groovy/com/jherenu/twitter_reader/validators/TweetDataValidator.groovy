package com.jherenu.twitter_reader.validators

import com.jherenu.twitter_reader.TweetDataToAnalyze
import com.jherenu.twitter_reader.validators.data_validators.DataValidator

class TweetDataValidator {

    def validators

    TweetDataValidator(validators) {
        this.validators = validators
    }

    Boolean validateTweetData(TweetDataToAnalyze tweetDataToAnalyze) {
        for(DataValidator dataValidator : validators) {
            if(!dataValidator.validate(tweetDataToAnalyze)) {
                println "${dataValidator.toString()} is not valid for ${tweetDataToAnalyze.getText()}"
                return false
            }
        }
        return true
    }
}
