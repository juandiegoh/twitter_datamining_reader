package com.jherenu.twitter_reader.validators.data_validators

import com.jherenu.twitter_reader.TweetDataConfiguration
import com.jherenu.twitter_reader.TweetDataToAnalyze

class ExtraWordsValidator implements DataValidator {

    TweetDataConfiguration configuration

    ExtraWordsValidator(TweetDataConfiguration configuration) {
        this.configuration = configuration
    }

    @Override
    Boolean validate(tweetDataToAnalyze) {

        def extraWords = this.configuration.getExtraWords().split(',')

        for(String extraWord : extraWords) {
            if(textContainsExtraWord(tweetDataToAnalyze, extraWord)) {
                return true
            }
        }

        return false
    }

    private boolean textContainsExtraWord(TweetDataToAnalyze tweetDataToAnalyze, String extraWord) {
        tweetDataToAnalyze.getText().toLowerCase().contains(extraWord.toLowerCase().trim())
    }
}
