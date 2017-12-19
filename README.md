## SoundBoardX

> Android soundboard with particles on click.

---

### Preview

![]()
![]()
![]()
![]()
![]()

### Features

* **Powerful** yet **lightweight**.
* **Rapid consecutive sound clicks**. Spam with no fear. The application uses **async** event bus to trigger sounds.
* **Particles on click**. `SoundBoardX` is powered with a particle system that gives your clicks some swag.
* **Set** sounds as **ringtone**, **notification** or **alarm** tone.
* Indexable. **Search** the sound you are looking for, without scrolling over all those sounds.
* **Categorized**. Sounds are grouped into categories for easier and quicker experience. Just click the drawer icon or just swipe to see the categories. Supports RTL.
* Customisable. **Colorize the entire application** using the color picker. Choose a color from the given material color palette that is located at the bottom of the drawer.

### Compatibility

Android **5.0** *(LOLLIPOP)* or later.

Compiled with SDK version `27` and built with SDK version `27.0.2`

### Known Bugs

* Consecutive clicking on sounds and on mute button at the same time, crashes the `SoundPlayer`
  * What does it affect?
    * Mute button: Does not work
    * Sounds: Are being played at the background (more than once at the same time)
  * How to bypass the issue?
    * **Fully** restart the application

### Libraries Used

* [Events](https://github.com/greenrobot/EventBus)
* [Particle System](https://github.com/plattysoft/Leonids)
* [Material Drawer](https://github.com/mikepenz/MaterialDrawer)
* [Iconics](https://github.com/mikepenz/Android-Iconics)
* [Floating Action Button](https://github.com/Clans/FloatingActionButton)
* [Color Picker](https://github.com/kristiyanP/colorpicker)
* [Animated Favorite Button](https://github.com/IvBaranov/MaterialFavoriteButton)
* [About](https://github.com/medyo/android-about-page)

### License

Copyleft (ↄ) 2017 by Sotirios M. Roussis. Some rights reserved

ship is under the terms of the GPLv3+ License, following all clarifications stated in the [license](LICENSE.md) file
