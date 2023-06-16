analytics-android-integration-batch
======================================

# Deprecation notice

This integraion has been deprecated and does not work with recent Segment SDKs. Please contact us for more info on how to connect Segment and Batch depending on your usecase.

======================================

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.batch.android/sdk-segment-integration/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.batch.android/sdk-segment-integration)

Batch.com integration for [analytics-android](https://github.com/segmentio/analytics-android).

## Usage

After adding the dependency, you must register the integration with our SDK. To do this, import the Batch integration:


```java
import com.segment.analytics.android.integrations.batch.BatchIntegration;
```

And add the following line:

```java
Analytics analytics = new Analytics.Builder(this, "write_key")
                .use(BatchIntegration.getFactory(this))
                .build();
```

Please also add the following to all of your activities:

```
@Override
protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    Batch.onNewIntent(intent);
}
```

Track an event :

```java
Properties trackProperties = new Properties();
trackProperties.putTitle("MyLabel");
Analytics.with(context).track("MyEventName", trackProperties);
```

The sdk-segment-integration module contains the Batch integration, and the app module contains an app sample.

## Integrating with other Batch features (recommended)

If you want to use this integration, but use other Batch features than Analytics, you may want to follow the [standard integration steps](https://batch.com/doc/android/sdk-integration/initial-setup.html), and then add this line in your Application subclass, **before** any segment call:

```java
BatchIntegrationConfig.enableAutomaticLifecycleManagement = false;
```

This will let your code fully control the configuration of the SDK, and calling of the lifecycle methods. Note that by enabling this, you will have to add Batch.onStart/onStop/onDestroy calls yourself, as indicated by Batch's documentation.

## Optional dependencies

This library does not strictly require Firebase to work, but they're recommended. If you don't add the firebase libraries, notifications will not work.  

We recommend you add the following to your build.gradle (version 12.0.1 is not strictly required, newer versions are compatible):  

```
implementation "com.google.firebase:firebase-core:12.0.1"
implementation "com.google.firebase:firebase-messaging:12.0.1"
```

## Modifying this library

To contribute on this library, simply use Android Studio as you would with any project.

You can run the unit tests from a CLI using:  
```
./gradlew clean testDebug
``` 
at the root of the project.
