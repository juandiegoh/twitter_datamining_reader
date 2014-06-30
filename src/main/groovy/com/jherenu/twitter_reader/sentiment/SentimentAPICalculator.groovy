package com.jherenu.twitter_reader.sentiment

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder

class SentimentAPICalculator implements SentimentCalculator{

    public static final String POSITIVE = 'pos'
    public static final String NEGATIVE = 'neg'
    String keywords

    SentimentAPICalculator(keywords) {
        this.keywords = keywords
    }

    @Override
    SentiValue calculateSentiValue(String text) {

        def response = null
        try {
            HTTPBuilder httpBuilder = new HTTPBuilder( 'http://text-processing.com/api/sentiment/')
            response = httpBuilder.post(
                    body : [ text:text, language: 'english' ],
                    requestContentType : ContentType.URLENC)
        } catch(e) {
            println 'Hubo un error al leer de la api de sentiment'
        }
        return createSentiValueFromResponse(response)
    }

    private def createSentiValueFromResponse(response) {
        if(response) {
            switch (response.label) {
                case POSITIVE: return SentiValue.POSITIVE
                    break
                case NEGATIVE: return SentiValue.NEGATIVE
                    break
                default: return SentiValue.NEUTRAL
                    break
            }
        }

        return SentiValue.NEUTRAL
    }
}
