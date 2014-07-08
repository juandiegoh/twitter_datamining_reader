package com.jherenu.twitter_reader.validators.data_validators

import com.jherenu.twitter_reader.TweetDataToAnalyze
import com.jherenu.twitter_reader.configuration.TweetDataConfiguration

class ExtraWordsValidator implements DataValidator {

    TweetDataConfiguration configuration

    ExtraWordsValidator(TweetDataConfiguration configuration) {
        this.configuration = configuration
    }

    @Override
    Boolean validate(tweetDataToAnalyze) {

        def extraWords = this.configuration.getExtraWords().split(',')
        if(extraWords.size() > 0) {
            for(String extraWord : extraWords) {
                if(textContainsExtraWord(tweetDataToAnalyze, extraWord)) {
                    return true
                }
            }
            return false
        } else {
            return true
        }
    }

    private boolean textContainsExtraWord(TweetDataToAnalyze tweetDataToAnalyze, String extraWord) {
        tweetDataToAnalyze.getText().toLowerCase().contains(extraWord.toLowerCase().trim())
    }
}
