# vfdReminder

**Class:** Volunteer Fire Department Fun

**Language:** Java

**Platform:** Android

A small app for providing reminders to members of our local volunteer fire department.

## Setup

First of all, install Android Studio

Then download the sources here as well as the Toolbox-Java project

Then call the updateDependencies.bat script

Finally, import this project into Android Studio

## Build

In Android Studio, you can build this in two different ways:

1. Debug build (click on Build > Build APK(s)), which will be saved to
```vfdReminder/app/build/outputs/apk/debug/app-debug.apk```

2. Release build (click on Build > Generate Signed APK...), which will be saved to
```vfdReminder/app/release/app-release.apk```

Either way, after you are done building the APK fine, you can either load it in an emulator (which I never got to work because my computer is real slow and old), or load it onto your Android smartphonedevicething. (To do so, just connect your phone via USB to the computer, copy the file over to a folder inside the phone, open the file on your phone with any file browser app, and then click yes to install it. When juggling different versions - e.g. ones downloaded from the Play Store and ones manually installed - you might have to manually uninstall the previous version of the app first via Settings > Apps on the phonedevicething.)

## Internal Structure

The Android client on the smartphone sends a request on initial startup, and if running for a longer time once a day, asking the server about upcoming events.
The server replies to these requests, providing the events from its internal SQL datastructure.

When the client notices an event coming close, it shows a notification to the user. The result of this is sent to the server.

The list is open for everyone (or should it not be?), and everyone can check their app to see which other people responded how.
The server for this provides the data, again upon request of the client.

For now, there is no pretty way of entering event data to the server; we will just do that directly in the backend.

## License

We at A Softer Space really love the Unlicense, which pretty much allows anyone to do anything with this source code.
For more info, see the file UNLICENSE.

If you desperately need to use this source code under a different license, [contact us](mailto:moya@asofterspace.com) - I am sure we can figure something out.