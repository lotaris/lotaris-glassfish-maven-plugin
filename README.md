# lotaris-glassfish-maven-plugin

> A plugin to create/drop domains, deploy application and manage resources.

## Usage

1. Put the following dependency in your pom.xml

```xml
<plugin>
	<groupId>com.lotaris.maven.plugins</groupId>
	<artifactId>lotaris-glassfish-maven-plugin</artifactId>
	<version>1.3.0</version>
	<executions>
		<execution>
			<id>glassfish</id>
			<goals>
				<goal>deploy</goal>
			</goals>
		</execution>
	</executions>
	
	<configuration>
		<glassfish>
			<home>path/to/glassfish/home</home>
			<user>admin</user>
			<passwordFile>path/to/glassfish/home/bin/PASSWORD_FILE</passwordFile>
			<echo>true</echo>
			<terse>false</terse>
		</glassfish>

		<domain>
			<name>domain1</name>
			<adminPort>4848</adminPort>
		</domain>

		<deployConfig>
			<name>${project.artifactId}</name>
			<file>${project.basedir}/target/${project.build.finalName}.${project.packaging}</file>
			<type>${project.packaging}</type>
			<force>true</force>
		</deployConfig>
	</configuration>
</plugin>
```

### Requirements

* Java 6+

## Known issues

1. If you are using glassfish 3.x with this plugin, you should not use the config:

```xml
...
	<deployConfig>
		...
		<type>ear</type>
		...
	</deployConfig>
...
```

Only one type is recognized by `asadmin` in glassfish 3.x. Therefore, the type is guessed from the packaging itself during the deployment.

See: http://docs.oracle.com/cd/E18930_01/html/821-2433/deploy-1.html#SJSASEEREFMANdeploy-1

## Contributing

* [Fork](https://help.github.com/articles/fork-a-repo)
* Create a topic branch - `git checkout -b feature`
* Push to your branch - `git push origin feature`
* Create a [pull request](http://help.github.com/pull-requests/) from your branch

Please add a changelog entry with your name for new features and bug fixes.

## License

**lotaris-glassfish-maven-plugin** is licensed under the [MIT License](http://opensource.org/licenses/MIT).
See [LICENSE.txt](LICENSE.txt) for the full text.
