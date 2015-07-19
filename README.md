# Mi Mercado Libre

Native Android app for those Mercado Libre's buyers/sellers that want to have an extremely personalized experience when searching items in MercadoLibre.

[![Download it or see some application screenshots directly from the Android Play Store](http://developer.android.com/images/brand/en_generic_rgb_wo_60.png "Download it or see some application screenshots directly from the Android Play Store")](https://play.google.com/store/apps/details?id=com.nbempire.mimercadolibre)

## Status
[![GitHub version](https://badge.fury.io/gh/mi-mercado-libre%2Fpiseis.svg)](https://github.com/barriosnahuel/mi-mercado-libre/releases)
[![Semver](http://img.shields.io/SemVer/2.0.0.png)](http://semver.org/spec/v2.0.0.html)
[![stable](https://img.shields.io/badge/stability-unstable-yellow.svg)](https://nodejs.org/api/documentation.html#documentation_stability_index)


## Repo usage

### Branching model

*Do not clone or push to* **master** *branch.*

Create branch or fork from **develop**, then push or create pull requests (if you don't have access) to that branch.

The repo uses [this branching model](http://nvie.com/posts/a-successful-git-branching-model/).
If you are a command line lover, then you should give a try to [Git Flow](https://github.com/petervanderdoes/gitflow).

### Issues management

[![Stories ready to be worked on](https://badge.waffle.io/barriosnahuel/mi-mercado-libre_android.png?label=ready&title=Ready)](https://waffle.io/barriosnahuel/barriosnahuel/mi-mercado-libre_android) [![Stories in progress](https://badge.waffle.io/barriosnahuel/mi-mercado-libre_android.png?label=in progress&title=In Progress)](https://waffle.io/barriosnahuel/mi-mercado-libre_android)

[![Throughput Graph](https://graphs.waffle.io/barriosnahuel/mi-mercado-libre_android/throughput.svg)](https://waffle.io/barriosnahuel/mi-mercado-libre_android/metrics)

### Generate a new release

* Update `MainKeys.API_HOST` constant field.
* Update `ga_trackingId` property in `app_tracker.xml`.
* Update `android.signingConfigs.release` properties in `build.gradle`.


## Coming soon!

Receive notifications about new articles that matches with a very specific set of rules like:


* A new article that is the cheapest article for a specific product. E.g. Ipod nano/touch of 8/16/32gb.
* A new article which price is lower/higher than the average price.
* Every new article of that product.
