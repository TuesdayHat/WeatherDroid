# WeatherDroid

#### _Compare weather predictions from multiple weather models_
#### _Adam Calhoun_

## Description
_takes in weather prediction data for a user input location from public weather APIs and displays them side by side, or display an average of all predictions for the given location_

## Planned Features/ToDo List
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