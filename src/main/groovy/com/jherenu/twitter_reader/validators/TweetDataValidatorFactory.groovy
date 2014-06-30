package com.jherenu.twitter_reader.validators

import com.jherenu.twitter_reader.validators.data_validators.ExtraWordsValidator
import com.jherenu.twitter_reader.validators.data_validators.MandatoryLocationValidator

class TweetDataValidatorFactory {

    TweetDataValidator createValidator(configuration) {
        def validators = [new ExtraWordsValidator(configuration), new MandatoryLocationValidator(configuration)]
        return new TweetDataValidator(validators)
    }
}
