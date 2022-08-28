package com.jonathasdev.motivation.infra

class MotivationConstants private constructor(){
    object KEY {
        const val USER_NAME = "USER_NAME"
    }
    object FILTER {
        const val ALL = 1
        const val HAPPY = 2
        const val SUNNY = 3
    }
    object LANGUAGE {
        const val PT ="pt"
        const val ENGLISH = "en"
        const val FRENCH = "fr"
    }
}