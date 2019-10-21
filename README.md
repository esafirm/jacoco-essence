# Jacoco Essence

A tool to extract Jacoco result such as total project coverage or class by class coverage for CI/CD usages or anything you want.

## Build & Test

To build the jar simply run

```bash
./gradlew fatJar
```

The jar will be located on `build/libs` directory.

To run the test use

```bash
./gradlew test
```

## Usage

You must specify where the `jacoco.xml` path in `--input`

Example:

```bash
java -jar ./jacoco-essence-1.0-SNAPSHOT.jar --input=src/test/resources/jacoco.xml
```

### Specify files to check

By default `jacoco-essence` will running git diff to `master` branch.
To change the destination you can specify the branch with `--dest`

```bash
java -jar ./jacoco-essence-1.0-SNAPSHOT.jar --input=src/test/resources/jacoco.xml --dest=develop --min=75
```

`jacoco-essence` also can take the affected files list with `git diff --name-only` format, like this

```bash
src/main/ClassToTest.kt
src/main/SomeProcessor.kt
```

You can specify the diff data by passing `--diff` parameter. This is also helpful if your CI environment already have the data

```bash
java -jar ./jacoco-essence-1.0-SNAPSHOT.jar --input=src/test/resources/jacoco.xml --diff=$GIT_FILES
```

## License

MIT @ Esa Firman
