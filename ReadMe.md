# WeatherDroid

#### _Compare weather predictions from multiple weather models_
#### _Adam Calhoun_

## Description
_takes in weather prediction data for a user input location from public weather APIs and displays them side by side, or display an average of all predictions for the given location_

## Setup
* clone from github
* open in Android Studio
* create a new file called gradle.properties in the project root directory
    * get yourself an API key from Open Weather Map
    * in gradle.properties, add ``OpenWeatherMapKey = "YOUR_API_KEY_HERE"``
    * rebuild (or resync) Gradle
* run 'app'; this should prompt you to create an emulated Android device if you don't have one already.
    * Configure the device and it should start as soon as the emulator is finished booting up.
    * this app was written and tested on an emulated Samsung Nexus 6, running Android 6.0 Marshmallow
    

## Planned Features/ToDo List

* Plan for UI:
    * Input Location => show current temp, list of all sources
    * >> tap a source => bring up details for tapped source (hourly forecast if available)
        * scroll down for 5 day forecast
        * swipe left/right for other sources

* main page to display all results
    * top/first screen shows average of all results, scroll down for simplified results from individual sources
    * click/navigate to individual pages for sources, see detailed information from that source, short description of how that model works/what's different about it
* User accounts
    * users may ask the app to hold onto location information (read: load and display cities they want to keep track of weather reports for every time the app opens)
    * users may rate sources for accuracy, have the app weight more accurate sources more heavily in 'Average'/combined results
        * also keep track of most accurate models for modifiers like location, types of weather, how early was the prediction
            * ex: one model is very accurate in Portland but doesn't care about DC or London, another is good at predicting the paths of hurricanes but not necessarily good at whether it's going to rain today in general. Or if a model is pretty good at making predictions 10 days out, but more local sources will have more accurate data to tell you whether to bring an umbrella as you're walking out the door.
    * users may have the app hold onto old weather data for their location
        * **RESEARCH**: how frequently do most weather trackers update their information? could that be used to automate accuracy recording? 
            * Read: compare predictions made when first available vs a day before vs an hour before vs an hour after.
    
    
### License
* _GNU GPLv3_

Copyright (c) 2018 Adam Calhoun