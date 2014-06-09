package com.jherenu.tweeter_reader

class Main {
    static main(args) {
        println 'Hello World'
        def twitterStreamReader = new TwitterStreamReader()
        twitterStreamReader.startConsumer()

        while(twitterStreamReader.keepReading()) {
            sleep 1000
        }

        twitterStreamReader.stopConsumer()

        println twitterStreamReader.getResult()
    }
}
