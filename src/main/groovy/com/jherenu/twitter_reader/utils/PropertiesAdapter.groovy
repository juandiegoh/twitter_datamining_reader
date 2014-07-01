package com.jherenu.twitter_reader.utils

class PropertiesAdapter {

    def config

    PropertiesAdapter() {
        this('config.groovy')
    }

    PropertiesAdapter(String path) {
        def file = new File(path)
        this.config = new ConfigSlurper().parse(file.toURI().toURL())
    }

    String getSentimentProperty() {
        return this.config.sentiment_calculator
    }
}
