# Mi Mercado Libre

Native Android app for those Mercado Libre's buyers/sellers that want to have an extremely personalized experience when searching items in MercadoLibre.

[![Download it or see some application screenshots directly from the Android Play Store](http://developer.android.com/images/brand/en_generic_rgb_wo_60.png "Download it or see some application screenshots directly from the Android Play Store")](https://play.google.com/store/apps/details?id=com.nbempire.mimercadolibre)

## Project status
[![version](https://img.shields.io/badge/version-1.2.0-brightgreen.svg)](https://play.google.com/store/apps/details?id=com.nbempire.mimercadolibre) 
[![Semver](http://img.shields.io/SemVer/2.0.0.png)](http://semver.org/spec/v2.0.0.html)
[![stable](https://img.shields.io/badge/stability-unstable-yellow.svg)](https://nodejs.org/api/documentation.html#documentation_stability_index)

## Before generating a new release

* Update `MainKeys.API_HOST` constant field.
* Update `ga_trackingId` property in `app_tracker.xml`.
* Update `android.signingConfigs.release` properties in `build.gradle`.


## Coming soon!

Receive notifications about new articles that matches with a very specific set of rules like:


* A new article that is the cheapest article for a specific product. E.g. Ipod nano/touch of 8/16/32gb.
* A new article which price is lower/higher than the average price.
* Every new article of that product.
