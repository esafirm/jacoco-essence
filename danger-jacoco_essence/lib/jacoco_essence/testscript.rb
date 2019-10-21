#!/usr/bin/env ruby

def doReport(minCoverage)
    jarFile = "#{File.dirname(__FILE__)}/jacoco-essence-1.0-SNAPSHOT.jar"
    jacocoFile = "#{File.dirname(__FILE__)}/../../spec/fixtures/jacoco.xml"

    script = "java -jar #{jarFile} -g --input=#{jacocoFile} --min=#{minCoverage}"
    puts script

    markdown_report = `#{script}`

    puts "Exit status #{$?.exitstatus}"
    if $?.exitstatus != 0
        puts "Is error"
    else
        puts "Is not error"
    end

    puts markdown_report
end

doReport(10)
puts "-----"
doReport(0)