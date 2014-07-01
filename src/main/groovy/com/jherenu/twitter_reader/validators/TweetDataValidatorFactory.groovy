package com.jherenu.twitter_reader.validators

import com.jherenu.twitter_reader.validators.data_validators.ExtraWordsValidator
import com.jherenu.twitter_reader.validators.data_validators.MandatoryLocationValidator
import com.jherenu.twitter_reader.validators.data_validators.NoRepetitionDataValidator

class TweetDataValidatorFactory {

    TweetDataValidator createValidator(configuration, dataCollection) {
        def validators = [
                new ExtraWordsValidator(configuration),
                new MandatoryLocationValidator(configuration),
                new NoRepetitionDataValidator(dataCollection)]
        return new TweetDataValidator(validators)
    }
}
