How To Release
==============

One-Time Setup
--------------

Import Authlete's private key.

    $ gpg --import admin_at_authlete_com-sec.asc

If the private key was imported successfully, the following command shows
a key owned by _"Authlete, Inc."_.

    $ gpg --list-secret-keys

Edit `~/.m2/settings.xml` for automated signing and releasing.

    <settings
      xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          http://maven.apache.org/xsd/settings-1.0.0.xsd">
      <servers>
        <server>
          <id>authlete</id>
          <username>authlete</username>
          <password>{password}</password>
        </server>
      </servers>

      <profiles>
        <profile>
          <id>authlete</id>
          <properties>
            <gpg.keyname>E834481D</gpg.keyname>
          </properties>
        </profile>
      </profiles>
    </settings>


Update Documents
----------------

Update version numbers hard-coded in `README.md`, `README.ja.md` and
`src/main/java/com/authlete/common/package-info.java`.

Update `CHANGES.md` and `CHANGES.ja.md`.

Don't forget to add `@since {version}` to JavaDoc if you added new methods,
classes and enums.


Generate and Release Package
----------------------------

    $ mvn clean
    $ mvn release:prepare
    $ mvn release:perform


Publish JavaDoc
---------------

    $ mkdir -p ../docs
    $ cd ../docs
    $ git clone https://github.com/authlete/authlete-java-common
    $ cd authlete-java-common
    $ git checkout gh-pages
    $ rm -rf *
    $ jar xf ../../authlete-java-common/target/authlete-java-common-{version}-javadoc.jar
    $ rm -rf META-INF
    $ git add .
    $ git commit -m 'Updated JavaDoc for version {version}'
    $ git push origin gh-pages
