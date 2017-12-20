<p align="center"><img width=25% src="/extras/imgs/logo.png"></img></p>
<p align="center">SoundBoardX</p>

---

> Material designed android soundboard with particles on tap; developed with ❤️ by **Sotirios M. Roussis**

---

### Compatibility

Android **5.0** *(LOLLIPOP)* 🍭 or later.

Compiled with SDK version `27` and built with SDK version `27.0.2`

### Features

* **Powerful** 💪 yet **lightweight**. Hardware accelerated 
* **Rapid consecutive taps**. Spam 💨 with no fear. The application uses **async** callbacks to trigger sounds
* **Mute button** 🔇 to stop the `SoundPlayer`
* **Particles on tap** 👇. `SoundBoardX` is powered with a particle system that gives your taps ✨ swag. Can be disabled.
* Mark sounds as **favorites** ❤️
* **Set** sounds as **ringtone**, **notification** or **alarm** tone 🔔
* Indexable. **Search** the sound you are looking for, without scrolling lists 🔍
* **Categorized**. Sounds are grouped into categories 📜 for easier and quicker experience. Just click the drawer icon or just swipe to see the categories. Supports RTL
* Customisable. **Colorize the entire application** using the color picker. Choose a color from the given material color palette 🎨 that is located at the bottom of the drawer

### Preview

|                 Preview                   |  Description                            |
|:-----------------------------------------:|:----------------------------------------|
| ![](/extras/imgs/particles.gif)           | Rapid consecutive taps with particles   |
| ![](/extras/imgs/categories.gif)          | Categorized sounds into drawer          |
| ![](/extras/imgs/favoritescategories.gif) | Favorites                               |
| ![](/extras/imgs/search.gif)              | Filtering user input                    |
| ![](/extras/imgs/searchfavorites.gif)     | Filtering user input on favorites       |
| ![](/extras/imgs/setas.gif)               | Set as ringtone, notification and alarm |
| ![](/extras/imgs/colors.gif)              | Material color palette                  |
| ![](/extras/imgs/powersaving.gif)         | Power saving mode aware                 |


### Known Bugs

* Consecutive clicking on sounds and on mute button at the same time, crashes the `SoundPlayer`
  * What does it affect?
    * Mute button: Does not work
    * Sounds: Are being played at the background (more than once at the same time)
  * How to bypass the issue?
    * **Fully** restart the application

### Thanks To

##### People

* [Panagiotis Keranoudis](https://github.com/notiskeranoudis) helped improving the application's content by adding, removing and modifying particles, sounds and titles. Also helped mapping sounds with particles programmatically

##### Libraries

* [About](https://github.com/medyo/android-about-page)
* [Animated Favorite Button](https://github.com/IvBaranov/MaterialFavoriteButton)
* [Color Picker](https://github.com/kristiyanP/colorpicker)
* [Events](https://github.com/greenrobot/EventBus)
* [Floating Action Button](https://github.com/Clans/FloatingActionButton)
* [Iconics](https://github.com/mikepenz/Android-Iconics)
* [Material Drawer](https://github.com/mikepenz/MaterialDrawer)
* [Particle System](https://github.com/plattysoft/Leonids)

### Contribution

Pull requests, issues, suggestions, testing and feedback are all welcome

* Fork the repo
* Create a new branch
  * `$ git checkout -b my-new-feature`
* Make the appropriate changes in the files
* Add changes to reflect the changes made
* Commit your changes
  * `$ git commit -am 'Added some feature'`
* Push to the branch
  * `$ git push origin my-new-feature`
* Create a Pull Request on `dev`

### License

Copyleft (ↄ) 2017 by **Sotirios M. Roussis**. Some rights reserved

`SoundBoardX` is under the terms of the GPLv3+ License, following all clarifications stated in the [license](LICENCE.md) file
