# Native Apps 1: Android

This is the syllabus used for the course Native Apps 1 (Android) @ Hogeschool Gent

## Getting Started for the syllabus

These instructions will get you a copy of the syllabus on your local machine.

This template uses biber for its bibliography xelatex (although pdflatex will probably work as well).
When you first open the template, compile it from the command line with the
commands below to make sure your LaTeX distribution is configured correctly:

* xelatex -synctex=1 -interaction=nonstopmode "cursus-nativeapps".tex
* biber "cursus-nativeapps"
* xelatex -synctex=1 -interaction=nonstopmode "cursus-nativeapps".tex
* xelatex -synctex=1 -interaction=nonstopmode "cursus-nativeapps".tex

After this, when you wish to update the bibliography/index use the appropriate
command above and make sure to compile with xelatex several times
afterwards to propagate your changes to the document.

This template also uses a number of packages which may need to be
updated to the newest versions for the template to compile. It is strongly
recommended you update your LaTeX distribution if you have any
compilation errors.



## Prerequisites

Successfully installing the Android SDK requires installing the Java Development Kit (JDK). If your system has an up-to-date JDK installed, you won’t need to install it again. The JDK provides tools, such as the Java compiler, used by IDEs and SDKs for developing Java programs. The JDK also contains a Java Runtime Environment (JRE), which enables Java programs to run on your system. You can find different tutorials online on how to install the JDK, e.g. [this one](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html)


## Installing Android Studio

You will also need to install Android Studio. You can find detailed instructions [on this links](https://developer.android.com/studio/install.html)


## Contributing

Please read [CONTRIBUTING.md](https://github.com/eothein/nativeapps1/blob/master/contribute.md) for details on our code of conduct, and the process for submitting pull requests.


## Versioning

We will try to  use [SemVer](http://semver.org/) for versioning. 

## Authors

* **Jens Buysse** - *Initial work*
* **Karine Samyn** - *Reviewing*
