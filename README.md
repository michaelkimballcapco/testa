# Mobile assignment PS Readme #

## Configuring the App ##

To configure this app, create a gradle.properties file modeled after the gradle.example.properties file and fill it with your API keys.

## Feedback ##

Android Studio 3.2 was used supporting Android 4.1+.

It would be better to support 4.4 so that the app could use updated google play services to provide a better map experience. 4.1-4.4 represent less than 4% of google play store users and it is unlikely that they will be in the marketshare of clients. Consider if the user is using that version of android, how likely are they to utilize online banking? Not likely. The cost of support is not worth the return of customer use.

There were requirements to not use 3rd party libraries, so they were not used. However, industry standard suggests that it greatly exceeds development time to use basic and time-tested libraries like: Retrofit, RxJava, Dagger2 and others. By not allowing the developers to utilize these tools and forcing a short time-table it is making an unreasonable request. This is indicative of how real world mobile development should be done.

As previously stated, the limitations of the libraries and forced support of old Android versions coupled with the shortened timetable have made it so that some pieces had to be cut. Unit tests, error-handling and single-screen multi-fragments where all casualties of this.

To continue about unit testing, it should be noted that writing unit tests without using a dependency injection framework or unit testing library does not make sense.

SharedPreferences was used for storage as it was the easiest choice. There are many options for storage.

ConstraintLayout is now the layout of choice, but Coordinator layout was also implemented. Along with a Navigation Drawer and RecyclerView

This does utilize a single activity with multiple fragments, but it should be known that the Android development community is moving away from fragments and towards views because of the unnecessarily complex lifecycle of the fragments.

In this app you will find:

* Home Screen for managing locations
* Map Modal for adding new locations
* City Screen for viewing Current Forecast (there was no rain chance in the API)
* Help Screen with a webview including a video tutorial of using the app
* Settings Screen (Bonus) for managing the app


## Instructions ##

### Repo ###

https://github.com/michaelkimballcapco/weather-app

### Overview ###

The goal of this assignment is to evaluate the problem solving skills, UX judgement and code quality of the candidate.

Weather, everybody wants to know how it is going to be during the week. Will it be rainy, windy, or sunny? Luckily for us, in the information age, there are open APIs to retrieve information about it.
For this assignment you will be using the API from: http://openweathermap.org/api. The API key is provided at the end of the statement, or you can request your own by registering on the website for free.

### Requirements ###

Your app should at least contain the following screens:

* Home screen:
	* Showing a list of locations that the user has bookmarked previously.
	* Show a way to remove locations from the list
	* Add locations by placing a pin on map.
* City screen: once the user clicks on a bookmarked city this screen will appear. On this screen the user should be able to see:
	* Today’s forecast, including: temperature, humidity, rain chances and wind information
* Help screen: The help screen should be done using a webview, and contain information of how to use the app, gestures available if any, etc.

### Bonus ###

The following bonus points can be implemented:

* Settings page: where the user can select some preferences like: unit system
(metric/imperial), any other user setting you consider relevant, e.g. reset cities
bookmarked.
* On the city screen: show the 5-days forecast, including: temperature, humidity, rain
chances and wind information.
* On the home screen, implement a list of known locations with search capabilities.

How navigation occurs, or how elements are placed on the screen is open for interpretation and creativity.

### Additional Requirements ###

Additionally, the following requirements have to be met:

* Alpha/beta versions of the IDE are forbidden, you must work with the stable version of
the IDE
* The API has to be consumed in JSON format
* The UI has to be responsive (landscape and portrait orientations, and tablet resolutions
must be supported)
* The code has to be published on GitHub or Bitbucket. We want to see the progress evolution

### Android Requirements ###

* For Android:
	* Language must be Java
	* The coordinator layout must be used at least in one of the screens.
	* UI has to be implemented using 1 activity with multiple fragments
	* Only 3rd party libraries allowed are: GSON or Jackson
	* Compatible with Android 4.1+



