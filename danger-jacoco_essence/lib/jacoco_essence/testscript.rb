#!/usr/bin/env ruby

jarFile = "#{File.dirname(__FILE__)}/jacoco-essence-1.0-SNAPSHOT.jar"
jacocoFile = "#{File.dirname(__FILE__)}/../../spec/fixtures/jacoco.xml"

puts "'java -jar #{jarFile} -g --input=#{jacocoFile}'"

markdown_report = `java -jar #{jarFile} -g --input="#{jacocoFile}"`

puts markdown_report