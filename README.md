# KOJAC : KOJAC is Optics Java Applets Classes

## What Is KOJAC?

KOJAC is a set of Java classes implementing optical elements and optics laws in order to build and simulate optical systems. KOJAC is also aimed at being a demonstrator of optics for educational purposes. It has been developed at the IMT by Olivier Scherler during a training period.

## How Does It Work?

Optical systems are composed of objects with defined properties that may be modified by the user through the applet’s buttons and scrollbars. Such basic devices are lenses, apertures, interfaces, and may be themselves composite sets of other elements.

Do note that many optical devices can be described by the composition of simple bricks. Spherical interfaces are no more than aspherical ones where the conic coefficient is null, real lenses (“real” as opposed to “infinitely thin paraxial”) are composed of two spherical interfaces and so on. This outlines the interest of the Object Oriented conception of KOJAC. Extending KOJAC with new classes is thus simple, and creating a system is also a task that does not require advanced knowledge in optics.

## Features

Among the various features of KOJAC, the main ones are:

* raytracing approach
	* no paraxial restrictions
	* elements can be off axis
	* all computations are done in 3 dimensions - no 2D restriction
	* rays may have amplitude, polarisation, wavelength (rays are painted in their corresponding colour on screen) and more
* The concept of materials
	* refraction index can be described by up to 10 formulas
	* polarisation dependant effects may be introduced
* Object orientation
	* devices can be built from primitives or from other devices
* The device switcher
	* this special device contains many elements that can be interchanged

In the future, KOJAC may be extended with new elements (GRIN lenses, fibres, cylindrical lenses, prisms, mirrors). We also plan to enhance the display routines with zoom or perspective capabilities. Finally, we plan to create more non optic devices (like the device switcher), i.e. elements that do not interact with the light but provide new capabilities to the tutorial designer.

## Documentation

To start with the program’s internals, recommended readings are the [semester work report][report] of Olivier Scherler, and the [slides][] of his presentation (PowerPoint format, zipped).
Experimenting with the existing tutorials is a good mean to learn more about the general structure of the libraries and the way an applet can be built. Writing an applet does not require much work and knowledge, and is straightforward if the author has some notions of Object Oriented Programming and Java.

[report]: http://olivier.ithink.ch/kojac/download/KOJAC%20Report.pdf
[slides]: http://olivier.ithink.ch/kojac/download/KOJAC%20Presentation.zip

Extending the sources needs more time, but <a title="I did not write this and I can assure you that I’m half-laughing, half-embarassed as I read it.">the Object Orientation of Java is a guaranty of clearness of the code. “Spaghetti” code is hard to write in Java</a>.

## Examples

At present, KOJAC archive contains the applets that are presented on our [tutorials page][tutorials], plus a few more. These applets are:

* The aspherical surface
* The plano-convex lens aberrations
* The field lens
* The achromat
* The paraxial and real lens doublet

[tutorials]: http://olivier.ithink.ch/kojac/optics_tutorials.html

## KOJAC Used Elsewhere

KOJAC has been used by David Przewozny of Sternklar (a German amator astronomy site) to demonstrate the effect of the telescope. Its applet shows how the light is concentrated by a telescope (resulting in brighter images), and how the angles are amplified, for a good separation between the stars.

KOJAC was used by a research group of Stanford University, CA, as part of a genetic algorithm used to design optical systems:

* John R. Koza, Sameer H. Al-Sakran and Lee W. Jones, “Automated _ab initio_ synthesis of complete designs of four patented optical lens systems by means of genetic programming,” _Artificial Intelligence for Engineering Design, Analysis and Manufacturing_ **22** (3), 249–273 (2008)

* John R. Koza, Sameer H. Al-Sakran and Lee W. Jones, “Automated re-invention of six patented optical lens systems using genetic programming,” in _Proceeding GECCO '05, Proceedings of the 2005 conference on Genetic and evolutionary computation_ (2005).

* Lee W. Jones, Sameer H. Al-Sakran and John R. Koza, “Automated synthesis of a human-competitive solution to the challenge problem of the 2002 international optical design conference by means of genetic programming and a multi-dimensional mutation operation,” in _Proceeding GECCO '06, Proceedings of the 8th annual conference on Genetic and evolutionary computation_ (2006).

## License and Copyright

The Java source code of the framework and the source code of the tutorials are released under the General Public License (GPL). Briefly, it states you are allowed to copy, translate, modify or sell it under the following conditions:

* All derived work are released under the same licence
* The source code must be freely available
* Copyright notice and licence must be explicitly found with the program
