# Prerequisites

To start development work, you have to prepare the development environment firstly. 

I assume you have installed the following software.

## Git

Install [Git](https://git-scm.com/) and Git GUI tools, such as TortoiseGit, GitHub Desktop, etc.

Optionally install [Github CLI](https://cli.github.com/) if you prefer using command/shell instead of the GUI tools.

## JDK 17

Although Jakarta EE 10 uses Java 11 as baseline, and also supports Java 17. But it is recommended to use to the latest LTS version as possible when you are starting a new project.

There are several JDK redistributions available in these days, choose your favoraite from the following list. 

* [OpenJDK]( https://openjdk.java.net)
* [Oracle JDK]( https://java.oracle.com)
* [Temurin OpenJDK]( https://adoptium.net/)
* [Zulu OpenJDK](https://www.azul.com/downloads/zulu-community/) by Azul
* [Microsoft OpenJDK](https://www.microsoft.com/openjdk)
* [BellSoft Liberica JDK](https://bell-sw.com/pages/downloads)

Additionally, IBM, Amazon, Alibaba, RedHat have maintained their own Java redistributions. 

My preferred is Temurin, it is envoved from the former AdoptOpenJDK which is a 100% community-support project.

## Apache Maven 

Download the latest [Apache Maven](https://maven.apache.org), extrac the files to you local machine.

Add a new environment variable `MAVEN_HOME`, the value is the maven installation location.

Also append *%MAVEN_HOME%/bin* to your system PATH environment variable.

## An Editor or IDE 

A great editor or IDE will speed up your development productivity. Choose your favorite one from the following list.

* [Visual Studio Cdoe](https://code.visualstudio.com) and [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
* [Intellij IDEA Communitiy Edition](https://www.jetbrains.com/idea/download/)(free)
* [Apache NetBeans](http://netbeans.apache.org)
* [Eclipse IDE for Java EE Developers](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)

My preferred IDE is IntelliJ IDEA.