package com.jherenu.twitter_reader.validators.data_validators

class NoRepetitionDataValidator implements DataValidator {


    Map dataCollection

    NoRepetitionDataValidator(dataCollection) {
        this.dataCollection = dataCollection
    }

    @Override
    Boolean validate(data) {
        return keyIsNotInCollection(data)
    }

    private boolean keyIsNotInCollection(data) {
        !dataCollection.containsKey(data.getId())
    }
}
