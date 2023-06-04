# Prerequisites

To start development work, you have to prepare the development environment firstly. 

I assume you have installed the following software.

* [Git](https://git-scm.com/)
* JDK 17+
* The latest [Apache Maven](https://maven.apache.org)
* Your favorite text Editor or Java IDE

## Git

For GNU Linux users, it is easy to get it installed via the Linux built-in repository.

For Windows users, download [Git](https://git-scm.com/), install it into your system. 

Alternatively, install it via `winget` command. Open Powershell with administrator role, run the following command to install Git.

```bash 
winget install Git.Git
```

Some Git GUI tools such as TortoiseGit, GitHub Desktop, etc. provides friendly interactive GUI for developers, choose your favorite GUI tools yourself.

Optionally install [Github CLI](https://cli.github.com/) if you prefer using command/shell instead of the GUI tools.

## JDK 17

Although Jakarta EE 10 uses Java 11 as baseline, and also need to support Java 17. But it is highly recommended to use to the latest LTS version (currently it is Java 17) when you are starting a new project.

There are several JDK redistributions available in these days, choose your favorite one from the following list. 

* [OpenJDK]( https://openjdk.java.net)
* [Oracle JDK]( https://java.oracle.com)
* [Eclipse Temurin]( https://adoptium.net/)
* [Zulu OpenJDK](https://www.azul.com/downloads/zulu-community/)
* [Microsoft OpenJDK](https://www.microsoft.com/openjdk)
* [BellSoft Liberica JDK](https://bell-sw.com/pages/downloads)

Additionally, IBM, Amazon, Alibaba, RedHat have maintained their own Java redistributions. 

My preferred is Eclipse Temurin, which is evolved from the former AdoptOpenJDK and is a 100% community-support project.

## Apache Maven 

Download the latest [Apache Maven](https://maven.apache.org), extract the files to you local machine.

Define a new environment variable `MAVEN_HOME`, the value is the Maven installation location.

Also append *%MAVEN_HOME%/bin* to your system PATH environment variable.

## An Editor or IDE 

A great editor or IDE will speed up your development productivity. Choose your favorite one from the following list.

* [Visual Studio Code](https://code.visualstudio.com) and [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
* [Intellij IDEA Community Edition](https://www.jetbrains.com/idea/download/)(free)
* [Apache NetBeans](http://netbeans.apache.org)
* [Eclipse IDE for Java EE Developers](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)

My preferred IDE is IntelliJ IDEA.