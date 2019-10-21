module Danger
  # This is your plugin class. Any attributes or methods you expose here will
  # be available from within your Dangerfile.
  #
  # To be published on the Danger plugins site, you will need to have
  # the public interface documented. Danger uses [YARD](http://yardoc.org/)
  # for generating documentation from your plugin source, and you can verify
  # by running `danger plugins lint` or `bundle exec rake spec`.
  #
  # You should replace these comments with a public description of your library.
  #
  # @example Ensure people are well warned about merging on Mondays
  #
  #          my_plugin.warn_on_mondays
  #
  # @see  Esa Firman/danger-jacoco_essence
  # @tags monday, weekends, time, rattata
  #
  class DangerJacocoEssence < Plugin
    # An attribute that you can read/write from your Dangerfile
    #
    # @return   [Array<String>]
    attr_accessor :minimum_project_coverage_percentage
    attr_accessor :minimum_class_coverage_percentage
    attr_accessor :files_extension

    # A method that you can call from your Dangerfile
    # @return   [Array<String>]
    #
    def report(path)
      jarFile = "#{File.dirname(__FILE__)}/jacoco-essence-1.0-SNAPSHOT.jar"

      script = "java -jar #{jarFile}"
      script << " --input=\"#{path}\""

      unless minimum_project_coverage_percentage.nil?
        script << " --min=#{minimum_project_coverage_percentage}"
      end 

      unless minimum_class_coverage_percentage.nil?
        script << " --min_class=#{minimum_class_coverage_percentage}"
      end

      markdown_report = `#{script}`
      
      if $?.exitstatus != 0
        puts "ERROR"
        fail("Coverage not met the spesification")
      else
        markdown(markdown_report)
      end
    end

  end
end
