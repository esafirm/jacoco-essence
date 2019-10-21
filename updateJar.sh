#!/usr/bin/env bash

./gradlew fatJar
cp build/libs/*.jar danger-jacoco_essence/lib/jacoco_essence/

echo "All set!"