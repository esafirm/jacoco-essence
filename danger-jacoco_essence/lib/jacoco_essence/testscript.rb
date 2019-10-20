#!/usr/bin/env ruby

jarFile = "#{File.dirname(__FILE__)}/jacoco-essence-1.0-SNAPSHOT.jar"
jacocoFile = "#{File.dirname(__FILE__)}/../../spec/fixtures/jacoco.xml"
min = "10"

puts "'java -jar #{jarFile} -g --input=#{jacocoFile}' --min=#{min}"

markdown_report = `java -jar #{jarFile} -g --input="#{jacocoFile}"`

code = system("java -jar #{jarFile} -g --input=\"#{jacocoFile}\"")

puts "Code: #{code}"

puts $?.exitstatus 
puts "Exit status #{$?.exitstatus}"
puts markdown_report