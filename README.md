# 🍻 Muh Beers 🍻

Simple Android app showing off Firestore's capabilities along with some other Firebase libraries.

It's basically a big chat room that lets you post beers you've had and rate them to be seen by everyone else using the app live. 

## Setup 

Pretty straightforward, just clone the project or download the .zip version. You'll need to go to the [Firebase Console](https://console.firebase.google.com) and setup a project. 

This project uses Google Authentication: 

* You'll need to go to the `Authentication` tab in the console
* Go to the `SIGN-IN METHOD` tab
* Select the Google option
* Check `enable` 
* The dialog also mentions you need to add your SHA1 finger print. It provides two links, one with how to get the fingerpring and another to where in settings you need to add it. Do both of those things and leave the settings page open.
* Click `save` on the Authentication dialog
* Download the `google-services.json` from your project settings page
* Place the `google-services.json` inside the `/app/` folder in the project 
* Done
